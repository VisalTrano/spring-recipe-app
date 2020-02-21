package guru.springframework.services.recipes;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.converters.recipes.RecipeCommandToRecipe;
import guru.springframework.converters.recipes.RecipeToRecipeCommand;
import guru.springframework.domian.Recipe;
import guru.springframework.repositories.reactvice.RecipeReactiveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeReactiveRepository recipeReactiveRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeReactiveRepository recipeReactiveRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeReactiveRepository = recipeReactiveRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Flux<Recipe> getRecipes() {
        return recipeReactiveRepository.findAll();
//        log.debug("service imp /getRecipe");
//        Set<Recipe> recipeSet = new HashSet<>();
//        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
//        return recipeSet;
    }

    @Override
    public Mono<Recipe> findById(String id) {
        Mono<Recipe> recipeMono = recipeReactiveRepository.findById(id);
        return recipeMono;

    }

    @Override
    public RecipeCommand findCommandById(String id) {
        return recipeToRecipeCommand.convert(findById(id).block());
    }

    @Override
    public Mono<RecipeCommand> saveRecipeCommand(RecipeCommand command) {
        return recipeReactiveRepository.save(recipeCommandToRecipe.convert(command))
                .map(recipeToRecipeCommand::convert);
//        Recipe detachedRecipe = recipeCommandToRecipe.convert(command);
//
//        Recipe savedRecipe = recipeRepository.save(detachedRecipe).block();
//        log.debug("Saved RecipeId:" + savedRecipe.getId());
//        return recipeToRecipeCommand.convert(savedRecipe);
    }

    @Override
    public void deleteById(String id) {
        recipeReactiveRepository.deleteById(id);
    }


}
