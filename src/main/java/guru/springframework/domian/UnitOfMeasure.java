package guru.springframework.domian;

import lombok.*;

import javax.persistence.*;
@Getter
@Setter
@Entity
public class UnitOfMeasure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    @Lob
    private String description;

}
