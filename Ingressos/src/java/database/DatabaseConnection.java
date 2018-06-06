package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author u16187
 */
public class DatabaseConnection {
    
    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    // Classe estática
    private DatabaseConnection() {}
    
    /**
     * Obtém uma conexão com o banco de dados
     * 
     * @return Uma java.sql.Connection ligada ao banco de dados
     */
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                "jdbc:sqlserver://regulus.cotuca.unicamp.br:1433;DatabaseName=JSP1RB16187",
                "JSP1RB16187",
                "JSP1RB16187"
            );
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
