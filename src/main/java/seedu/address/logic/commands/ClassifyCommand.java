package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.TagsContainsKeywordsPredicate;

/**
 * Classifies and lists all candidates in RecruitIntel whose any of the tags contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class ClassifyCommand extends Command {

    public static final String COMMAND_WORD = "classify";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all candidates whose tags contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " python";

    private final TagsContainsKeywordsPredicate predicate;

    public ClassifyCommand(TagsContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public CommandResult undo(Model model) {
        return new CommandResult("ClassifyCommand should not appear in the undo stack");
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClassifyCommand)) {
            return false;
        }

        ClassifyCommand otherClassifyCommand = (ClassifyCommand) other;
        return predicate.equals(otherClassifyCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
