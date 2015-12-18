package messages;

import Models.Student;
import utils.socket.IMessage;

import java.util.ArrayList;

@Deprecated
public class ClassStudentResponse implements IMessage{
    public final ArrayList<Student> students;

    public ClassStudentResponse(ArrayList<Student> students) {
        this.students = students;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }
}
