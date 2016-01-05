package messages.models;

import Models.Subject;
import utils.socket.IMessage;

import java.util.ArrayList;

public class EuSubjectsResponse implements IMessage {
    private final ArrayList<Subject> subjects;

    public EuSubjectsResponse(ArrayList<Subject> subjects) {
        this.subjects = subjects;
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }
}
