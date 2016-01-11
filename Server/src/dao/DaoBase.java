package dao;

import java.sql.*;
import java.util.ArrayList;

public abstract class DaoBase<T> {

    private static Connection getJdbc() {
        return DaoConnection.getInstance().jdbc;
    }
    private final String tableName;

    public DaoBase(String tableName) {
        this.tableName = tableName;
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

    public boolean updateRow(String column, String newValue) {
        final String sql = String.format("UPDATE %s SET %s = ?", tableName, column);
        try(PreparedStatement f =  getJdbc().prepareStatement(sql)){
            f.setString(1, newValue);
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
