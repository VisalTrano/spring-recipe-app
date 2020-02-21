package guru.springframework.converters.categories;

import guru.springframework.domian.Category;
import guru.springframework.repositories.CategoryRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryCheckBox implements Converter<String, Category> {
    private final CategoryRepository categoryRepository;

    public CategoryCheckBox(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category convert(String id) {
        Category category = categoryRepository.findById(id).orElse(null);
        return category;
    }
}
