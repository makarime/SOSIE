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
    private int duree;

    public Course(int courseId, int subjectId, int classBatchId, int professorId, int classroomId, Date date, int duree) {
        this.courseId = courseId;
        this.subjectId = subjectId;
        this.classBatchId = classBatchId;
        this.professorId = professorId;
        this.classroomId = classroomId;
        this.date = date;
        this.duree = duree;
    }

    @Override
    public int getPrimaryKey() {
        return courseId;
    }

    public Subject getSubject() {
        return DataBaseEnv.currentProxy.loadObjectById(Subject.class, subjectId);
    }

    public ClassBatch getClassBatch() {
        return DataBaseEnv.currentProxy.loadObjectById(ClassBatch.class, classBatchId);
    }

    public Professor getProfessor() {
        return DataBaseEnv.currentProxy.loadObjectById(Professor.class, professorId);
    }

    public Classroom getClassroom() {
        return DataBaseEnv.currentProxy.loadObjectById(Classroom.class, classroomId);
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

    public int getDuree() {
        return this.duree;
    }

    @Override
    public String toString() {
        return this.getSubject().getName() + "\n" + this.date.toString();
    }
}
