package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for UndoCommand.
 */
public class UndoCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_undoDeleteCommand_success() {
        Person firstPerson = model.getFilteredPersonList().get(6);

        DeleteCommand deleteCommand = new DeleteCommand(firstPerson);
        try {
            deleteCommand.execute(model);
        } catch (CommandException e) {
            e.printStackTrace();
        }

        UndoCommand undoCommand = new UndoCommand();

        try {
            undoCommand.execute(model);
        } catch (CommandException e) {
            e.printStackTrace();
        }

        assertEquals(model.getFilteredPersonList().size(), 7);
    }
}
