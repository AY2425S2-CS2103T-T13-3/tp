package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class StartTimeTest {

    @Test
    public void constructor_validInput_success() {
        StartTime st = new StartTime("2025-04-01 10:15");
        assertEquals("2025-04-01 10:15", st.value);
        assertEquals(LocalDateTime.of(2025, 4, 1, 10, 15), st.getParsedStartTime());
    }

    @Test
    public void constructor_nullInput_success() {
        StartTime st = new StartTime(null);
        assertEquals("", st.value);
        assertNull(st.getParsedStartTime());
    }

    @Test
    public void constructor_invalidFormat_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new StartTime("2025/04/01 10:15"));
    }

    @Test
    public void constructor_minuteNotMultipleOfFive_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new StartTime("2025-04-01 10:07"));
    }

    @Test
    public void isValidStartTime_variousCases() {
        assertTrue(StartTime.isValidStartTime("2025-12-31 23:55"));
        assertFalse(StartTime.isValidStartTime("2025-04-01 10-15")); // wrong format
        assertFalse(StartTime.isValidStartTime("2025-04-01 10:03")); // invalid minute
        assertTrue(StartTime.isValidStartTime(null));
        assertTrue(StartTime.isValidStartTime(""));
    }

}
