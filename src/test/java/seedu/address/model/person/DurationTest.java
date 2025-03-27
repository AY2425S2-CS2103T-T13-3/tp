package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DurationTest {

    @Test
    public void constructor_validInput_success() {
        Duration d = new Duration("30");
        assertEquals("30", d.value);
        assertEquals(30, d.getDurationInMinutes());
    }

    @Test
    public void constructor_blankInput_success() {
        Duration d = new Duration("");
        assertEquals("", d.value);
        assertEquals(0, d.getDurationInMinutes());
    }

    @Test
    public void constructor_invalidFormat_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new Duration("abc"));
        assertThrows(IllegalArgumentException.class, () -> new Duration("17")); // not multiple of 5
        assertThrows(IllegalArgumentException.class, () -> new Duration("-10"));
    }

    @Test
    public void isValidDuration_variousCases() {
        assertTrue(Duration.isValidDuration("15"));
        assertTrue(Duration.isValidDuration(""));
        assertFalse(Duration.isValidDuration("abc"));
        assertFalse(Duration.isValidDuration("22"));
        assertFalse(Duration.isValidDuration("-10"));
    }
}
