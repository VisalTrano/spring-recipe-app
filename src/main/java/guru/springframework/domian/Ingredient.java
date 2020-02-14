package guru.springframework.domian;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@EqualsAndHashCode(exclude = {"recipe"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String description;
    private BigDecimal amount;

    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure unitOfMeasure;

    @ManyToOne
    private Recipe recipe;

    public UnitOfMeasure getUnitOfMeasureClone() {
        return this.unitOfMeasure;
    }

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure unitOfMeasure) {
        this.description = description;
        this.amount = amount;
        this.unitOfMeasure = unitOfMeasure;

    }

}
