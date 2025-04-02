package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;

/**
 * Represents a Candidate's job position in RecruitIntel.
 * Guarantees: immutable; is valid as declared in {@link #isValidJobPosition(String)}
 */
public class JobPosition {
    public static final String MESSAGE_CONSTRAINTS =
            "Job position should:\n"
                    + "- Start with a letter or number\n"
                    + "- Can contain letters, numbers, spaces\n"
                    + "- Can contain common special characters: . , ( ) / - & + @\n"
                    + "- Cannot be blank";

    /*
     * Job position validation rules:
     * 1. Must start with alphanumeric character: ^[\\p{Alnum}]
     * 2. Can contain:
     *    - alphanumeric characters: \\p{Alnum}
     *    - spaces and special chars: [ .,()@/\\-&+]
     * 3. Can have any number of these characters after the first: *
     */
    public static final String VALIDATION_REGEX = "^[\\p{Alnum}][\\p{Alnum} .,()@/\\-&+]*$";

    private static final Logger logger = LogsCenter.getLogger(JobPosition.class);
    private static final String MESSAGE_EMPTY = "Job position cannot be empty.\n\n";
    private static final String MESSAGE_INVALID_START = "Job position must start "
            + "with a letter or number, found: '%s'\n\n";
    private static final String MESSAGE_INVALID_CHARS = "Found invalid character(s): %s\n\n";
    private static final String ALLOWED_SPECIAL_CHARS = ". ,()@/-&+ ";

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
            logger.warning("Invalid job position attempted: " + jobPosition);
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
            logger.fine("Job position validation failed: " + test);
            return false;
        }
    }

    /**
     * Returns a specific error message identifying which characters are invalid in the job position.
     */
    private static String getValidationErrorMessage(String jobPosition) {
        if (jobPosition.isEmpty()) {
            logger.fine("Empty job position detected");
            return MESSAGE_EMPTY + MESSAGE_CONSTRAINTS;
        }

        if (!Character.isLetterOrDigit(jobPosition.charAt(0))) {
            logger.fine("Invalid starting character in job position: " + jobPosition.charAt(0));
            return String.format(MESSAGE_INVALID_START, jobPosition.charAt(0)) + MESSAGE_CONSTRAINTS;
        }

        String invalidChars = findInvalidCharacters(jobPosition);
        if (!invalidChars.isEmpty()) {
            logger.fine("Invalid characters found in job position: " + invalidChars);
            return String.format(MESSAGE_INVALID_CHARS, invalidChars) + MESSAGE_CONSTRAINTS;
        }

        return MESSAGE_CONSTRAINTS;
    }

    /**
     * Returns a string containing all invalid characters found in the job position.
     */
    private static String findInvalidCharacters(String jobPosition) {
        StringBuilder invalidChars = new StringBuilder();
        for (char c : jobPosition.toCharArray()) {
            if (!Character.isLetterOrDigit(c) && !ALLOWED_SPECIAL_CHARS.contains(String.valueOf(c))) {
                invalidChars.append("'").append(c).append("' ");
            }
        }
        return invalidChars.toString().trim();
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
