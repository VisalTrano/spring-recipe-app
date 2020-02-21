package guru.springframework.services.uom;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.converters.uom.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.domian.UnitOfMeasure;
import guru.springframework.repositories.UnitOfMeasureRepository;
import guru.springframework.repositories.reactvice.UnitOfMeasureReactiveRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static reactor.core.publisher.Mono.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class UnitOfMeasureServiceImpTest {
    UnitOfMeasureToUnitOfMeasureCommand convert = new UnitOfMeasureToUnitOfMeasureCommand();
    UnitOfMeasureService service;

    @Mock
    UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new UnitOfMeasureServiceImp(unitOfMeasureReactiveRepository, convert);
    }

    @Test
    public void testGetAllUoms() throws Exception {
        //given
        Set<UnitOfMeasure> unitOfMeasures = new HashSet<>();
        UnitOfMeasure uom1 = new UnitOfMeasure();
        uom1.setId("1");
        unitOfMeasures.add(uom1);

        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom2.setId("2");
        unitOfMeasures.add(uom2);
        Flux<UnitOfMeasure> unitOfMeasureFlux = Flux.just(uom1, uom2);

        given(unitOfMeasureReactiveRepository.findAll()).willReturn(unitOfMeasureFlux);

        //when
        List<UnitOfMeasureCommand> commands = service.listAllUoms().collectList().block();

        //then
        assertEquals(2, commands.size());
        verify(unitOfMeasureReactiveRepository, times(1)).findAll();
    }
}