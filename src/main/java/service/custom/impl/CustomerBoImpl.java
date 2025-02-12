package service.custom.impl;

import model.Customer;
import repo.DAOFactory;
import repo.custom.CustomerDAO;
import service.custom.CustomerBO;
import util.DAOType;

import java.sql.SQLException;

public class CustomerBoImpl implements CustomerBO {
    CustomerDAO customerDAO= DAOFactory.getInstance().getCustomerDAOImpl(DAOType.CUSTOMER);
    @Override
    public boolean addCustomer(Customer customer) throws SQLException {
        return customerDAO.add(customer)?true:false;

    }
}
