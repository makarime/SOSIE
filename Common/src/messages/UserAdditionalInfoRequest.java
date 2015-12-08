package messages;


import utils.socket.IMessage;

public class UserAdditionalInfoRequest implements IMessage {
    private final int userID;

    public UserAdditionalInfoRequest(int userID) {
        this.userID = userID;
    }

    public int getUserId() {
        return this.userID;
    }
}
