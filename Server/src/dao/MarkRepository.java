package dao;

import Models.Mark;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MarkRepository extends DaoBase<Mark> {

    private static MarkRepository instance = null;
    public static MarkRepository getInstance() {
        if(instance == null)
            instance = new MarkRepository();
        return instance;
    }

    private static final String SElECTREQUEST = "SELECT * FROM Notes ";

    public static Mark getById(int id) {
        ArrayList<Mark> ar = getInstance().select(SElECTREQUEST + "WHERE NoteId = " + id);
        return ar.size() > 0 ? ar.get(0) : null;
    }

    @Override
    public Mark dataToClass(ResultSet rs) throws SQLException {
        return new Mark(
                rs.getInt("NoteId"),
                rs.getInt("EleveCPId"),
                rs.getInt("MatiereId"),
                rs.getInt("valeur")
        );
    }
}
