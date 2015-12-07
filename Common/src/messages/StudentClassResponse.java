package messages;

import utils.socket.IMessage;

public class StudentClassResponse implements IMessage {
    private final Class c;

    public StudentClassResponse(Class c) {
        this.c = c;
    }

    public Class getStudentClass() {
        return this.c;
    }
}
