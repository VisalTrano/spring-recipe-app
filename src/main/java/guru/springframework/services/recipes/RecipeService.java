package guru.springframework.services.recipes;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domian.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();

    Recipe findById(Long l);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    void deleteById(Long id);

    RecipeCommand findCommandById(long anyLong);
}
