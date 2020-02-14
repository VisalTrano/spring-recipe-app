package guru.springframework.services.uom;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.converters.uom.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.domian.UnitOfMeasure;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UnitOfMeasureServiceImp implements UnitOfMeasureService {
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    public UnitOfMeasureServiceImp(UnitOfMeasureRepository unitOfMeasureRepository, UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
    }

    @Override
    public Set<UnitOfMeasure> getUnitOfMeasures() {
        Set<UnitOfMeasure> unitOfMeasures = new HashSet<>();
        unitOfMeasureRepository.findAll().iterator().forEachRemaining(unitOfMeasures::add);
        return unitOfMeasures;
    }

    @Override
    public Set<UnitOfMeasureCommand> getUnitOfMeasureCommands() {
        Set<UnitOfMeasureCommand> unitOfMeasureCommands = new HashSet<>();
        unitOfMeasureRepository.findAll().iterator().forEachRemaining(unitOfMeasure -> unitOfMeasureCommands.add(unitOfMeasureToUnitOfMeasureCommand.convert(unitOfMeasure)));
        return null;
    }
}
