package dao;

import Models.ClassBatch;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClassBatchRepository extends DaoBase<ClassBatch> {

    private static ClassBatchRepository instance = null;
    public static ClassBatchRepository getInstance() {
        if(instance == null)
            instance = new ClassBatchRepository();
        return instance;
    }

    private static final String SElECTREQUEST = "SELECT * FROM ClassePromo ";

    public static ClassBatch getById(int id) {
        ArrayList<ClassBatch> ar = getInstance().select(SElECTREQUEST + "WHERE PromoClassId = " + id);
        return ar.size() > 0 ? ar.get(0) : null;
    }

    @Override
    public ClassBatch dataToClass(ResultSet rs) throws SQLException {
        return new ClassBatch(
                rs.getInt("PromoClassId"),
                rs.getInt("ClasseId"),
                rs.getInt("PromotionId"),
                rs.getInt("ResponsableId")
        );
    }
}
