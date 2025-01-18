package controller;

import db.DBConnection;
import model.Customer;
import model.Item;
import model.Order;
import model.OrderDetail;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

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

    public static boolean updateItemStock(ArrayList<OrderDetail> details ){
        details.forEach(detail -> {
            try {
                updateItemStock(detail.getItemcode(),detail.getQty());
            } catch (ClassNotFoundException e) {

                throw new RuntimeException(e);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        return true;
    }
    public static boolean updateItemStock(String itemCode, int qty) throws ClassNotFoundException, SQLException{
        PreparedStatement stm = DBConnection.getDBconnection().getConnection().prepareStatement("Update Item set QtyOnHand=QtyOnHand-? where code=?");
        stm.setObject(1,qty);
        stm.setObject(2, itemCode);
        return stm.executeUpdate()>0;
    }
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
    public  static Item serachById(String ID) throws SQLException {
        String SQL = "Select * From item WHERE code="+"'"+ID+"'";
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery(SQL);
        while (rst.next()) {
            String code =(rst.getString(1));
            String desc = rst.getString(2);
            double unitPrice = rst.getDouble(3);
            int QOH= rst.getInt(4);

            return new Item(code,desc,unitPrice, QOH);
        }
        return null;
    }
    public static List<Item> getItems() throws SQLException {
        List<Item> itemList=new ArrayList<>();
        String SQL = "Select * From Item";
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery(SQL);
        while (rst.next()) {
            String id = rst.getString("code");
            String description = rst.getString("description");
            double unitPrice = rst.getDouble("unitPrice");
            int QtyOnHand = rst.getInt("qtyOnHand");
            itemList.add(new Item(id,description,unitPrice,QtyOnHand));
        }
        return itemList;

    }

}
