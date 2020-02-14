package guru.springframework.converters.uom;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domian.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitOfMeasureToUnitOfMeasureCommandTest {
    static final String DESCRIPTION = "description";
    static final Long LONG_VALUE = 1L;
    UnitOfMeasureToUnitOfMeasureCommand converter;
    @BeforeEach
    void setUp() {
        converter= new UnitOfMeasureToUnitOfMeasureCommand();
    }
    @Test
    void convertNull() {
        assertNull(converter.convert(null));
    }
    @Test
    void convert() {
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(LONG_VALUE);
        unitOfMeasure.setDescription(DESCRIPTION);

        UnitOfMeasureCommand unitOfMeasureCommand = converter.convert(unitOfMeasure);

        assertNotNull(unitOfMeasureCommand);
        assertEquals(unitOfMeasureCommand.getId(), unitOfMeasure.getId());
        assertEquals(unitOfMeasure.getDescription(), unitOfMeasure.getDescription());
    }
}