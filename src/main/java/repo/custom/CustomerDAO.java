package repo.custom;

import dto.CustomerDTO;
import entity.CustomerEntity;
import repo.CRUDRepo;

public interface CustomerDAO extends CRUDRepo<CustomerEntity,String> {
}
