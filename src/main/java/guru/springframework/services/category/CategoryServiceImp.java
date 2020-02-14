package guru.springframework.services.category;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.converters.categories.CategoryToCategoryCommand;
import guru.springframework.domian.Category;
import guru.springframework.repositories.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
@Slf4j
@Service
public class CategoryServiceImp implements CategoryService {
    private final CategoryRepository categoryRepository;
private final CategoryToCategoryCommand categoryToCategoryCommand;
    public CategoryServiceImp(CategoryRepository categoryRepository, CategoryToCategoryCommand categoryToCategoryCommand) {
        this.categoryRepository = categoryRepository;
        this.categoryToCategoryCommand = categoryToCategoryCommand;
    }

    @Override
    public Set<Category> getCategories() {
        Set<Category> categories= new HashSet<>();
        categoryRepository.findAll().iterator().forEachRemaining(categories::add);
        return categories;
    }

    @Override
    public Set<CategoryCommand> getCategoryCommands() {
        Set<CategoryCommand> categoryCommands= new HashSet<>();
        categoryRepository.findAll().iterator().forEachRemaining(category -> categoryCommands.add(categoryToCategoryCommand.convert(category)));
        return categoryCommands;
    }
}
