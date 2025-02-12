package service.custom;

import dto.CustomerDTO;
import service.SuperBO;

import java.sql.SQLException;

public interface CustomerBO extends SuperBO {
    public boolean addCustomer(CustomerDTO customer) throws SQLException;

}
