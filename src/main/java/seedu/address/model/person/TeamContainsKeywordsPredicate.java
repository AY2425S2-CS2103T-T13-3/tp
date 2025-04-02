package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Team} matches any of the keywords given.
 * Keywords are matched case-insensitively against any part of the team name.
 */
public class TeamContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    /**
     * Constructs a new {@code TeamContainsKeywordsPredicate}.
     * @param keywords the list of keywords to match against team names
     */
    public TeamContainsKeywordsPredicate(List<String> keywords) {
        requireNonNull(keywords);
        keywords.forEach(keyword -> requireNonNull(keyword, "Keywords cannot contain null elements"));
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        requireNonNull(person);
        assert person.getTeam() != null : "Person's team cannot be null";

        String teamText = person.getTeam().toString().toLowerCase();
        assert !teamText.isEmpty() : "Team text cannot be empty after conversion";

        return keywords.stream()
                .map(String::toLowerCase)
                .anyMatch(teamText::contains);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TeamContainsKeywordsPredicate)) {
            return false;
        }

        TeamContainsKeywordsPredicate otherPredicate = (TeamContainsKeywordsPredicate) other;
        return keywords.equals(otherPredicate.keywords);
    }
}
