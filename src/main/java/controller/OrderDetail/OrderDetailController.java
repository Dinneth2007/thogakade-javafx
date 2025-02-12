package controller.OrderDetail;

import db.DBConnection;
import model.OrderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailController {
    private static Connection connection;

    static {
        try {
            connection = DBConnection.getDBconnection().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean addOrderDetail(List<OrderDetail> orderDetails) throws SQLException {
        for(OrderDetail orderDetail:orderDetails){
            if(!addOrderDetail(orderDetail)){
                return false;
            };
        }
        return true;
    }
    public static boolean addOrderDetail(OrderDetail orderDetails) throws SQLException {
        String SQL = "Insert into OrderDetail Values(?,?,?,?)";
        PreparedStatement stm = connection.prepareStatement(SQL);

        stm.setObject(1, orderDetails.getOrderid());
        stm.setObject(2, orderDetails.getItemcode());
        stm.setObject(3, orderDetails.getQty());
        stm.setObject(4, orderDetails.getUnitPrice());


        int res=stm.executeUpdate();
        if(res>0){
            return true;
        }else{
            return false;
        }
    }
}
