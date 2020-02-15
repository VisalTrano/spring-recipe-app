package guru.springframework.commands;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotesCommand {
    private String id;
    private String recipeNotes;
}
