package guru.springframework.converters.recipes;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.converters.categories.CategoryCommandToCategory;
import guru.springframework.converters.ingredients.IngredientCommandToIngredient;
import guru.springframework.converters.notes.NotesCommandToNotes;
import guru.springframework.domian.Recipe;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Objects;
@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final NotesCommandToNotes notesCommandToNotes;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final CategoryCommandToCategory categoryCommandToCategory;

    public RecipeCommandToRecipe(NotesCommandToNotes notesCommandToNotes, IngredientCommandToIngredient ingredientCommandToIngredient, CategoryCommandToCategory categoryCommandToCategory) {
        this.notesCommandToNotes = notesCommandToNotes;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.categoryCommandToCategory = categoryCommandToCategory;
    }

    @Override
    public Recipe convert(RecipeCommand recipeCommand) {
        if (recipeCommand == null)
            return null;
        Recipe recipe = new Recipe();
        recipe.setId(recipeCommand.getId());
        recipe.setDescription(recipeCommand.getDescription());
        recipe.setPrepTime(recipeCommand.getPrepTime());
        recipe.setCookTime(recipeCommand.getCookTime());
        recipe.setServing(recipeCommand.getServing());
        recipe.setUrl(recipeCommand.getUrl());
        recipe.setSource(recipeCommand.getSource());
        recipe.setDirections(recipeCommand.getDirections());
        recipe.setImage(recipeCommand.getImage());
        recipe.setDifficulty(recipeCommand.getDifficulty());
        recipe.setNotes(Objects.requireNonNull(notesCommandToNotes.convert(recipeCommand.getNotes())));
        if (recipeCommand.getIngredients() != null && recipeCommand.getIngredients().size() > 0) {
            recipeCommand.getIngredients().forEach(ingredient -> recipe.getIngredients().add(ingredientCommandToIngredient.convert(ingredient)));
        }
        if (recipeCommand.getCategories() != null && recipeCommand.getCategories().size() > 0) {
            recipeCommand.getCategories().forEach(category -> recipe.getCategories().add(categoryCommandToCategory.convert(category)));
        }
        return recipe;
    }
}
