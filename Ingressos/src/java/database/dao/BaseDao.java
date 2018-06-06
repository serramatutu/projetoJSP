package database.dao;

import java.sql.ResultSet;
import java.util.function.Function;
import database.DatabaseConnection;
import database.dbo.Espetaculo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;


public class BaseDao<T> {
    public static <T> T getSingle(String query, BaseDaoOperations<T> ops) throws SQLException {
        T elem;
        
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(query);
            ops.bindParams(stmt);
            
            try (ResultSet rs = stmt.executeQuery()) {
                stmt.closeOnCompletion();
                elem = rs.next() ? ops.fromResultSet(rs) : null;
            }
        }
        
        return elem;
    }
    
    public static <T> ArrayList<T> getMultiple(String query, BaseDaoOperations<T> ops) throws SQLException {
        ArrayList<T> list = new ArrayList<T>();
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(query);
            ops.bindParams(stmt);
            
            try (ResultSet rs = stmt.executeQuery()) {
                stmt.closeOnCompletion();
                while (rs.next())
                    list.add(ops.fromResultSet(rs));
            }
        }
        
        return list;
    }
    
    public static <T> boolean executeNonQuery(String command, BaseDaoOperations<T> ops) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(command);
            ops.bindParams(stmt);
            return stmt.execute();
        }
    }
}
