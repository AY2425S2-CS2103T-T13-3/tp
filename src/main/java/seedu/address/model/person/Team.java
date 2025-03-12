package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Candidate's team in RecruitIntel.
 * Guarantees: immutable; is valid as declared in {@link #isValidTeam(String)}
 */
public class Team {

    public static final String MESSAGE_CONSTRAINTS =
            "Team names can contain letters, numbers, spaces, and special characters like /, -, &";

    /*
     * Team name can contain alphanumeric characters, spaces, and common special characters.
     * It must not be blank and must not start with whitespace.
     */
    public static final String VALIDATION_REGEX = "^[\\p{Alnum}][\\p{Alnum} /\\-&]*$";

    public final String value;

    /**
     * Constructs a {@code Team}.
     *
     * @param team A valid team.
     */
    public Team(String team) {
        requireNonNull(team);
        checkArgument(isValidTeam(team), MESSAGE_CONSTRAINTS);
        value = team;
    }

    /**
     * Returns true if a given string is a valid team.
     */
    public static boolean isValidTeam(String test) {
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
