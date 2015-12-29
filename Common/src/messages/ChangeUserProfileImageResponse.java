package messages;

import utils.socket.IMessage;


public class ChangeUserProfileImageResponse implements IMessage {
    private final boolean result;

    public ChangeUserProfileImageResponse(boolean result) {
        this.result = result;
    }

    public boolean getResult() {
        return result;
    }
}
