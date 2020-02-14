package guru.springframework.converters.notes;

import guru.springframework.commands.NotesCommand;
import guru.springframework.domian.Notes;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NotesToNotesCommnad implements Converter<Notes, NotesCommand> {
    @Override
    public NotesCommand convert(Notes notes) {
        if (notes == null)
            return null;
        NotesCommand notesCommand = new NotesCommand().builder()
                .id(notes.getId())
                .recipeNotes(notes.getRecipeNotes()).build();
        return notesCommand;
    }
}
