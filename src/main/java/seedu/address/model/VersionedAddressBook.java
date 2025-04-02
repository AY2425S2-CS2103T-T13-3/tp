package seedu.address.model;

import java.util.ArrayList;
import java.util.List;

public class VersionedAddressBook extends AddressBook {
    private final List<ReadOnlyAddressBook> addressBookStateList;
    private int currentStatePointer;

    public VersionedAddressBook(ReadOnlyAddressBook initialState) {
        super(initialState);
        addressBookStateList = new ArrayList<>();
        addressBookStateList.add(new AddressBook(initialState));
        currentStatePointer = 0;
    }

    public void commit() {
        removeStatesAfterCurrentPointer();
        addressBookStateList.add(new AddressBook(this));
        currentStatePointer++;
    }

    private void removeStatesAfterCurrentPointer() {
        addressBookStateList.subList(currentStatePointer + 1, addressBookStateList.size()).clear();
    }

    public ReadOnlyAddressBook undo() throws NoUndoableStateException {
        if (currentStatePointer == 0) {
            throw new NoUndoableStateException();
        }
        currentStatePointer--;
        return addressBookStateList.get(currentStatePointer);
    }

    public void redo() throws NoRedoableStateException {
        if (currentStatePointer >= addressBookStateList.size() - 1) {
            throw new NoRedoableStateException();
        }
        currentStatePointer++;
        resetData(addressBookStateList.get(currentStatePointer));
    }

    public static class NoUndoableStateException extends Exception {}
    public static class NoRedoableStateException extends Exception {}
}
