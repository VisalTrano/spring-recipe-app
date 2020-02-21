package guru.springframework.services.uom;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domian.UnitOfMeasure;
import reactor.core.publisher.Flux;

import java.util.Set;

public interface UnitOfMeasureService {
    Set<UnitOfMeasure> getUnitOfMeasures();

    Set<UnitOfMeasureCommand> getUnitOfMeasureCommands();

    Flux<UnitOfMeasureCommand> listAllUoms();
}
