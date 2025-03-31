package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class SortCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_sortByStartTime_success() {
        expectedModel.sortFilteredPersonList(Comparator.comparing(p -> p.getStartTime()));
        assertCommandSuccess(new SortCommand(), model, SortCommand.MESSAGE_SORT_SUCCESS, expectedModel);
    }

    @Test
    public void execute_sortOnEmptyList_success() {
        Model emptyModel = new ModelManager(new AddressBook(), new UserPrefs());
        Model expectedEmptyModel = new ModelManager(new AddressBook(), new UserPrefs());
        assertCommandSuccess(new SortCommand(), emptyModel, SortCommand.MESSAGE_SORT_SUCCESS, expectedEmptyModel);
    }

    @Test
    public void equals() {
        SortCommand firstSort = new SortCommand();
        SortCommand secondSort = new SortCommand();

        // same object -> returns true
        assertTrue(firstSort.equals(firstSort));

        // different objects, same logic -> returns true
        assertTrue(firstSort.equals(secondSort));

        // null -> returns false
        assertFalse(firstSort.equals(null));

        // different type -> returns false
        assertFalse(firstSort.equals(new ClearCommand()));
    }

    @Test
    public void toStringMethod() {
        SortCommand sortCommand = new SortCommand();
        String expected = SortCommand.class.getCanonicalName() + "{}";
        System.out.println(expected);
        System.out.println(sortCommand);
        assertTrue(sortCommand.toString().startsWith(expected)); // accept formatting extensions
    }
}
