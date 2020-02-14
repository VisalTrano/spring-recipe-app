package guru.springframework.converters.categories;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domian.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {
    @Override
    public CategoryCommand convert(Category category) {
        if (category == null)
            return null;
        CategoryCommand categoryCommand = new CategoryCommand().builder()
                .id(category.getId())
                .description(category.getDescription())
                .build();
        return categoryCommand;
    }
}
