package messages.models;

import Models.Subject;
import utils.socket.IMessage;

import java.util.List;

public class EuSubjectsResponse implements IMessage {
    private final List<Subject> subjects;

    public EuSubjectsResponse(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }
}
