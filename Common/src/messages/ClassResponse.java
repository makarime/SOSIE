package messages;

import utils.socket.IMessage;

public class ClassResponse implements IMessage {
    private final Class c;

    public ClassResponse(Class c) {
        this.c = c;
    }

    public Class getStudentClass() {
        return this.c;
    }
}
