package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection connection;
    private static DBConnection DBconnection;
    private DBConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection= DriverManager.getConnection("jdbc:mysql://localhost/ThogaKade", "root", "dinneth123");
    }
    public static DBConnection getDBconnection () throws SQLException,ClassNotFoundException {
        if(DBconnection==null){
            DBconnection=new DBConnection();
            return DBconnection;
        }else{
            return DBconnection;
        }

    }
    public static Connection getConnection(){
        return connection;
    }
}
