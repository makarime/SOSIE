package messages;

import Models.User;
import utils.socket.IMessage;

public class UserResponse implements IMessage {
    private User user;

    public UserResponse(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
