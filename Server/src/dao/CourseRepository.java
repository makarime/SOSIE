package dao;

import Models.*;

import java.lang.Class;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CourseRepository extends DaoBase<Course> {

    private static final String SElECTREQUEST = "SELECT * FROM Cours ";
    public static final String TABLENAME = "Cours";
    public interface Columns{
        String ID           = "CoursId";
        String SUBJECTID    = "MatiereId";
        String CLASSBATCHID = "PromoClassId";
        String PROFESSORID  = "EnseignantId";
        String CLASSROOMID  = "SalleId";
        String DATE         = "date";
        String DURATION     = "Duree";
    }

    private static CourseRepository instance = null;
    public static CourseRepository getInstance() {
        if(instance == null)
            instance = new CourseRepository();
        return instance;
    }

    public CourseRepository() {
        super(TABLENAME, Columns.ID);
    }

    public static Course getById(int id) {
        ArrayList<Course> ar = getInstance().select(SElECTREQUEST + "WHERE CoursId = " + id);
        return ar.size() > 0 ? ar.get(0) : null;
    }

    public static List<Course> getByReverseId(Class<?> clazz, int id) {
        String column = null;
        if(clazz == Classroom.class)        column = "SalleId";
        if(clazz == Professor.class)        column = "EnseignantId";
        if(clazz == ClassBatch.class)       column = "PromoClassId";
        if(clazz == Subject.class)          column = "MatiereId";
        if(column == null) {
            System.err.println(String.format("%s.getByReverseId: Class not found : %s",
                    getInstance().getClass().getSimpleName(), clazz.getClass().getSimpleName()));
            return null;
        }
        return getInstance().select(SElECTREQUEST + "WHERE " + column + " = " + id);
    }

    public static boolean update(Integer id, String columnName, String newValue) {
        return getInstance().updateRow(columnName, newValue, id);
    }

    public static List<Course> getCoursesOnDateByCP(int ClassPromoId, Date date) {
        //TODO A Refactoriser et voir si c'est réellement ici qu'on met la requete
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return getInstance().select(SElECTREQUEST + "WHERE PromoClassId = ? AND date(Date) = ?", ClassPromoId, dateFormat.format(date));
    }
    public static List<Course> getCoursesOnDateByProfessor(int ProfessorId, Date date) {
        //TODO A Refactoriser et voir si c'est réellement ici qu'on met la requete
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return getInstance().select(SElECTREQUEST + "WHERE EnseignantId = ? AND date(Date) = ?", ProfessorId, dateFormat.format(date));
    }

    @Override
    public Course dataToClass(ResultSet rs) throws SQLException {
        return new Course(
                rs.getInt(Columns.ID),
                rs.getInt(Columns.SUBJECTID),
                rs.getInt(Columns.CLASSBATCHID),
                rs.getInt(Columns.PROFESSORID),
                rs.getInt(Columns.CLASSROOMID),
                new Date(rs.getTimestamp(Columns.DATE).getTime()),
                rs.getInt(Columns.DURATION)
        );
    }
}
