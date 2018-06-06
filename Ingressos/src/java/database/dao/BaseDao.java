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
    public static <T> T getSingle(String query, Function<ResultSet, T> resultSetConverter) throws SQLException {
        T elem;
        
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(query);
            
            try (ResultSet rs = stmt.executeQuery()) {
                stmt.closeOnCompletion();
                elem = rs.next() ? resultSetConverter.apply(rs) : null;
            }
        }
        
        return elem;
    }
    
    public static <T> ArrayList<T> getMultiple(String query, Function<ResultSet, T> resultSetConverter) throws SQLException {
        ArrayList<T> list = new ArrayList<T>();
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(query);
            
            try (ResultSet rs = stmt.executeQuery()) {
                stmt.closeOnCompletion();
                while (rs.next())
                    list.add(resultSetConverter.apply(rs));
            }
        }
        
        return list;
    }
}
