package guru.springframework.domian;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;


/**
 * Created by jt on 6/13/17.
 */
@Getter
@Setter
public class Notes {
    @Id
    private String id;
    private Recipe recipe;
    private String recipeNotes;
}
