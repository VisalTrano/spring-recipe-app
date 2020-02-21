package guru.springframework.repositories.reactvice;

import guru.springframework.domian.UnitOfMeasure;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UnitOfMeasureReactiveRepository extends ReactiveMongoRepository<UnitOfMeasure,String> {
}
