package controller.Order;

import controller.Item.ItemController;
import controller.OrderDetail.OrderDetailController;
import db.DBConnection;
import model.Order;

import java.sql.*;

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
    public boolean addOrder(Order order) throws SQLException, ClassNotFoundException {

        String SQL = "Insert into Orders(id,date,customerId) Values(?,?,?)";
        PreparedStatement stm = connection.prepareStatement(SQL);

        stm.setObject(1, order.getId());
        stm.setObject(2, order.getDate());
        stm.setObject(3, order.getCustomer_id());
    try{
        connection.setAutoCommit(false);
        System.out.println("set auto commit false");
        boolean isAddOrder=stm.executeUpdate()>0;
        if(isAddOrder){
            System.out.println("Order Added "+order.getId());
            boolean IsAddOrderDetail= OrderDetailController.addOrderDetail(order.getOrderDetailList());
              if(IsAddOrderDetail){
                  System.out.println("OrderDetail Added!");
                  boolean IsUpdateStock= ItemController.updateItemStock(order.getOrderDetailList());
                  if (IsUpdateStock){
                      System.out.println("stock updated");
                      connection.commit();
                      System.out.println("commited");
                      return true;

                  }
              }

        }



    }finally {
        connection.rollback();
        System.out.println("rolled back");
            connection.setAutoCommit(true);
        System.out.println(" finally autocommit true");
        }
        return false;
    }

}

