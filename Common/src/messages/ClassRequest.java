package messages;


import utils.socket.IMessage;

public class ClassRequest implements IMessage {
    private final int idClass;

    public ClassRequest(int idClass) {
        this.idClass = idClass;
    }

    public int getIdClass() {
        return this.idClass;
    }
}
