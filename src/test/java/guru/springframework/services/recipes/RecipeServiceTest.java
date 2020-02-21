package guru.springframework.services.recipes;

import guru.springframework.converters.recipes.RecipeCommandToRecipe;
import guru.springframework.converters.recipes.RecipeToRecipeCommand;
import guru.springframework.domian.Recipe;
import guru.springframework.exceptions.NotFoundException;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.reactvice.RecipeReactiveRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class RecipeServiceTest {
    RecipeServiceImpl recipeService;

    @Mock
    RecipeReactiveRepository recipeRepository;
    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;
    RecipeCommandToRecipe recipeCommandToRecipe;
    Long ID_VALUE = 1L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

//    @Test()
//    public void testGetRecipeIdNotFound() throws NotFoundException {
//        Optional<Recipe> recipeOptional = Optional.empty();
//        when(recipeRepository.findById(anyString())).thenReturn(Mono.empty());
//        assertThrows(NotFoundException.class, () -> {
//          Mono<Recipe> recipeMono=  recipeService.findById("1");
//        });
//
//    }


    @Test
    void getRecipes() throws Exception {
        Recipe recipe = new Recipe();
        HashSet recipeData = new HashSet();
        recipeData.add(recipe);
        when(recipeService.getRecipes()).thenReturn(Flux.just(recipe));
        List<Recipe> recipes = recipeService.getRecipes().collectList().block();
        assertEquals(recipes.size(), 1);
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    void getRecipesById() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId("1");
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyString())).thenReturn(Mono.just(recipe));

        Recipe recipeReturned = recipeService.findById("1").block();

        assertNotNull("Null recipe returned", recipeReturned);
        verify(recipeRepository, times(1)).findById(anyString());
        verify(recipeRepository, never()).findAll();
    }


}