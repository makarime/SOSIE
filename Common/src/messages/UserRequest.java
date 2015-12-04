package messages;

import utils.socket.IMessage;

public class UserRequest implements IMessage {
    private final int id;

    public UserRequest(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
