package guru.springframework.services.recipes;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domian.Recipe;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RecipeService {
    Flux<Recipe> getRecipes();

    Mono<Recipe> findById(String l);

    Mono<RecipeCommand> saveRecipeCommand(RecipeCommand command);

    void deleteById(String id);

    RecipeCommand findCommandById(String anyString);
}
