package guru.springframework.converters.categories;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domian.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryToCategoryCammandTest {
    static final String DESCRIPTION = "description";
    static final String LONG_VALUE = "1";
    CategoryToCategoryCommand converter;

    @BeforeEach
    void setUp() {
        converter = new CategoryToCategoryCommand();
    }
    @Test
    void convertNull() {
        assertNull(converter.convert(null));
    }
    @Test
    void convert() {
        Category category = new Category();
        category.setId(LONG_VALUE);
        category.setDescription(DESCRIPTION);

        CategoryCommand categoryCommand = converter.convert(category);
        assertNotNull(categoryCommand);
        assertEquals(category.getId(),categoryCommand.getId());
        assertEquals(category.getDescription(),categoryCommand.getDescription());
    }
}