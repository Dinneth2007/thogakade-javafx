package repo;

import repo.custom.impls.CustomerDAOImpl;
import service.BOFactory;
import service.SuperBO;
import service.custom.impl.CustomerBoImpl;
import service.custom.impl.ItemBoImpl;
import service.custom.impl.OrderBoImpl;
import util.BOType;
import util.DAOType;

public class DAOFactory {
    private static DAOFactory factory;
    private DAOFactory(){}
    public static DAOFactory getInstance(){
        return factory==null?new DAOFactory():factory;
    }
    public <T extends SuperDAO> T getCustomerDAOImpl(DAOType type){
        switch (type){
            case CUSTOMER : return (T) new CustomerDAOImpl();


        }
        return null;
    }

}
