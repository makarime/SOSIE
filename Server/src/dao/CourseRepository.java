package dao;

import Models.Course;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseRepository extends DaoBase<Course> {

    private static CourseRepository instance = null;
    public static CourseRepository getInstance() {
        if(instance == null)
            instance = new CourseRepository();
        return instance;
    }

    private static final String SElECTREQUEST = "SELECT * FROM Cours ";

    public static Course getById(int id) {
        ArrayList<Course> ar = getInstance().select(SElECTREQUEST + "WHERE CoursId = " + id);
        return ar.size() > 0 ? ar.get(0) : null;
    }

    @Override
    public Course dataToClass(ResultSet rs) throws SQLException {
        return new Course(
                rs.getInt("CoursId"),
                rs.getInt("MatiereId"),
                rs.getInt("PromoClassId"),
                rs.getInt("EnseignantId"),
                rs.getInt("SalleId"),
                rs.getDate("date")
        );
    }
}
