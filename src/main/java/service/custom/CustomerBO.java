package service.custom;

import model.Customer;
import service.SuperBO;

import java.sql.SQLException;

public interface CustomerBO extends SuperBO {
    public boolean addCustomer(Customer customer) throws SQLException;

}
