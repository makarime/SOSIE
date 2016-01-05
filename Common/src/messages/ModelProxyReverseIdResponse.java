package messages;

import utils.socket.IMessage;

import java.util.List;

public class ModelProxyReverseIdResponse implements IMessage {
    private final List<?> value;

    public ModelProxyReverseIdResponse(List<?> value) {
        this.value = value;
    }

    public List<?> getValue() {
        return value;
    }
}
