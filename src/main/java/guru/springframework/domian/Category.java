package guru.springframework.domian;

import lombok.*;


import javax.persistence.*;
import java.util.Set;

@EqualsAndHashCode(exclude = {"recipes"})
@Getter
@Setter
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String description;
    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;

}
