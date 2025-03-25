package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting RecruitIntel as requested ...";

    public static final String MESSAGE_UNDO_NOT_AVAILABLE = "Cannot undo exit command!";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }

    @Override
    public CommandResult undo(Model model) {
        return new CommandResult(MESSAGE_UNDO_NOT_AVAILABLE);
    }
}
