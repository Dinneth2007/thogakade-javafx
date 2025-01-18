package controller;

import db.DBConnection;
import model.CartTableModel;
import model.Order;

import java.sql.*;
import java.util.ArrayList;

public class OrderController {

    Connection connection;
    OrderController() throws SQLException, ClassNotFoundException {
        connection=DBConnection.getDBconnection().getConnection();

    }

    public  String loadNewOrderId() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDBconnection().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT id FROM orders ORDER BY id DESC LIMIT 1");
        return rst.next() ? rst.getString("id")+1 : "";
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

