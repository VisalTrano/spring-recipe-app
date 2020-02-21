package guru.springframework.services.ingredient;

import guru.springframework.converters.ingredients.IngredientCommandToIngredient;
import guru.springframework.converters.ingredients.IngredientToIngredientCommand;
import guru.springframework.domian.Ingredient;
import guru.springframework.repositories.IngredientRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import guru.springframework.repositories.reactvice.RecipeReactiveRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class IngredientServiceImpTest {
    @Mock
    IngredientRepository ingredientRepository;

    @Mock
    private RecipeReactiveRepository recipeReactiveRepository;
    @Mock
    private UnitOfMeasureRepository unitOfMeasureRepository;
    IngredientToIngredientCommand ingredientToIngredientCommand;

    IngredientCommandToIngredient ingredientCommandToIngredient;

    IngredientService ingredientService;
    String ID_VALUE = "1";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ingredientService = new IngredientServiceImp(ingredientRepository, ingredientToIngredientCommand, ingredientCommandToIngredient, recipeReactiveRepository, unitOfMeasureRepository);
    }

    @Test
    void findCommandById() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VALUE);
        Optional<Ingredient> ingredientData = Optional.of(ingredient);
        when(ingredientRepository.findById(anyString())).thenReturn(ingredientData);
        Ingredient ingredientReturn = ingredientService.findById(ID_VALUE).block();
        assertNotNull(ingredientReturn);
    }
}