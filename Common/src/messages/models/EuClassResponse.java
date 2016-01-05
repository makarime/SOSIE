package messages.models;

import Models.Class;
import utils.socket.IMessage;

@Deprecated
public class EuClassResponse implements IMessage {
    private final Class clazz;

    public EuClassResponse(Class clazz) {
        this.clazz = clazz;
    }

    public Class getClazz() {
        return clazz;
    }

}
