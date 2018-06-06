package database.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public abstract class BaseDaoOperations<T> {
    public abstract T fromResultSet(ResultSet rs) throws SQLException;
    
    Object[] params;
    public void setParams(Object[] params) {
        this.params = params;
    }
    
    public void bindParams(PreparedStatement stmt) throws SQLException {
        if (params == null)
            return;
        
        for (int i=0; i<params.length; i++) {
            Object o = params[i];
            Class c = o.getClass();
            
            if (c.equals(String.class)) {
                stmt.setString(i+1, (String)o);
            }
            else if (c.equals(UUID.class)) {
                stmt.setString(i+1, o.toString());
            }
            else if (c.equals(Integer.class)) {
                stmt.setInt(i+1, (int)o);
            }
            else {
                stmt.setObject(i+1, o);
            }
        }
    }
}
