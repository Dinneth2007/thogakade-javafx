package controller;

import db.DBConnection;
import model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderController {
    Connection connection;
    OrderController() throws SQLException, ClassNotFoundException {
        connection=DBConnection.getDBconnection().getConnection();
    }
    public boolean addOrder(Order order) throws SQLException {

        String SQL = "Insert into Orders(id,date,customerId) Values(?,?,?)";
        PreparedStatement stm = connection.prepareStatement(SQL);

        stm.setObject(1, order.getId());
        stm.setObject(2, order.getDate());
        stm.setObject(3, order.getCustomer_id());


        int res=stm.executeUpdate();
        if(res>0){
            return true;
        }else{
            return false;
        }

    }
}

