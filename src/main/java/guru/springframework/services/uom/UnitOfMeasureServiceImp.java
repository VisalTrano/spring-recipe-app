package guru.springframework.services.uom;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.converters.uom.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.domian.UnitOfMeasure;
import guru.springframework.repositories.UnitOfMeasureRepository;
import guru.springframework.repositories.reactvice.UnitOfMeasureReactiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.HashSet;
import java.util.Set;

@Service
public class UnitOfMeasureServiceImp implements UnitOfMeasureService {
    @Autowired
    private UnitOfMeasureRepository unitOfMeasureRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;
    private final UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

    public UnitOfMeasureServiceImp(UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository, UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
        this.unitOfMeasureReactiveRepository = unitOfMeasureReactiveRepository;
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

    @Override
    public Flux<UnitOfMeasureCommand> listAllUoms() {
        return unitOfMeasureReactiveRepository.findAll()
                .map(unitOfMeasureToUnitOfMeasureCommand::convert);
    }
}
