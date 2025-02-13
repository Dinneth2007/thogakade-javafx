package config;

import com.google.inject.AbstractModule;
import repo.custom.CustomerDAO;
import repo.custom.impls.CustomerDAOImpl;
import service.custom.CustomerBO;
import service.custom.impl.CustomerBoImpl;

public class AppModule extends AbstractModule {
    @Override
    protected void configure(){
        bind(CustomerBO.class).to( CustomerBoImpl.class);
        bind(CustomerDAO.class).to(CustomerDAOImpl.class);
    }
}
