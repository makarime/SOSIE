package messages.models;

import Models.Eu;
import utils.socket.IMessage;

@Deprecated
public class SubjectEuResponse implements IMessage {
    private final Eu eu;

    public SubjectEuResponse(Eu eu) {
        this.eu = eu;
    }

    public Eu getEu() {
        return eu;
    }

}
