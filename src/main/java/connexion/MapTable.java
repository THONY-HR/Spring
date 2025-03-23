package connexion;

import java.util.Map;

public interface MapTable {
    String getTableName();
    String[] getColumnNames();
    Object[] getValues();
    public Map<String, String> getColumnMapping();
}
