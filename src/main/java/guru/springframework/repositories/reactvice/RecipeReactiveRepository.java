package guru.springframework.repositories.reactvice;

import guru.springframework.domian.Recipe;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface RecipeReactiveRepository extends ReactiveMongoRepository<Recipe,String> {
}
