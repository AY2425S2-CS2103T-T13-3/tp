package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;

/**
 * Clears the RecruitIntel.
 */
public class ClearCommand extends Command {
    public static ReadOnlyAddressBook current;
    public static ReadOnlyAddressBook previous;

    public static ReadOnlyAddressBook empty = new AddressBook();

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "RecruitIntel has been cleared!";

    public static final String MESSAGE_UNDO_CLEAR_SUCCESS = "RecruitIntel has been restored!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        previous = model.getAddressBook();
        current = empty;
        model.setAddressBook(current);
        lastCommand = this;
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public CommandResult undo(Model model) {
        model.setAddressBook(previous);
        ReadOnlyAddressBook temp = previous;
        previous = current;
        current = temp;
        lastCommand = this;
        return new CommandResult(MESSAGE_UNDO_CLEAR_SUCCESS);
    }
}
