package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Sorts all candidates in RecruitIntel by their start time in ascending order.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all candidates by their start time "
            + "in ascending order and displays them as a list.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SORT_SUCCESS = "Sorted all candidates by their "
            + "Interviews' Start Time.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortFilteredPersonList(this::compareByStartTime);
        return new CommandResult(MESSAGE_SORT_SUCCESS);
    }

    /**
     * Compares two persons by their interview start time.
     */
    private int compareByStartTime(Person p1, Person p2) {
        return p1.getStartTime().compareTo(p2.getStartTime());
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof SortCommand; // no internal state, all instances are equal
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).toString();
    }

}
