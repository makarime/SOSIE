package utils.socket.message;

import utils.socket.IMessage;

public class StringMessage implements IMessage {
    private String value;

    public StringMessage() {}

    public StringMessage(String value) {
        this.value = value;
    }

    public void set(String value) {
        this.value = value;
    }

    public String get() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
