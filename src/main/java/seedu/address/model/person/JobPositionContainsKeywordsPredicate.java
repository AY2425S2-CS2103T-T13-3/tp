package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code JobPosition} matches any of the keywords given.
 */
public class JobPositionContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public JobPositionContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword ->
                        person.getJobPosition().value.toLowerCase().contains(keyword.toLowerCase()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof JobPositionContainsKeywordsPredicate)) {
            return false;
        }

        JobPositionContainsKeywordsPredicate otherPredicate = (JobPositionContainsKeywordsPredicate) other;
        return keywords.equals(otherPredicate.keywords);
    }
}
