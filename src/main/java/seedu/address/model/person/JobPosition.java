package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Candidate's job position in RecruitIntel.
 * Guarantees: immutable; is valid as declared in {@link #isValidJobPosition(String)}
 */
public class JobPosition {

    public static final String MESSAGE_CONSTRAINTS =
            "Job position should:\n" +
                    "- Start with a letter or number\n" +
                    "- Can contain letters, numbers, spaces\n" +
                    "- Can contain common special characters: . , ( ) / - & + @\n" +
                    "- Cannot be blank";

    /*
     * Job position validation rules:
     * 1. Must start with alphanumeric character
     * 2. Can contain alphanumeric characters, spaces, and common special characters
     * 3. Must not be blank
     */
    public static final String VALIDATION_REGEX = "^[\\p{Alnum}][\\p{Alnum} .,()@/\\-&+]*$";

    public final String value;

    /**
     * Constructs a {@code JobPosition}.
     *
     * @param jobPosition A valid job position.
     */
    public JobPosition(String jobPosition) {
        requireNonNull(jobPosition);
        String validationError = getValidationErrorMessage(jobPosition);
        if (!validationError.equals(MESSAGE_CONSTRAINTS)) {
            throw new IllegalArgumentException(validationError);
        }
        value = jobPosition;
    }

    /**
     * Returns true if a given string is a valid job position.
     */
    public static boolean isValidJobPosition(String test) {
        try {
            new JobPosition(test);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Returns a specific error message identifying which characters are invalid in the job position.
     */
    private static String getValidationErrorMessage(String jobPosition) {
        if (jobPosition.isEmpty()) {
            return "Job position cannot be empty.\n\n" + MESSAGE_CONSTRAINTS;
        }

        if (!Character.isLetterOrDigit(jobPosition.charAt(0))) {
            return "Job position must start with a letter or number, found: '"
                    + jobPosition.charAt(0) + "'\n\n" + MESSAGE_CONSTRAINTS;
        }

        StringBuilder invalidChars = new StringBuilder();
        for (char c : jobPosition.toCharArray()) {
            if (!Character.isLetterOrDigit(c) && !". ,()@/-&+ ".contains(String.valueOf(c))) {
                invalidChars.append("'").append(c).append("' ");
            }
        }

        if (invalidChars.length() > 0) {
            return "Found invalid character(s): " + invalidChars.toString().trim() + "\n\n" + MESSAGE_CONSTRAINTS;
        }

        return MESSAGE_CONSTRAINTS;
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
