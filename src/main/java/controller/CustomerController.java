package controller;

import db.DBConnection;
import model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerController {

    Connection connection;

    {
        try {
            connection = DBConnection.getDBconnection().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean addcustomer(Customer customer) throws SQLException {
        String SQL = "Insert into Customer Values(?,?,?,?)";
        PreparedStatement stm = connection.prepareStatement(SQL);
        stm.setObject(1, customer.getId());
        stm.setObject(2, customer.getName());
        stm.setObject(3, customer.getAddress());
        stm.setObject(4, customer.getSalary());
        int res=stm.executeUpdate();
        if(res>0){
            return true;
        }else{
            return false;
        }

    }
    public boolean deleteCustomer(String id) throws SQLException {
        String SQL = "DELETE FROM Customer WHERE ID=?";
        PreparedStatement stm = connection.prepareStatement(SQL);
        stm.setObject(1,id);

        int res=stm.executeUpdate();


        return res>0?true:false;
    }
    public List<Customer> getCustomers() throws SQLException {
        List<Customer> customerList=new ArrayList<>();
        String SQL = "Select * From Customer";
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery(SQL);
        while (rst.next()) {
            String id = rst.getString("id");
            String name = rst.getString("name");
            String address = rst.getString(3);
            double salary = rst.getDouble("salary");
            customerList.add(new Customer(id,name,address,salary));
        }
        return customerList;
    }
    public  Customer serachById(String ID) throws SQLException {
        String SQL = "Select * From customer WHERE id="+ID;
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery(SQL);
        while (rst.next()) {
            String id =(rst.getString(1));
            String name = rst.getString(2);
            String address = rst.getString(3);
            double salary= rst.getInt(4);

            return new Customer(id,name,address,salary);
        }
        return null;
    }
}
