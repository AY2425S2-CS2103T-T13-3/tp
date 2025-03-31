package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Candidate's team in RecruitIntel.
 * Guarantees: immutable; is valid as declared in {@link #isValidTeam(String)}
 */
public class Team {

    public static final String MESSAGE_CONSTRAINTS =
            "Team name should:\n" +
                    "- Start with a letter or number\n" +
                    "- Can contain letters, numbers, spaces\n" +
                    "- Can contain common special characters: . , ( ) / - & + @\n" +
                    "- Cannot be blank";

    /*
     * Team name validation rules:
     * 1. Must start with alphanumeric character
     * 2. Can contain alphanumeric characters, spaces, and common special characters
     * 3. Must not be blank
     */
    public static final String VALIDATION_REGEX = "^[\\p{Alnum}][\\p{Alnum} .,()@/\\-&+]*$";

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
            return false;
        }
    }

    /**
     * Returns a specific error message identifying which characters are invalid in the team name.
     */
    private static String getValidationErrorMessage(String team) {
        if (team.isEmpty()) {
            return "Team name cannot be empty.\n\n" + MESSAGE_CONSTRAINTS;
        }

        if (!Character.isLetterOrDigit(team.charAt(0))) {
            return "Team name must start with a letter or number, found: '"
                    + team.charAt(0) + "'\n\n" + MESSAGE_CONSTRAINTS;
        }

        StringBuilder invalidChars = new StringBuilder();
        for (char c : team.toCharArray()) {
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
