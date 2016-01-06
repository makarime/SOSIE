package Models;


import Models.proxy.IProxy;

import java.util.Hashtable;

public class DataBase {
    public static IProxy currentProxy = null;
    public static Hashtable<Integer, User> userHashtable = new Hashtable<>();
    public static Hashtable<Integer, Student> studentHashtable = new Hashtable<>();
    public static Hashtable<Integer, Professor> professorHashtable = new Hashtable<>();
    public static Hashtable<Integer, Class> classHashtable = new Hashtable<>();
    public static Hashtable<Integer, ClassBatch> classBatchHashtable = new Hashtable<>();
    public static Hashtable<Integer, Eu> euHashtable = new Hashtable<>();
    public static Hashtable<Integer, Subject> subjectHashtable = new Hashtable<>();

    public static void flushHastables() {
        DataBase.userHashtable = new Hashtable<>();
        DataBase.studentHashtable = new Hashtable<>();
        DataBase.professorHashtable = new Hashtable<>();
        DataBase.classHashtable = new Hashtable<>();
        DataBase.classBatchHashtable = new Hashtable<>();
        DataBase.euHashtable = new Hashtable<>();
        DataBase.subjectHashtable = new Hashtable<>();
    }
}
