package messages.models;


import utils.socket.IMessage;

public class ChangeUserPasswordRequest implements IMessage {
    private final int userId;
    private final String userPassword;

    public ChangeUserPasswordRequest(int userId, String userPassword) {
        this.userId = userId;
        this.userPassword = userPassword;
    }

    public int getUserId() {
        return this.userId;
    }

    public String getUserPassword() {
        return this.userPassword;
    }
}
