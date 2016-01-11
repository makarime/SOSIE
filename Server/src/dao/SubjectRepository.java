package dao;

import Models.Eu;
import Models.Subject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectRepository extends DaoBase<Subject> {

    private static final String SElECTREQUEST = "SELECT * FROM Matiere ";
    private static final String TABLENAME = "Matiere";

    private static SubjectRepository instance = null;
    public static SubjectRepository getInstance() {
        if(instance == null)
            instance = new SubjectRepository();
        return instance;
    }

    public SubjectRepository() {
        super(TABLENAME);
    }

    public static Subject getById(int id) {
        ArrayList<Subject> ar = getInstance().select(SElECTREQUEST + "WHERE MatiereId = " + id);
        return ar.size() > 0 ? ar.get(0) : null;
    }

    public static List<Subject> getByReverseId(Class<?> clazz, int id) {
        String column = null;
        if(clazz == Eu.class) column = "UeId";
        if(column == null) {
            System.err.println(String.format("%s.getByReverseId: Class not found : %s",
                    getInstance().getClass().getSimpleName(), clazz.getClass().getSimpleName()));
            return null;
        }
        return getInstance().select(SElECTREQUEST + "WHERE " + column + " = " + id);
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
