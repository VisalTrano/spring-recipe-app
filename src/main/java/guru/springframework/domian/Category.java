package guru.springframework.domian;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

/**
 * Created by jt on 6/13/17.
 */
@Getter
@Setter
@Document(collation = "categories")
public class Category {
    private String id;
    private String description;
    private Set<Recipe> recipes;
}
