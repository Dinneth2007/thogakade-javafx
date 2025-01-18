package controller;

import db.DBConnection;
import model.Customer;
import model.Item;
import model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemController {
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

    ;



    public static boolean addItem(Item item) throws SQLException {

        String SQL = "Insert into Item(code,description,unitPrice,qtyOnHand) Values(?,?,?,?)";
        PreparedStatement stm = connection.prepareStatement(SQL);

        stm.setObject(1, item.getItemcode());
        stm.setObject(2, item.getDesc());
        stm.setObject(3, item.getUnitPrice());
        stm.setObject(4, item.getQtyOnHand());


        int res=stm.executeUpdate();
        if(res>0){
            return true;
        }else{
            return false;
        }

    }
//    public static List<Item> getItems() throws SQLException {
//        List<Item> itemList=new ArrayList<>();
//        String SQL = "Select * From Item";
//        Statement stm = connection.createStatement();
//        ResultSet rst = stm.executeQuery(SQL);
//        while (rst.next()) {
//            String id = rst.getString("code");
//            String description = rst.getString("description");
//            double unitPrice = rst.getDouble("unitPrice");
//            int QtyOnHand = rst.getInt("qtyOnHand");
//            itemList.add(new Item(id,description,unitPrice,QtyOnHand));
//        }
//        return itemList;
//
//    }

}
