package guru.springframework.converters.ingredients;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.converters.uom.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.domian.Ingredient;
import guru.springframework.domian.Recipe;
import guru.springframework.domian.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class IngredientToIngredientCommandTest {
    public static final String RECIPE_ID ="1";
    public static final BigDecimal AMOUNT = new BigDecimal("1");
    public static final String DESCRIPTION = "Cheeseburger";
    public static final String ID_VALUE ="1";
    public static final String UOM_ID = "2";
    IngredientToIngredientCommand converter;

    @BeforeEach
    void setUp() {
        converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    void converterNull() {
        assertNull(converter.convert(null));
    }

    @Test
    void convert() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VALUE);
        ingredient.setAmount(AMOUNT);
        ingredient.setDescription(DESCRIPTION);
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(UOM_ID);
        ingredient.setUom(unitOfMeasure);
        Recipe recipe = new Recipe();
        recipe.setId(RECIPE_ID);
        IngredientCommand ingredientCommand = converter.convert(ingredient);

        assertNotNull(ingredientCommand);
        assertEquals(ingredient.getId(), ingredientCommand.getId());
        assertEquals(ingredient.getAmount(), ingredientCommand.getAmount());
        assertEquals(ingredient.getDescription(), ingredientCommand.getDescription());
        assertEquals(ingredient.getUom().getId(), ingredientCommand.getUnitOfMeasure().getId());
    }
}