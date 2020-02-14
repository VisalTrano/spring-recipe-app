package guru.springframework.converters.recipes;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.converters.categories.CategoryToCategoryCommand;
import guru.springframework.converters.ingredients.IngredientToIngredientCommand;
import guru.springframework.converters.notes.NotesToNotesCommnad;
import guru.springframework.converters.uom.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.domian.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecipeToRecipeCommandTest {
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

    RecipeToRecipeCommand converter;

    @BeforeEach
    void setUp() {
        converter = new RecipeToRecipeCommand(new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()), new CategoryToCategoryCommand(), new NotesToNotesCommnad());
    }

    @Test
    void converterNull() {
        assertNull(converter.convert(null));
    }

    @Test
    void convert() {
        Recipe recipe = new Recipe();
        recipe.setId(RECIPE_ID);
        recipe.setDescription(DESCRIPTION);
        recipe.setPrepTime(PREP_TIME);
        recipe.setCookTime(COOK_TIME);
        recipe.setServing(SERVINGS);
        recipe.setSource(SOURCE);
        recipe.setUrl(URL);
        recipe.setDirections(DIRECTIONS);
        recipe.setDifficulty(DIFFICULTY);

        Notes notes = new Notes();
        notes.setId(NOTES_ID);
        recipe.setNotes(notes);
        Category cat1= new Category();

        cat1.setId(CAT_ID_1);
        recipe.setNotes(notes);
        Category cat2= new Category();
        cat1.setId(CAT_ID2);
        recipe.getCategories().add(cat1);
        recipe.getCategories().add(cat2);

        Ingredient ingredient1= new Ingredient();
        ingredient1.setId(INGRED_ID_1);
        Ingredient ingredient2= new Ingredient();
        ingredient1.setId(INGRED_ID_2);

        recipe.getIngredients().add(ingredient1);
        recipe.getIngredients().add(ingredient2);

        RecipeCommand recipeCommand = converter.convert(recipe);
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