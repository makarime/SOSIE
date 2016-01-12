package dao;

import Models.ClassBatch;
import Models.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository extends DaoBase<Student> {

    private static final String SElECTREQUEST = "SELECT * FROM Eleves " +
            "  INNER JOIN Utilisateurs ON Utilisateurs.IdUtilisateur = Eleves.EleveId ";
    public static final String TABLENAME = "Eleves"; //TODO Erreur heritage

    private static StudentRepository instance = null;
    public static StudentRepository getInstance() {
        if(instance == null)
            instance = new StudentRepository();
        return instance;
    }

    public StudentRepository() {
        super(TABLENAME);
    }

    public static Student getById(int id) {
        ArrayList<Student> ar = getInstance().select(SElECTREQUEST + "WHERE EleveId = " + id);
        return ar.size() > 0 ? ar.get(0) : null;
    }

    public static List<Student> getByReverseId(Class<?> clazz, int id) {
        if (clazz == ClassBatch.class) {
            System.out.println("toto");
            return getInstance().select("SELECT *\n" +
                    "FROM (\n" +
                    "\tSELECT Eleves.*\n" +
                    "\tFROM Eleves, EleveCP, ClassePromo\n" +
                    "    WHERE Eleves.EleveId = EleveCP.EleveId\n" +
                    "\tAND EleveCP.ClassPromoId = ClassePromo.PromoClassId\n" +
                    "\tAND ClassePromo.PromoClassId = " + id + "\n" +
                    ") as Tmp\n" +
                    "INNER JOIN Utilisateurs ON Utilisateurs.IdUtilisateur = Tmp.EleveId");
        }

        return null;
    }

    public static boolean update(String columnName, String newValue) {
        return getInstance().updateRow(columnName, newValue);
    }

    @Override
    public Student dataToClass(ResultSet rs) throws SQLException {
        return new Student(
                rs.getInt(UserRepository.Columns.ID),
                rs.getString(UserRepository.Columns.FIRSTNAME),
                rs.getString(UserRepository.Columns.LASTNAME),
                rs.getString(UserRepository.Columns.EMAIL)
        );
    }
}
