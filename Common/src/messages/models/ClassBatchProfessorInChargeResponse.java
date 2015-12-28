package messages.models;


import Models.Professor;
import utils.socket.IMessage;

public class ClassBatchProfessorInChargeResponse implements IMessage {
    private final Professor professor;

    public ClassBatchProfessorInChargeResponse(Professor professor) {
        this.professor = professor;
    }

    public Professor getProfessor() {
        return this.professor;
    }
}
