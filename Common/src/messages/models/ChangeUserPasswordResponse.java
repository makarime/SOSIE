package messages.models;


import utils.socket.IMessage;

public class ChangeUserPasswordResponse implements IMessage {
    private final boolean result;

    public ChangeUserPasswordResponse(boolean result) {
        this.result = result;
    }

    public boolean getResult() {
        return this.result;
    }
}
