package service.custom.impl;

import com.google.inject.Inject;
import dto.CustomerDTO;
import entity.CustomerEntity;
import org.modelmapper.ModelMapper;
import repo.DAOFactory;
import repo.custom.CustomerDAO;
import service.custom.CustomerBO;
import util.DAOType;

import java.sql.SQLException;

public class CustomerBoImpl implements CustomerBO {
    @Inject
    CustomerDAO customerDAO=DAOFactory.getInstance().getCustomerDAOImpl(DAOType.CUSTOMER);
    @Override
    public boolean addCustomer(CustomerDTO customer) throws SQLException {
        System.out.println(customer);
        CustomerEntity map = new ModelMapper().map(customer, CustomerEntity.class);
        return customerDAO.add(map)?true:false;

    }
}
