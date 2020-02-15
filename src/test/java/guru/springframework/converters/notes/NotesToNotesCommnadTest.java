package guru.springframework.converters.notes;

import guru.springframework.commands.NotesCommand;
import guru.springframework.domian.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotesToNotesCommnadTest {
    static final String RECIPE_NOTES = "recipe notes";
    static final String LONG_VALUE = "1";
    NotesToNotesCommnad converter;

    @BeforeEach
    void setUp() {
        converter = new NotesToNotesCommnad();
    }

    @Test
    void convertNull() {
        assertNull(converter.convert(null));
    }

    @Test
    void convert() {
        Notes notes = new Notes();
        notes.setId(LONG_VALUE);
        notes.setRecipeNotes(RECIPE_NOTES);

        NotesCommand notesCommand = converter.convert(notes);
        assertNotNull(notesCommand);
        assertEquals(notes.getRecipeNotes(), notesCommand.getRecipeNotes());
        assertEquals(notes.getId(), notesCommand.getId());
    }
}