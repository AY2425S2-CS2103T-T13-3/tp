package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Candidate}'s any of the {@code Tag} matches any of the keywords given.
 */
public class TagsContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public TagsContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword ->
                        person.getTags().stream()
                                .anyMatch(tag -> tag.getTagName().toLowerCase().contains(keyword.toLowerCase()))
                );
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TagsContainsKeywordsPredicate)) {
            return false;
        }

        TagsContainsKeywordsPredicate otherTagsContainsKeywordsPredicate = (TagsContainsKeywordsPredicate) other;
        return keywords.equals(otherTagsContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
