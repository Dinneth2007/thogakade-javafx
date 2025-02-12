package service.custom.impl;

import dto.CustomerDTO;
import entity.CustomerEntity;
import org.modelmapper.ModelMapper;
import repo.DAOFactory;
import repo.custom.CustomerDAO;
import service.custom.CustomerBO;
import util.DAOType;

import java.sql.SQLException;

public class CustomerBoImpl implements CustomerBO {
    CustomerDAO customerDAO= DAOFactory.getInstance().getCustomerDAOImpl(DAOType.CUSTOMER);
    @Override
    public boolean addCustomer(CustomerDTO customer) throws SQLException {
        CustomerEntity map = new ModelMapper().map(CustomerDTO.class, CustomerEntity.class);
        return customerDAO.add(map)?true:false;

    }
}
