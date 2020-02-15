package guru.springframework.domian;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

class CategoryTest {
    private Category category;

    @BeforeEach
    void setUp() {
        category = new Category();
    }

    @Test
    public void getId() throws Exception {

        String idValue = "3";
        category.setId(idValue);
        assertEquals(idValue, category.getId());
    }

    @Test
    void getDescription() {
    }

    @Test
    void getRecipes() {
    }
}