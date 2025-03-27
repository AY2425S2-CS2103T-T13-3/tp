package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;

/**
 * Clears the RecruitIntel.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "RecruitIntel has been cleared!";
    public static final String MESSAGE_UNDO_SUCCESS = "RecruitIntel has been restored!";
    private ReadOnlyAddressBook currentAddressBook;
    private ReadOnlyAddressBook previousAddressBook;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        currentAddressBook = new AddressBook(model.getAddressBook());
        previousAddressBook = new AddressBook();
        model.setAddressBook(previousAddressBook);
        lastCommand = this;
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public CommandResult undo(Model model) {
        model.setAddressBook(currentAddressBook);
        ReadOnlyAddressBook tempt = currentAddressBook;
        currentAddressBook = previousAddressBook;
        previousAddressBook = tempt;
        lastCommand = this;
        return new CommandResult(MESSAGE_UNDO_SUCCESS);
    }
}
