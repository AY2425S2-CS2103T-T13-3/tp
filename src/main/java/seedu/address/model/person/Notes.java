package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents interviewer notes for a Candidate in RecruitIntel.
 * Guarantees: immutable; is valid as declared in {@link #isValidNotes(String)}
 */
public class Notes {

    public static final String MESSAGE_CONSTRAINTS =
            "Notes cannot exceed 450 characters in length.";

    public static final int MAX_LENGTH = 450;

    /*
     * Notes can be any string up to MAX_LENGTH characters
     */
    public static final String VALIDATION_REGEX = "^.{0," + MAX_LENGTH + "}$";

    public final String value;

    /**
     * Constructs a {@code Notes}.
     *
     * @param notes A valid notes string.
     */
    public Notes(String notes) {
        requireNonNull(notes);
        checkArgument(isValidNotes(notes), MESSAGE_CONSTRAINTS);
        value = notes;
    }

    /**
     * Returns true if a given string is valid for notes.
     * A valid notes string must not exceed MAX_LENGTH characters.
     */
    public static boolean isValidNotes(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Notes)) {
            return false;
        }

        Notes otherNotes = (Notes) other;
        return value.equals(otherNotes.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
