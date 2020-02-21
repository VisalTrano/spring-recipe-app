package guru.springframework.repositories;

import guru.springframework.domian.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe,String> {
}
