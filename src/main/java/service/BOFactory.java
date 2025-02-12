package service;

import service.custom.OrderBO;
import service.custom.impl.CustomerBoImpl;
import service.custom.impl.ItemBoImpl;
import service.custom.impl.OrderBoImpl;
import util.BOType;

public class BOFactory {
    private static BOFactory factory;
    private BOFactory(){}
    public static BOFactory getInstance(){
        return factory==null?new BOFactory():factory;
    }
    public <T extends SuperBO> T getCustomerBoImpl(BOType type){
        switch (type){
            case CUSTOMER : return (T) new CustomerBoImpl();
            case ITEM:return (T) new ItemBoImpl();
            case ORDER:return (T) new OrderBoImpl();

        }
        return null;
    }
}
