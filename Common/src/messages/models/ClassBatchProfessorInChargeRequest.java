package messages.models;


import utils.socket.IMessage;

@Deprecated
public class ClassBatchProfessorInChargeRequest implements IMessage {
    private final int professorInChargeId;

    public ClassBatchProfessorInChargeRequest(int professorInChargeId) {
        this.professorInChargeId = professorInChargeId;
    }

    public int getProfessorId() {
        return this.professorInChargeId;
    }
}
