package messages.models;

import utils.socket.IMessage;

@Deprecated
public class SubjectEuRequest implements IMessage {
    private final int euId;

    public SubjectEuRequest(int euId) {
        this.euId = euId;
    }

    public int getEuId() {
        return euId;
    }

}
