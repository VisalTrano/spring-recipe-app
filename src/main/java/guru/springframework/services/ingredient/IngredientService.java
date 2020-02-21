package guru.springframework.services.ingredient;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domian.Ingredient;
import reactor.core.publisher.Mono;

import java.util.Set;

public interface IngredientService {
    Mono<Ingredient> findById(String id);

    Mono<IngredientCommand> findCommandById(String id);

    Mono<IngredientCommand> saveIngredientCommand(IngredientCommand ingredientCommand);
    IngredientCommand saveIngredientCommand2(IngredientCommand ingredientCommand);

    Mono<Void> deletedById(String id);

    Set<Ingredient> getIngredients();
    Mono<IngredientCommand> findByRecipeIdAndIngredientId(String recipeId, String ingredientId);
}
