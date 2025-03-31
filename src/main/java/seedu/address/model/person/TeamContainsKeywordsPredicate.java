package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Team} matches any of the keywords given.
 */
public class TeamContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public TeamContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword ->
                        person.getTeam().value.toLowerCase().contains(keyword.toLowerCase()));
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
