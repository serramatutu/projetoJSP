package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author u16187
 */
public class DatabaseConnection {
    private static final String CONNECTION_STRING = 
              //"jdbc:sqlserver://regulus:1433" +
              "Server=regulus.cotuca.unicamp.br;" +
              "Database=JSP1RB16187;" +
              "User Id=JSP1RB16187;" +
              "Password=JSP1RB16187;";
    
    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        }
        catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
    
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(CONNECTION_STRING);
        }
        catch (SQLException e) {
            return null;
        }
    }
}
