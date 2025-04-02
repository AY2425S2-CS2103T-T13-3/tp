package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code JobPosition} matches any of the keywords given.
 * Keywords are matched case-insensitively against any part of the job position.
 */
public class JobPositionContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    /**
     * Constructs a new {@code JobPositionContainsKeywordsPredicate}.
     * @param keywords the list of keywords to match against job positions
     */
    public JobPositionContainsKeywordsPredicate(List<String> keywords) {
        requireNonNull(keywords);
        keywords.forEach(keyword -> requireNonNull(keyword, "Keywords cannot contain null elements"));
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        requireNonNull(person);
        assert person.getJobPosition() != null : "Person's job position cannot be null";

        String jobPositionText = person.getJobPosition().toString().toLowerCase();
        assert !jobPositionText.isEmpty() : "Job position text cannot be empty after conversion";

        return keywords.stream()
                .map(String::toLowerCase)
                .anyMatch(jobPositionText::contains);
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
