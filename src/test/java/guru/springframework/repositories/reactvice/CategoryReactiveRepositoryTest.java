package guru.springframework.repositories.reactvice;

import guru.springframework.domian.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@DataMongoTest
class CategoryReactiveRepositoryTest {

    private final CategoryReactiveRepository categoryReactiveRepository;

    @Autowired
    CategoryReactiveRepositoryTest(CategoryReactiveRepository categoryReactiveRepository) {
        this.categoryReactiveRepository = categoryReactiveRepository;
    }

    @BeforeEach
    void setUp() {
        categoryReactiveRepository.deleteAll().block();
    }

    @Test
    public void testSave() throws Exception{
        Category category = new Category();
        category.setDescription("Foo");

        categoryReactiveRepository.save(category).block();
        Long count = categoryReactiveRepository.count().block();
        assertEquals(Long.valueOf(1L),count);
    }
    @Test
    public void testFindByDescription()throws Exception{
        Category category = new Category();
        category.setDescription("foo");

        categoryReactiveRepository.save(category).then().block();
        Category fetchCat= categoryReactiveRepository.findByDescription("foo").block();
        assertNotNull(fetchCat.getDescription());
    }
}