package guru.springframework.converters.uom;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domian.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitOfMeasureCommandToUnitOfMeasureTest {

    static final String DESCRIPTION = "description";
    static final String LONG_VALUE =  "1";
    UnitOfMeasureCommandToUnitOfMeasure converter;

    @BeforeEach
    void setUp() {
        converter = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Test
    void convertNull() {
        assertNull(converter.convert(null));
    }

    @Test
    void convert() {
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand().builder().id(LONG_VALUE).description(DESCRIPTION).build();

        UnitOfMeasure unitOfMeasure = converter.convert(unitOfMeasureCommand);

        assertNotNull(unitOfMeasure);
        assertEquals(unitOfMeasureCommand.getId(), unitOfMeasure.getId());
        assertEquals(unitOfMeasure.getDescription(), unitOfMeasure.getDescription());
    }
}