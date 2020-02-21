package guru.springframework.repositories;

import guru.springframework.domian.Ingredient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient,String> {
}
