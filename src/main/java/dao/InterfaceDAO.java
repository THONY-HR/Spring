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
    public <T extends MapTable> T findById(int key, String tableName, Class<T> classy) throws Exception;
    public <T extends MapTable> T findById(int id, String tableName, Class<T> classy,Connexion c) throws Exception;
    public <T extends MapTable> Boolean delete(String key, String tableName) throws Exception;
    public <T extends MapTable> Boolean delete(String key, String tableName,Connexion con) throws Exception;
    public <T extends MapTable> List<T> pagination(String tableName, int index, int nbr, Class<T> classy) throws Exception;
    public <T extends MapTable> List<T> pagination(String tableName, int index, int nbr, Class<T> classy, Connexion con) throws Exception;
}
