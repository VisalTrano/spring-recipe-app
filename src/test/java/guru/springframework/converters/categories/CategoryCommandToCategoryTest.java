package guru.springframework.converters.categories;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domian.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryCommandToCategoryTest {

    static final String DESCRIPTION = "description";
    static final Long LONG_VALUE = 1L;
    CategoryCommandToCategory converter;

    @BeforeEach
    void setUp() {
        converter = new CategoryCommandToCategory();
    }

    @Test
    void convertNull() {
        assertNull(converter.convert(null));
    }

    @Test
    void convert() {
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setDescription(DESCRIPTION);
        categoryCommand.setId("1");

        Category category=converter.convert(categoryCommand);

        assertNotNull(category);
        assertEquals(category.getId(),categoryCommand.getId());
        assertEquals(category.getDescription(),categoryCommand.getDescription());

    }
}