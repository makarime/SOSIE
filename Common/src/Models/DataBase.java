package Models;


import Models.proxy.IProxy;

import java.util.Hashtable;

public class DataBase {
    public static IProxy currentProxy = null;
    public static Hashtable<Integer, User> users = new Hashtable<>();
    public static Hashtable<Integer, Class> classes = new Hashtable<>();
}
