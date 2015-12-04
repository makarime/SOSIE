package messages;

import utils.socket.IMessage;

public class LoginResponse implements IMessage {
    public final boolean success;

    public LoginResponse(boolean success) {
        this.success = success;
    }

    public boolean getSuccess() {
        return success;
    }
}
