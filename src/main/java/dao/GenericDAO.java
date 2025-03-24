package dao;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import connexion.Connexion;
import connexion.MapTable;
public class GenericDAO extends BaseDAO {

    private String createSQLInsertion(String tableName, String[] columnName) {
        StringBuilder column = new StringBuilder();
        StringBuilder valuesNBR = new StringBuilder();
        
        for (int i = 1; i < columnName.length; i++) {
            column.append(columnName[i]);
            valuesNBR.append("?");
            if (i < columnName.length - 1) {
                column.append(", ");
                valuesNBR.append(", ");
            }
        }
        String sql = "INSERT INTO " + tableName + " (" + column + ") VALUES (" + valuesNBR + ")";
        System.out.println(sql);
        return sql;
    }
    
    @Override
    public Boolean create(MapTable o) throws Exception {
        String sql = createSQLInsertion(o.getTableName(), o.getColumnNames());
        Connexion con = super.getCon();
        if(!con.isOpen()){
            con.creeCon();
        }
        try{
            PreparedStatement pstmt = con.getCon().prepareStatement(sql);
            Object[] values = o.getValues();
            for (int i = 1; i < values.length; i++) {
                System.out.println(o.getColumnNames()[i] + ":" + values[i] + "\n");
                pstmt.setObject(i, values[i]);
            }
            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
    
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally{
            con.closeCon();
        }
    }
    
    @Override
    public Boolean create(MapTable o,Connexion c) throws Exception {
        String sql = createSQLInsertion(o.getTableName(), o.getColumnNames());
    
        try{
            Connexion con = c;
            PreparedStatement pstmt = con.getCon().prepareStatement(sql);
            Object[] values = o.getValues();
            for (int i = 1; i < values.length; i++) {
                pstmt.setObject(i, values[i]);
            }
            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
    
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public <T extends MapTable> List<T> findAll(String tableName, Class<T> classy) throws Exception {
        List<T> results = new ArrayList<>();
        Connexion con = super.getCon();
        
        if (!con.isOpen()) {
            con.creeCon();
        }

        try {
            String sql = "SELECT * FROM " + tableName;
            PreparedStatement pstmt = con.getCon().prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                T obj = classy.getDeclaredConstructor().newInstance();
                Map<String, String> columnMapping = obj.getColumnMapping(); // Récupère la correspondance

                ResultSetMetaData meta = rs.getMetaData();
                int columnCount = meta.getColumnCount();

                for (int i = 1; i <= columnCount; i++) {
                    String columnName = meta.getColumnName(i);
                    Object value = rs.getObject(i);
                    if (value instanceof BigDecimal) {
                        value = ((BigDecimal) value).doubleValue(); // Convertit en double
                    }
                    // Vérifie si la colonne doit être convertie en un autre nom de variable
                    if (columnMapping.containsKey(columnName)) {
                        columnName = columnMapping.get(columnName);
                    }

                    // Recherche du champ correspondant dans la classe
                    try {
                        java.lang.reflect.Field field = classy.getDeclaredField(columnName);
                        field.setAccessible(true);
                        field.set(obj, value);
                    } catch (NoSuchFieldException e) {
                        System.out.println("Champ non trouvé : " + columnName);
                    }
                }
                results.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            con.closeCon();
        }
        return results;
    }

    @Override
    public <T extends MapTable> List<T> findAll(String tableName, Class<T> classy,Connexion c) throws Exception {
        List<T> results = new ArrayList<>();
        Connexion con = c;
        
        if (!con.isOpen()) {
            con.creeCon();
        }

        try {
            String sql = "SELECT * FROM " + tableName;
            PreparedStatement pstmt = con.getCon().prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                T obj = classy.getDeclaredConstructor().newInstance();
                Map<String, String> columnMapping = obj.getColumnMapping(); // Récupère la correspondance

                ResultSetMetaData meta = rs.getMetaData();
                int columnCount = meta.getColumnCount();

                for (int i = 1; i <= columnCount; i++) {
                    String columnName = meta.getColumnName(i);
                    Object value = rs.getObject(i);
                    if (value instanceof BigDecimal) {
                        value = ((BigDecimal) value).doubleValue(); // Convertit en double
                    }
                    // Vérifie si la colonne doit être convertie en un autre nom de variable
                    if (columnMapping.containsKey(columnName)) {
                        columnName = columnMapping.get(columnName);
                    }

                    // Recherche du champ correspondant dans la classe
                    try {
                        java.lang.reflect.Field field = classy.getDeclaredField(columnName);
                        field.setAccessible(true);
                        field.set(obj, value);
                    } catch (NoSuchFieldException e) {
                        System.out.println("Champ non trouvé : " + columnName);
                    }
                }
                results.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    @Override
    public Boolean update(MapTable o) throws Exception {
        Connexion con = super.getCon();
        if (!con.isOpen()) {
            con.creeCon();
        }
    
        try {
            StringBuilder sql = new StringBuilder("UPDATE " + o.getTableName() + " SET ");
            String[] columnNames = o.getColumnNames();
            Object[] values = o.getValues();
    
            // Construction de la requête SQL pour la mise à jour
            for (int i = 0; i < columnNames.length; i++) {
                sql.append(columnNames[i]).append(" = ?");
                if (i < columnNames.length - 1) {
                    sql.append(", ");
                }
            }
    
            sql.append(" WHERE id = ?");  // En supposant qu'il y a une colonne 'id' pour identifier l'entité
    
            PreparedStatement pstmt = con.getCon().prepareStatement(sql.toString());
    
            // Définition des valeurs des colonnes dans la requête préparée
            for (int i = 0; i < values.length; i++) {
                pstmt.setObject(i + 1, values[i]);
            }
    
            pstmt.setObject(values.length + 1, o.getValues()[0]);  // Ici, l'id est supposé être le premier élément
    
            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;
    
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            con.closeCon();
        }
    }

    @Override
    public Boolean update(MapTable o,Connexion c) throws Exception {
        Connexion con = c;
        if (!con.isOpen()) {
            con.creeCon();
        }
    
        try {
            StringBuilder sql = new StringBuilder("UPDATE " + o.getTableName() + " SET ");
            String[] columnNames = o.getColumnNames();
            Object[] values = o.getValues();
    
            // Construction de la requête SQL pour la mise à jour
            for (int i = 0; i < columnNames.length; i++) {
                sql.append(columnNames[i]).append(" = ?");
                if (i < columnNames.length - 1) {
                    sql.append(", ");
                }
            }
    
            sql.append(" WHERE id = ?");  // En supposant qu'il y a une colonne 'id' pour identifier l'entité
    
            PreparedStatement pstmt = con.getCon().prepareStatement(sql.toString());
    
            // Définition des valeurs des colonnes dans la requête préparée
            for (int i = 0; i < values.length; i++) {
                pstmt.setObject(i + 1, values[i]);
            }
    
            pstmt.setObject(values.length + 1, o.getValues()[0]);  // Ici, l'id est supposé être le premier élément
    
            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;
    
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    

    // @Override
    // public Object findById() throws Exception {
    // }

    // @Override
    // public Boolean delete() throws Exception {
    //     return true;
    // }

    // @Override
    // public List<Object> pagination(int index, int nbr) throws Exception {
    // }
}
