package messages;

import Models.User;
import utils.socket.IMessage;

public class LoginResponse implements IMessage {
    public final boolean success;
    public final User user;

    public LoginResponse(boolean success, User user) {
        this.success = success;
        this.user = user;
    }

    public boolean getSuccess() {
        return success;
    }

    public User getUser() {
        return user;
    }
}
