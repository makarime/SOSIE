package messages.models;

import Models.Classroom;
import Models.Professor;
import utils.socket.IMessage;

public class CourseProfessorResponse implements IMessage {
    private final Professor professor;

    public CourseProfessorResponse(Professor professor) {
        this.professor = professor;
    }

    public Professor getProfessor() {
        return professor;
    }
}
