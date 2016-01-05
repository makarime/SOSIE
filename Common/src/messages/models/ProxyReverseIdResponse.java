package messages.models;

import utils.socket.IMessage;

import java.util.List;

public class ProxyReverseIdResponse<T> implements IMessage {
    private final List<T> value;

    public ProxyReverseIdResponse(List<T> value) {
        this.value = value;
    }

    public List<T> getValue() {
        return value;
    }
}
