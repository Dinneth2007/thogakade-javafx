package controller;

import db.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class OrderController {
    Connection connection;
    OrderController() throws SQLException, ClassNotFoundException {
        connection=DBConnection.getDBconnection().getConnection();
    }

}
