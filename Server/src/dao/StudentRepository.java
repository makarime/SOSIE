package dao;

import Models.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentRepository extends DaoBase<Student> {

    private static StudentRepository instance = null;
    public static StudentRepository getInstance() {
        if(instance == null)
            instance = new StudentRepository();
        return instance;
    }

    private static final String SElECTREQUEST = "SELECT * FROM Eleves " +
                                                "  INNER JOIN Utilisateurs ON Utilisateurs.IdUtilisateur = Eleves.EleveId ";

    public static Student getById(int id) {
        ArrayList<Student> ar = getInstance().select(SElECTREQUEST + "WHERE EleveId = " + id);
        return ar.size() > 0 ? ar.get(0) : null;
    }

    @Override
    public Student dataToClass(ResultSet rs) throws SQLException {
        return new Student(
                rs.getInt("IdUtilisateur"),
                rs.getString("FirstName"),
                rs.getString("LastName"),
                rs.getInt("CurrentCP")
        );
    }
}
