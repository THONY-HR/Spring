package dao;
// import java.util.List;

import java.util.List;

import connexion.Connexion;
import connexion.MapTable;
public interface InterfaceDAO {
    public Boolean create(MapTable o) throws Exception;
    public Boolean create(MapTable o,Connexion con) throws Exception;
    public <T extends MapTable> List<T> findAll(String tableName, Class<T> classy) throws Exception;
    public <T extends MapTable> List<T> findAll(String tableName, Class<T> classy,Connexion con) throws Exception;
    public Boolean update(MapTable o) throws Exception;
    public Boolean update(MapTable o,Connexion con) throws Exception;
    // public Object findById() throws Exception;
    // public Boolean delete() throws Exception;
    // public List<Object> pagination(int index,int nbr) throws Exception;
}