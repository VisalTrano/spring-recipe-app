package guru.springframework.converters.uom;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domian.UnitOfMeasure;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {
    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasure unitOfMeasure) {
        if (unitOfMeasure == null)
            return null;
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand().builder()
                .id(unitOfMeasure.getId())
                .description(unitOfMeasure.getDescription())
                .build();
        return unitOfMeasureCommand;
    }
}
