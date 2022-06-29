package blueprint;

import java.sql.ResultSet;

public abstract class Crud_Abstract{
    public abstract void update(String statament,Object a,String entityName);
    public abstract void delete(String statament , Object a);
    public abstract void add(String statement,Object a,String entityName);
    public abstract ResultSet getAll(String statament);
}
