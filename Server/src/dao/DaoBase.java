package dao;

import java.sql.*;
import java.util.ArrayList;

public abstract class DaoBase<T> {

    private static Connection getJdbc() {
        return DaoConnection.getInstance().jdbc;
    }
    private final String tableName;
    private final String primaryKey;

    public DaoBase(String tableName, String primaryKey) {
        this.tableName = tableName;
        this.primaryKey = primaryKey;
    }

    public ArrayList<T> selectAll() {
        return select("SELECT * FROM " + tableName);
    }

    public ArrayList<T> select(String sql) {
        try(Statement stmt = getJdbc().createStatement() ;
            ResultSet rs = stmt.executeQuery(sql)) {
            return parseResultset(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<T> select(String sql, Object... args) {
        try(PreparedStatement f =  getJdbc().prepareStatement(sql)){
            for(int i = 0; i < args.length; i++) {
                if(args[i] instanceof Integer) {
                    f.setInt(i+1, (int)args[i]);
                } else {
                    f.setString(i+1, args[i].toString());
                }
            }
            try(ResultSet rs = f.executeQuery()) {
                return parseResultset(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean updateRow(String column, String newValue, Integer id) {
        final String sql = String.format("UPDATE %s SET %s = ?", tableName, column) + (id != null ? " " + primaryKey + " = ?" : null);
        try(PreparedStatement f =  getJdbc().prepareStatement(sql)){
            f.setString(1, newValue);
            if(id != null)
                f.setInt(2, id);
            f.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<T> parseResultset(ResultSet rs) throws SQLException {
        ArrayList<T> rslt = new ArrayList<>();
        while(rs.next())
            rslt.add(dataToClass(rs));
        return rslt;
    }

    public abstract T dataToClass(ResultSet rs) throws SQLException;

}
