package Models;

import java.io.Serializable;
import java.util.Date;

public class Course implements Serializable, IEntity {
    private int courseId;
    private int subjectId;
    private int classBatchId;
    private int professorId;
    private int classroomId;
    private Date date;

    public Course(int courseId, int subjectId, int classBatchId, int professorId, int classroomId, Date date) {
        this.courseId = courseId;
        this.subjectId = subjectId;
        this.classBatchId = classBatchId;
        this.professorId = professorId;
        this.classroomId = classroomId;
        this.date = date;
    }

    @Override
    public int getPrimaryKey() {
        return courseId;
    }

}
