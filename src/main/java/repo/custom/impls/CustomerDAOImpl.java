package repo.custom.impls;

import db.DBConnection;
import dto.CustomerDTO;
import entity.CustomerEntity;
import repo.custom.CustomerDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    Connection connection;
    public  CustomerDAOImpl(){


        {
            try {
                connection = DBConnection.getDBconnection().getConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Override
    public boolean add(CustomerEntity customer) throws SQLException {

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


    @Override
    public boolean Update(CustomerEntity customer, String s) {
        return false;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public List<CustomerEntity> getAll() {
        return List.of();
    }
}
