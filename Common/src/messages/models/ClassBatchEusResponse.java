package messages.models;


import Models.Eu;
import utils.socket.IMessage;

import java.util.ArrayList;

@Deprecated
public class ClassBatchEusResponse implements IMessage {
    private final ArrayList<Eu> eus;

    public ClassBatchEusResponse(ArrayList<Eu> eus) {
        this.eus = eus;
    }

    public ArrayList<Eu> getEus() {
        return this.eus;
    }
}
