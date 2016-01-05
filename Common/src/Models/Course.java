package Models;

import messages.models.*;

import java.util.Date;

public class Course {
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

    public Subject getSubject() {
        return ((CourseSubjectResponse)DataBase.currentProxy.load(new CourseSubjectRequest(subjectId))).getSubject();
    }

    public ClassBatch getClassBatch() {
        return ((CourseClassBatchResponse)DataBase.currentProxy.load(new CourseClassBatchRequest(classBatchId))).getClassBatch();
    }

    public Professor getProfessor() {
        return ((CourseProfessorResponse)DataBase.currentProxy.load(new CourseProfessorRequest(professorId))).getProfessor();
    }

    public Classroom getClassroom() {
        return ((CourseClassroomResponse)DataBase.currentProxy.load(new CourseClassroomRequest(classroomId))).getClassroom();
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
