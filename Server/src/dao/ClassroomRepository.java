package dao;

import Models.Classroom;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClassroomRepository extends DaoBase<Classroom>{

    private static ClassroomRepository instance = null;
    public static ClassroomRepository getInstance() {
        if(instance == null)
            instance = new ClassroomRepository();
        return instance;
    }

    private static final String SElECTREQUEST = "SELECT * FROM Salle ";

    public static Classroom getById(int id) {
        ArrayList<Classroom> ar = getInstance().select(SElECTREQUEST + "WHERE idSalle = " + id);
        return ar.size() > 0 ? ar.get(0) : null;
    }

    @Override
    public Classroom dataToClass(ResultSet rs) throws SQLException {
        return new Classroom(
                    rs.getInt("idSalle"),
                    rs.getInt("Numero"),
                    rs.getInt("Capacite"),
                    rs.getBoolean("Pc"),
                    rs.getBoolean("Videoprojecteur"),
                    rs.getBoolean("TabNum"),
                    rs.getBoolean("Handi"));
    }

}
