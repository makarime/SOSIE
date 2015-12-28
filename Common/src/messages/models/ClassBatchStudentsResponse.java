package messages.models;


import Models.Student;
import utils.socket.IMessage;

import java.util.ArrayList;

public class ClassBatchStudentsResponse implements IMessage {
    private final ArrayList<Student> students;

    public ClassBatchStudentsResponse(ArrayList<Student> students) {
        this.students = students;
    }

    public ArrayList<Student> getStudents() {
        return this.students;
    }
}
