package guru.springframework.domian;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Set;

/**
 * Created by jt on 6/13/17.
 */
@Getter
@Setter
@Document
public class Category {
    @Id
    private String id;
    private String description;
    private Set<Recipe> recipes;
}
