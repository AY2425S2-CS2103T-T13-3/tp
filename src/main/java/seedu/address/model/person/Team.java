package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;

/**
 * Represents a Candidate's team in RecruitIntel.
 * Guarantees: immutable; is valid as declared in {@link #isValidTeam(String)}
 */
public class Team {
    public static final String MESSAGE_CONSTRAINTS =
            "Team name should:\n"
                    + "- Start with a letter or number\n"
                    + "- Can contain letters, numbers, spaces\n"
                    + "- Can contain common special characters: . , ( ) / - & + @\n"
                    + "- Cannot be blank";

    /*
     * Team name validation rules:
     * 1. Must start with alphanumeric character: ^[\\p{Alnum}]
     * 2. Can contain:
     *    - alphanumeric characters: \\p{Alnum}
     *    - spaces and special chars: [ .,()@/\\-&+]
     * 3. Can have any number of these characters after the first: *
     */
    public static final String VALIDATION_REGEX = "^[\\p{Alnum}][\\p{Alnum} .,()@/\\-&+]*$";

    private static final Logger logger = LogsCenter.getLogger(Team.class);
    private static final String MESSAGE_EMPTY = "Team name cannot be empty.\n\n";
    private static final String MESSAGE_INVALID_START = "Team name must start with a letter or number, found: '%s'\n\n";
    private static final String MESSAGE_INVALID_CHARS = "Found invalid character(s): %s\n\n";
    private static final String ALLOWED_SPECIAL_CHARS = ". ,()@/-&+ ";

    public final String value;

    /**
     * Constructs a {@code Team}.
     *
     * @param team A valid team.
     */
    public Team(String team) {
        requireNonNull(team);
        String validationError = getValidationErrorMessage(team);
        if (!validationError.equals(MESSAGE_CONSTRAINTS)) {
            logger.warning("Invalid team name attempted: " + team);
            throw new IllegalArgumentException(validationError);
        }
        value = team;
    }

    /**
     * Returns true if a given string is a valid team.
     */
    public static boolean isValidTeam(String test) {
        try {
            new Team(test);
            return true;
        } catch (IllegalArgumentException e) {
            logger.fine("Team name validation failed: " + test);
            return false;
        }
    }

    /**
     * Returns a specific error message identifying which characters are invalid in the team name.
     */
    private static String getValidationErrorMessage(String team) {
        if (team.isEmpty()) {
            logger.fine("Empty team name detected");
            return MESSAGE_EMPTY + MESSAGE_CONSTRAINTS;
        }

        if (!Character.isLetterOrDigit(team.charAt(0))) {
            logger.fine("Invalid starting character in team name: " + team.charAt(0));
            return String.format(MESSAGE_INVALID_START, team.charAt(0)) + MESSAGE_CONSTRAINTS;
        }

        String invalidChars = findInvalidCharacters(team);
        if (!invalidChars.isEmpty()) {
            logger.fine("Invalid characters found in team name: " + invalidChars);
            return String.format(MESSAGE_INVALID_CHARS, invalidChars) + MESSAGE_CONSTRAINTS;
        }

        return MESSAGE_CONSTRAINTS;
    }

    /**
     * Returns a string containing all invalid characters found in the team name.
     */
    private static String findInvalidCharacters(String team) {
        StringBuilder invalidChars = new StringBuilder();
        for (char c : team.toCharArray()) {
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

        if (!(other instanceof Team)) {
            return false;
        }

        Team otherTeam = (Team) other;
        return value.equals(otherTeam.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
