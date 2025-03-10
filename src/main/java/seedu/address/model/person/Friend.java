package seedu.address.model.person;

public class Friend {
    public enum FriendType {
        FRIEND, CLOSE_FRIEND, ACQUAINTANCE
    }

    private final FriendType friendType;

    public Friend(FriendType friendType) {
        this.friendType = friendType;
    }

    public FriendType getFriendType() {
        return friendType;
    }

    @Override
    public String toString() {
        return friendType.toString();
    }
}
