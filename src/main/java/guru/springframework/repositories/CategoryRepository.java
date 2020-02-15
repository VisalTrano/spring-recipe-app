package guru.springframework.repositories;

import guru.springframework.domian.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends MongoRepository<Category, Long> {
    Optional<Category> findByDescription(String description);
}
