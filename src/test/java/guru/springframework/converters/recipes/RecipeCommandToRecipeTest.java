package guru.springframework.converters.recipes;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.NotesCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.converters.categories.CategoryCommandToCategory;
import guru.springframework.converters.ingredients.IngredientCommandToIngredient;
import guru.springframework.converters.notes.NotesCommandToNotes;
import guru.springframework.converters.recipes.RecipeCommandToRecipe;
import guru.springframework.converters.uom.UnitOfMeasureCommandToUnitOfMeasure;
import guru.springframework.domian.Difficulty;
import guru.springframework.domian.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RecipeCommandToRecipeTest {
    public static final Long RECIPE_ID = 1L;
    public static final Integer COOK_TIME = Integer.valueOf("5");
    public static final Integer PREP_TIME = Integer.valueOf("7");
    public static final String DESCRIPTION = "My Recipe";
    public static final String DIRECTIONS = "Directions";
    public static final Difficulty DIFFICULTY = Difficulty.EASY;
    public static final Integer SERVINGS = Integer.valueOf("3");
    public static final String SOURCE = "Source";
    public static final String URL = "Some URL";
    public static final Long CAT_ID_1 = 1L;
    public static final Long CAT_ID2 = 2L;
    public static final Long INGRED_ID_1 = 3L;
    public static final Long INGRED_ID_2 = 4L;
    public static final Long NOTES_ID = 9L;

    RecipeCommandToRecipe converter;

    @BeforeEach
    void setUp() {
        converter = new RecipeCommandToRecipe(new NotesCommandToNotes(), new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()), new CategoryCommandToCategory());
    }

    @Test
    void converterNull() {
        assertNull(converter.convert(null));
    }

    @Test
    void convert() {
        RecipeCommand recipeCommand = new RecipeCommand().builder().id(RECIPE_ID)
                .description(DESCRIPTION)
                .prepTime(PREP_TIME)
                .cookTime(COOK_TIME)
                .serving(SERVINGS)
                .source(SOURCE)
                .url(URL)
                .directions(DIRECTIONS)
                .difficulty(DIFFICULTY)
                .notes(new NotesCommand().builder().id(NOTES_ID).build())
                .categories(new HashSet<>())
                .ingredients(new HashSet<>())
                .build();

        //Add category to Recipe
//        Set<CategoryCommand> categoryCommands = new HashSet<>();
//        categoryCommands.add(new CategoryCommand().builder().id(CAT_ID_1).build());
//        categoryCommands.add(new CategoryCommand().builder().id(CAT_ID2).build());
//        recipeCommand.setCategories(categoryCommands);

        CategoryCommand cat1 = new CategoryCommand().builder().id(CAT_ID_1).build();
        CategoryCommand cat2 = new CategoryCommand().builder().id(CAT_ID2).build();
        recipeCommand.AddCategory(cat1);
        recipeCommand.AddCategory(cat2);
        //Add Ingredients to recipe
        Set<IngredientCommand> ingredientCommands = new HashSet<>();
        ingredientCommands.add(new IngredientCommand().builder().id(INGRED_ID_1).build());
        ingredientCommands.add(new IngredientCommand().builder().id(INGRED_ID_2).build());
        recipeCommand.getIngredients().addAll(ingredientCommands);

        Recipe recipe = converter.convert(recipeCommand);
        assertNotNull(recipe);
        assertEquals(RECIPE_ID, recipe.getId());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(PREP_TIME, recipe.getPrepTime());
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(DIFFICULTY, recipe.getDifficulty());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(SERVINGS, recipe.getServing());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(URL, recipe.getUrl());
        assertEquals(NOTES_ID, recipe.getNotes().getId());
        assertEquals(2, recipe.getCategories().size());
        assertEquals(2, recipe.getIngredients().size());
    }
}