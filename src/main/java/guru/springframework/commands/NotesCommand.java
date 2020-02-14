package guru.springframework.commands;

import guru.springframework.domian.Recipe;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotesCommand {
    private Long id;
    private String recipeNotes;
}
