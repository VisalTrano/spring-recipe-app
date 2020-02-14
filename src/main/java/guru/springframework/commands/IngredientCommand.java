package guru.springframework.commands;

import guru.springframework.domian.UnitOfMeasure;
import lombok.*;

import java.math.BigDecimal;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IngredientCommand {
    private Long id;
    private Long recipeId;
    private String description;
    private BigDecimal amount;
    private UnitOfMeasureCommand unitOfMeasure;

}
