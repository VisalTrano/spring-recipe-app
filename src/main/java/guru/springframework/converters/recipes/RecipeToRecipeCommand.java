package guru.springframework.converters.recipes;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.converters.categories.CategoryToCategoryCommand;
import guru.springframework.converters.ingredients.IngredientToIngredientCommand;
import guru.springframework.converters.notes.NotesToNotesCommnad;
import guru.springframework.domian.Recipe;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final CategoryToCategoryCommand categoryToCategoryCommand;
    private final NotesToNotesCommnad notesToNotesCommnad;

    public RecipeToRecipeCommand(IngredientToIngredientCommand ingredientToIngredientCommand, CategoryToCategoryCommand categoryToCategoryCommand, NotesToNotesCommnad notesToNotesCommnad) {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.categoryToCategoryCommand = categoryToCategoryCommand;
        this.notesToNotesCommnad = notesToNotesCommnad;
    }

    @Override
    public RecipeCommand convert(Recipe recipe) {
        if (recipe == null)
            return null;
        Set<CategoryCommand> categoryCommands = new HashSet<>();
        if (recipe.getCategories() != null && recipe.getCategories().size() > 0) {
            recipe.getCategories().forEach(category -> categoryCommands.add(categoryToCategoryCommand.convert(category)));
        }
        Set<IngredientCommand> ingredientCommands = new HashSet<>();
        if (recipe.getIngredients() != null && recipe.getIngredients().size() > 0) {
            recipe.getIngredients().forEach(ingredient -> ingredientCommands.add(ingredientToIngredientCommand.convert(ingredient)));
        }
        RecipeCommand recipeCommand = new RecipeCommand().builder()
                .id(recipe.getId())
                .description(recipe.getDescription())
                .prepTime(recipe.getPrepTime())
                .cookTime(recipe.getCookTime())
                .serving(recipe.getServing())
                .source(recipe.getSource())
                .url(recipe.getUrl())
                .directions(recipe.getDirections())
                .difficulty(recipe.getDifficulty())
                .image(recipe.getImage())
                .notes(notesToNotesCommnad.convert(recipe.getNotes()))
                .categories(categoryCommands)
                .ingredients(ingredientCommands)
                .build();
        return recipeCommand;
    }
}
