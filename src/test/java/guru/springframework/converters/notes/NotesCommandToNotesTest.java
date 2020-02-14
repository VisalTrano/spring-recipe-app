package guru.springframework.converters.notes;

import guru.springframework.commands.NotesCommand;
import guru.springframework.converters.notes.NotesCommandToNotes;
import guru.springframework.domian.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotesCommandToNotesTest {

    static final String RECIPE_NOTES = "recipe notes";
    static final Long LONG_VALUE = 1L;
    NotesCommandToNotes converter;

    @BeforeEach
    void setUp() {
        converter = new NotesCommandToNotes();
    }

    @Test
    void convertNull() {
        assertNull(converter.convert(null));
    }

    @Test
    void convert() {
        NotesCommand notesCommand = new NotesCommand().builder().id(LONG_VALUE).recipeNotes(RECIPE_NOTES).build();

        Notes notes=converter.convert(notesCommand);

        assertNotNull(notes);
        assertEquals(notes.getRecipeNotes(),notesCommand.getRecipeNotes());
        assertEquals(notes.getId(),notesCommand.getId());
    }
}