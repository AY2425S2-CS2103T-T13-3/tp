package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Candidate's job position in RecruitIntel.
 * Guarantees: immutable; is valid as declared in {@link #isValidJobPosition(String)}
 */
public class JobPosition {

    public static final String MESSAGE_CONSTRAINTS =
            "Job positions can contain letters, numbers, spaces, and special characters like /, -, &";

    /*
     * Job position can contain alphanumeric characters, spaces, and common special characters.
     * It must not be blank and must not start with whitespace.
     */
    public static final String VALIDATION_REGEX = "^[\\p{Alnum}][\\p{Alnum} /\\-&]*$";

    public final String value;

    /**
     * Constructs a {@code JobPosition}.
     *
     * @param jobPosition A valid job position.
     */
    public JobPosition(String jobPosition) {
        requireNonNull(jobPosition);
        checkArgument(isValidJobPosition(jobPosition), MESSAGE_CONSTRAINTS);
        value = jobPosition;
    }

    /**
     * Returns true if a given string is a valid job position.
     */
    public static boolean isValidJobPosition(String test) {
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

        if (!(other instanceof JobPosition)) {
            return false;
        }

        JobPosition otherJobPosition = (JobPosition) other;
        return value.equals(otherJobPosition.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
