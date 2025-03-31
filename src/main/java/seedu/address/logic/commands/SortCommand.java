package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.Model;

/**
 * Sorts all candidates in RecruitIntel by their start time in ascending order.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all candidates by their start time "
            + "in ascending order and displays them as a list.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SORT_SUCCESS = "Sorted all candidates by Start Time.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        // Use the natural ordering defined by StartTime's compareTo method
        model.sortFilteredPersonList((p1, p2) -> p1.getStartTime().compareTo(p2.getStartTime()));

        return new CommandResult(MESSAGE_SORT_SUCCESS);
    }

    @Override
    public CommandResult undo(Model model) {
        return new CommandResult("SortCommand should not appear in the undo stack");
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
