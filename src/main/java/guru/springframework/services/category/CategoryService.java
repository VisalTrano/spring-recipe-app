package guru.springframework.services.category;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domian.Category;

import java.util.Set;

public interface CategoryService {
    Set<Category> getCategories();
    Set<CategoryCommand> getCategoryCommands();
}
