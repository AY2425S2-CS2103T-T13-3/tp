package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Classifies and lists all candidates in RecruitIntel based on specified criteria (tags, team, or job position).
 * Multiple criteria can be specified (using AND logic).
 * Keyword matching is case-insensitive.
 */
public class ClassifyCommand extends Command {

    public static final String COMMAND_WORD = "classify";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all candidates matching ALL specified criteria "
            + "and displays them as a list with index numbers.\n"
            + "Parameters: [t/TAG] [tm/TEAM] [j/JOB_POSITION]\n"
            + "Examples:\n"
            + "  " + COMMAND_WORD + " t/python\n"
            + "  " + COMMAND_WORD + " tm/Engineering\n"
            + "  " + COMMAND_WORD + " j/Software Engineer\n"
            + "  " + COMMAND_WORD + " tm/iOS Development j/Product Manager t/swift";

    private final List<Predicate<Person>> predicates;

    public ClassifyCommand(List<Predicate<Person>> predicates) {
        this.predicates = predicates;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        // Combine all predicates with AND logic
        Predicate<Person> combinedPredicate = person ->
                predicates.stream().allMatch(pred -> pred.test(person));

        model.updateFilteredPersonList(combinedPredicate);
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

        if (!(other instanceof ClassifyCommand)) {
            return false;
        }

        ClassifyCommand otherClassifyCommand = (ClassifyCommand) other;
        return predicates.equals(otherClassifyCommand.predicates);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicates", predicates)
                .toString();
    }
}
