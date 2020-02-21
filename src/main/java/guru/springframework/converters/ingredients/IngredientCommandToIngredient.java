package guru.springframework.converters.ingredients;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.converters.uom.UnitOfMeasureCommandToUnitOfMeasure;
import guru.springframework.domian.Ingredient;
import guru.springframework.domian.Recipe;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {
    private final UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure;

    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure) {
        this.unitOfMeasureCommandToUnitOfMeasure = unitOfMeasureCommandToUnitOfMeasure;
    }

    @Override
    public Ingredient convert(IngredientCommand ingredientCommand) {
        if (ingredientCommand == null)
            return null;
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ingredientCommand.getId());
        ingredient.setAmount(ingredientCommand.getAmount());
        ingredient.setDescription(ingredientCommand.getDescription());
        if(ingredientCommand.getRecipeId()!=null){
            Recipe recipe = new Recipe();
            recipe.setId(ingredientCommand.getRecipeId());
        }
        ingredient.setUom(unitOfMeasureCommandToUnitOfMeasure.convert(ingredientCommand.getUnitOfMeasure()));
        return ingredient;
    }
}
