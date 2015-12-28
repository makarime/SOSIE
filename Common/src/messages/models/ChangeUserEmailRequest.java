package messages.models;


import utils.socket.IMessage;

public class ChangeUserEmailRequest implements IMessage {
    private final int userId;
    private final String email;

    public ChangeUserEmailRequest(int userId, String email) {
        this.userId = userId;
        this.email = email;
    }

    public int getUserId() {
        return this.userId;
    }

    public String getEmail() {
        return this.email;
    }
}
