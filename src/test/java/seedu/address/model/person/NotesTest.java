package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NotesTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Notes(null));
    }

    @Test
    public void isValidNotes() {
        // null notes
        assertThrows(NullPointerException.class, () -> Notes.isValidNotes(null));

        // valid notes
        assertTrue(Notes.isValidNotes("")); // empty string
        assertTrue(Notes.isValidNotes(" ")); // spaces only
        assertTrue(Notes.isValidNotes("Good candidate")); // normal notes
        assertTrue(Notes.isValidNotes("Very detailed notes with multiple sentences. Shows promise in leadership."));
        assertTrue(Notes.isValidNotes("a".repeat(Notes.MAX_LENGTH))); // exactly maximum characters

        // invalid notes
        assertFalse(Notes.isValidNotes("a".repeat(Notes.MAX_LENGTH + 1))); // exceeds maximum length
    }

    @Test
    public void equals() {
        Notes notes = new Notes("Test notes");

        // same object -> returns true
        assertTrue(notes.equals(notes));

        // same value -> returns true
        Notes sameNotes = new Notes("Test notes");
        assertTrue(notes.equals(sameNotes));

        // null -> returns false
        assertFalse(notes.equals(null));

        // different type -> returns false
        assertFalse(notes.equals(5));

        // different value -> returns false
        Notes differentNotes = new Notes("Different notes");
        assertFalse(notes.equals(differentNotes));
    }
}
