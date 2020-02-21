package guru.springframework.services.ingredient;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.converters.ingredients.IngredientCommandToIngredient;
import guru.springframework.converters.ingredients.IngredientToIngredientCommand;
import guru.springframework.domian.Ingredient;
import guru.springframework.domian.Recipe;
import guru.springframework.domian.UnitOfMeasure;
import guru.springframework.repositories.IngredientRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import guru.springframework.repositories.reactvice.RecipeReactiveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class IngredientServiceImp implements IngredientService {
    private final IngredientRepository ingredientRepository;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final RecipeReactiveRepository recipeReactiveRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IngredientServiceImp(IngredientRepository ingredientRepository, IngredientToIngredientCommand ingredientToIngredientCommand, IngredientCommandToIngredient ingredientCommandToIngredient, RecipeReactiveRepository recipeReactiveRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.ingredientRepository = ingredientRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.recipeReactiveRepository = recipeReactiveRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public Mono<Ingredient> findById(String id) {
        return Mono.just(ingredientRepository.findById(id).orElse(null));
    }

    @Override
    public Mono<IngredientCommand> findCommandById(String id) {
        return Mono.just(ingredientToIngredientCommand.convert(findById(id).block()));
    }

    @Override
    public Mono<IngredientCommand> saveIngredientCommand(IngredientCommand ingredientCommand) {

        Optional<Recipe> recipeOptional = recipeReactiveRepository.findById(ingredientCommand.getRecipeId()).blockOptional();
        if (!recipeOptional.isPresent()) {
            log.error("Recipe not found for id: " + ingredientCommand.getRecipeId());
            return Mono.just(new IngredientCommand());
        }
        Recipe recipe = recipeOptional.get();
        Optional<Ingredient> ingredientOptional = recipe
                .getIngredients()
                .stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientCommand.getId()))
                .findFirst();
        if (ingredientOptional.isPresent()) {
            Ingredient ingredientFound = ingredientOptional.get();
            ingredientFound.setDescription(ingredientCommand.getDescription());
            ingredientFound.setAmount(ingredientCommand.getAmount());
            ingredientFound.setUom(unitOfMeasureRepository
                    .findById(ingredientCommand.getUnitOfMeasure().getId())
                    .orElseThrow(() -> new RuntimeException("UOM NOT FOUND"))); //todo address this
        } else {
            //add new Ingredient
            Ingredient ingredient = ingredientCommandToIngredient.convert(ingredientCommand);
            recipe.addIngredient(ingredient);
        }
        Recipe savedRecipe = recipeReactiveRepository.save(recipe).block();

        Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredients().stream()
                .filter(recipeIngredients -> recipeIngredients.getId().equals(ingredientCommand.getId()))
                .findFirst();

        //check by description
        if (!savedIngredientOptional.isPresent() || savedIngredientOptional.get().getId().equals("")) {
            //not totally safe... But best guess
            savedIngredientOptional = savedRecipe.getIngredients().stream()
                    .filter(recipeIngredients -> recipeIngredients.getDescription().equals(ingredientCommand.getDescription()))
                    .filter(recipeIngredients -> recipeIngredients.getAmount().equals(ingredientCommand.getAmount()))
                    .filter(recipeIngredients -> recipeIngredients.getUom().getId().equals(ingredientCommand.getUnitOfMeasure().getId()))
                    .findFirst();
        }

        //to do check for fail
        return Mono.just(ingredientToIngredientCommand.convert(savedIngredientOptional.get()));
    }

    @Override
    public IngredientCommand saveIngredientCommand2(IngredientCommand ingredientCommand) {

        Optional<Recipe> recipeOptional = recipeReactiveRepository.findById(ingredientCommand.getId()).blockOptional();
        if (!recipeOptional.isPresent()) {
            log.error("Recipe not found for id: " + ingredientCommand.getRecipeId());
            return new IngredientCommand();
        }

        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findById(ingredientCommand.getUnitOfMeasure().getId());
        if (!unitOfMeasureOptional.isPresent()) {
            log.error("UnitOfMeasure not found for id: " + ingredientCommand.getUnitOfMeasure().getId());
            return new IngredientCommand();
        }
        Ingredient ingredient = ingredientCommandToIngredient.convert(ingredientCommand);
        ingredient.setUom(unitOfMeasureOptional.get());
        Ingredient saveIngredient = ingredientRepository.save(ingredient);
        return ingredientToIngredientCommand.convert(saveIngredient);
    }

    @Override
    public Mono<Void> deletedById(String id) {
        ingredientRepository.deleteById(id);
        return Mono.empty();
    }

    @Override
    public Set<Ingredient> getIngredients() {
        Set<Ingredient> ingredients = new HashSet<>();
        ingredientRepository.findAll().iterator().forEachRemaining(ingredients::add);
        return ingredients;
    }

    @Override
    public Mono<IngredientCommand> findByRecipeIdAndIngredientId(String recipeId, String ingredientId) {

        return
                recipeReactiveRepository
                        .findById(recipeId)
                        .flatMapIterable(Recipe::getIngredients)
                        .filter(ingredient -> ingredient.getId().equalsIgnoreCase(ingredientId))
                        .single()
                        .map(ingredient -> {
                            IngredientCommand command = ingredientToIngredientCommand.convert(ingredient);
                            command.setRecipeId(recipeId);
                            return command;
                        });
//        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
//
//        if (!recipeOptional.isPresent()) {
//            //todo impl error handling
//            log.error("recipe id not found. Id: " + recipeId);
//        }
//
//        Recipe recipe = recipeOptional.get();
//
//        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
//                .filter(ingredient -> ingredient.getId().equals(ingredientId))
//                .map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();
//
//        if (!ingredientCommandOptional.isPresent()) {
//            //todo impl error handling
//            log.error("Ingredient id not found: " + ingredientId);
//        }
//
//        return Mono.just( ingredientCommandOptional.get());
    }

}
