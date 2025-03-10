package seedu.address.model.person;

/**
 * Represents a Person's friend level in the address book.
 */
public enum FriendLevel {
    ACQUAINTANCE,
    FRIEND,
    CLOSE_FRIEND;

    public static final String MESSAGE_CONSTRAINTS = "Friend level should be one of: ACQUAINTANCE, FRIEND, CLOSE_FRIEND";

    /**
     * Returns true if a given string is a valid friend level.
     */
    public static boolean isValidFriendLevel(String test) {
        try {
            valueOf(test.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
} 