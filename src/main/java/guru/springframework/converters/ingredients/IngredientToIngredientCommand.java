package guru.springframework.converters.ingredients;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.converters.uom.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.domian.Ingredient;
import guru.springframework.domian.UnitOfMeasure;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {
    private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
        this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
    }

    @Override
    public IngredientCommand convert(Ingredient ingredient) {
        if (ingredient == null)
            return null;
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ingredient.getId());
        if (ingredient.getRecipe() != null)
            ingredientCommand.setRecipeId(ingredient.getRecipe().getId());
        ingredientCommand.setDescription(ingredient.getDescription());
        ingredientCommand.setAmount(ingredient.getAmount());
        UnitOfMeasure unitOfMeasure = ingredient.getUnitOfMeasure();
        UnitOfMeasureCommand unitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand.convert(unitOfMeasure);
        ingredientCommand.setUnitOfMeasure(unitOfMeasureCommand);
        return ingredientCommand;
    }
}
