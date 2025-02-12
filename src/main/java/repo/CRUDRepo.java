package repo;

import java.sql.SQLException;
import java.util.List;

public interface CRUDRepo <T ,ID> extends SuperDAO{
    public boolean add(T customer) throws SQLException;
    public boolean Update(T customer, ID id);
    public boolean delete(ID id);
    public List<T> getAll();
}
