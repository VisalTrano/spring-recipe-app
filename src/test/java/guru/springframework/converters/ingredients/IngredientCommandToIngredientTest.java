package guru.springframework.converters.ingredients;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.converters.uom.UnitOfMeasureCommandToUnitOfMeasure;
import guru.springframework.domian.Ingredient;
import guru.springframework.domian.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class IngredientCommandToIngredientTest {

    public static final Recipe RECIPE = new Recipe();
    public static final BigDecimal AMOUNT = new BigDecimal("1");
    public static final String DESCRIPTION = "Cheeseburger";
    public static final String ID_VALUE = "1";
    public static final String UOM_ID = "2";

    IngredientCommandToIngredient converter;

    @BeforeEach
    void setUp() {
        converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    void converterNull() {
        assertNull(converter.convert(null));
    }

    @Test
    void convert() {
        IngredientCommand ingredientCommand = new IngredientCommand().builder()
                .id(ID_VALUE)
                .description(DESCRIPTION)
                .amount(AMOUNT)
                .unitOfMeasure(new UnitOfMeasureCommand().builder().id(UOM_ID).build())
                .recipeId("1")
                .build();
        Ingredient ingredient = converter.convert(ingredientCommand);
        assertNotNull(ingredient);
        assertEquals(ingredient.getId(), ingredientCommand.getId());
        assertEquals(ingredient.getAmount(), ingredientCommand.getAmount());
        assertEquals(ingredient.getDescription(), ingredientCommand.getDescription());
        assertEquals(ingredient.getUom().getId(), ingredientCommand.getUnitOfMeasure().getId());
    }
}