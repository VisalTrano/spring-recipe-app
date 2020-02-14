package guru.springframework.services.ingredient;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domian.Ingredient;

import java.util.Set;

public interface IngredientService {
    Ingredient findById(Long id);

    IngredientCommand findCommandById(Long id);

    IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);
    IngredientCommand saveIngredientCommand2(IngredientCommand ingredientCommand);

    void deletedById(Long id);

    Set<Ingredient> getIngredients();
    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
}
