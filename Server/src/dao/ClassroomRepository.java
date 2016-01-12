package dao;

import Models.Classroom;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClassroomRepository extends DaoBase<Classroom>{

    private static final String SElECTREQUEST = "SELECT * FROM Salle ";
    public static final String TABLENAME = "Salle";
    public interface Columns{
        String ID           = "idSalle";
        String NUMBER       = "Numero";
        String CAPACITY     = "Capacite";
        String COMPUTER     = "Pc";
        String PROJECTOR    = "Videoprojecteur";
        String DIGITALPANE  = "TabNum";
        String DISABLEPERS  = "Handi";
    }

    private static ClassroomRepository instance = null;
    public static ClassroomRepository getInstance() {
        if(instance == null)
            instance = new ClassroomRepository();
        return instance;
    }

    public ClassroomRepository() {
        super(TABLENAME, Columns.ID);
    }

    public static Classroom getById(int id) {
        ArrayList<Classroom> ar = getInstance().select(SElECTREQUEST + "WHERE idSalle = " + id);
        return ar.size() > 0 ? ar.get(0) : null;
    }

    public static boolean update(Integer id, String columnName, String newValue) {
        return getInstance().updateRow(columnName, newValue, id);
    }

    @Override
    public Classroom dataToClass(ResultSet rs) throws SQLException {
        return new Classroom(
                    rs.getInt(Columns.ID),
                    rs.getInt(Columns.NUMBER),
                    rs.getInt(Columns.CAPACITY),
                    rs.getBoolean(Columns.COMPUTER),
                    rs.getBoolean(Columns.PROJECTOR),
                    rs.getBoolean(Columns.DIGITALPANE),
                    rs.getBoolean(Columns.DISABLEPERS));
    }

}
