package database.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
                stmt.setString(i, (String)o);
            }
            else if (c.equals(Integer.class)) {
                stmt.setInt(i, (int)o);
            }
            else {
                stmt.setObject(i, o);
            }
        }
    }
}
