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

    public Subject getSubject() {
        return DataBase.currentProxy.loadObjectById(Subject.class, subjectId);
    }

    public ClassBatch getClassBatch() {
        return DataBase.currentProxy.loadObjectById(ClassBatch.class, classBatchId);
    }

    public Professor getProfessor() {
        return DataBase.currentProxy.loadObjectById(Professor.class, professorId);
    }

    public Classroom getClassroom() {
        return DataBase.currentProxy.loadObjectById(Classroom.class, classroomId);
    }

    public Date getDate() {
        return date;
    }

    public int getCourseId() {
        return courseId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public int getClassBatchId() {
        return classBatchId;
    }

    public int getProfessorId() {
        return professorId;
    }

    public int getClassroomId() {
        return classroomId;
    }

}
