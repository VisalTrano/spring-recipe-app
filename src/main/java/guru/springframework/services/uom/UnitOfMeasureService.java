package guru.springframework.services.uom;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domian.UnitOfMeasure;

import java.util.Set;

public interface UnitOfMeasureService {
    Set<UnitOfMeasure> getUnitOfMeasures();
    Set<UnitOfMeasureCommand> getUnitOfMeasureCommands();
}
