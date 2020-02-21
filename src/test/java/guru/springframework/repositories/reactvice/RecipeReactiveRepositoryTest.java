package guru.springframework.repositories.reactvice;

import guru.springframework.domian.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataMongoTest
class RecipeReactiveRepositoryTest {

    private final RecipeReactiveRepository recipeReactiveRepository;

    @Autowired
    RecipeReactiveRepositoryTest(RecipeReactiveRepository recipeReactiveRepository) {
        this.recipeReactiveRepository = recipeReactiveRepository;
    }

    @BeforeEach
    void setUp() {
        recipeReactiveRepository.deleteAll().block();
    }

    @Test
    public void testRecipeSave() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setDescription("Yummy");

        recipeReactiveRepository.save(recipe).block();
        Long count = recipeReactiveRepository.count().block();
        assertEquals(Long.valueOf(1L), count);
    }

    @Test
    public void testGetById() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId("1");
        recipe.setDescription("Yummy");

        recipeReactiveRepository.save(recipe).block();
        Recipe recipe1 = recipeReactiveRepository.findById("1").block();
        assertNotNull(recipe1);
    }
}