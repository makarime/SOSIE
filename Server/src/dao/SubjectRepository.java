package dao;

import Models.Subject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SubjectRepository extends DaoBase<Subject> {

    private static SubjectRepository instance = null;
    public static SubjectRepository getInstance() {
        if(instance == null)
            instance = new SubjectRepository();
        return instance;
    }

    private static final String SElECTREQUEST = "SELECT * FROM Matiere ";

    public static Subject getById(int id) {
        ArrayList<Subject> ar = getInstance().select(SElECTREQUEST + "WHERE MatiereId = " + id);
        return ar.size() > 0 ? ar.get(0) : null;
    }

    @Override
    public Subject dataToClass(ResultSet rs) throws SQLException {
        return new Subject(
                rs.getInt("MatiereId"),
                rs.getInt("UeId"),
                rs.getString("Nom")
        );
    }
}
