package messages;


import utils.socket.IMessage;

public class ChangeUserEmailResponse implements IMessage {
    private final boolean result;

    public ChangeUserEmailResponse(boolean result) {
        this.result = result;
    }

    public boolean getResult() {
        return this.result;
    }
}
