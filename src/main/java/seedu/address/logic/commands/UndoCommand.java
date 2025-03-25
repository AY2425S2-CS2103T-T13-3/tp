package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Undo the most recent command that modifies the data.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Undo the most recent command that modifies the data.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Undo successful!";

    public static final String MESSAGE_UNDO_NOT_AVAILABLE = "No command to undo!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (lastCommand == null) {
            throw new CommandException(Messages.MESSAGE_NO_COMMAND_TO_UNDO);
        }
        return lastCommand.undo(model);
    }

    @Override
    public CommandResult undo(Model model) throws CommandException {
        throw new CommandException(MESSAGE_UNDO_NOT_AVAILABLE);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .toString();
    }

}
