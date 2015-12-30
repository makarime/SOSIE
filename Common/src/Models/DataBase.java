package Models;


import Models.proxy.IProxy;

import java.util.Hashtable;

public class DataBase {
    public static IProxy currentProxy = null;
    public static Hashtable<Integer, User> userHashtable = new Hashtable<>();
    public static Hashtable<Integer, Student> studentHashtable = new Hashtable<>();
    public static Hashtable<Integer, Professor> professorHashtable = new Hashtable<>();
    public static Hashtable<Integer, Class> classeHashtable = new Hashtable<>();
    public static Hashtable<Integer, ClassBatch> classBatchHashtable = new Hashtable<>();
}
