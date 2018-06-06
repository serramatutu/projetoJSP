package database.dao;

import database.DatabaseConnection;
import database.dbo.Ingresso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class Ingressos {
    private Ingressos() {}
    
    public static void insert(Ingresso i) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        
        PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO Ingresso(TipoIngresso, EdicaoEspetaculo, Assento, Espectador, StatusIngresso) " +
                             "VALUES(?, ?, ?, ?, ?)");
        
        stmt.setString(1, i.getTipoIngresso().toString());
        stmt.setString(2, i.getEdicaoEspetaculo().toString());
        stmt.setString(3, i.getAssento().toString());
        stmt.setString(4, i.getEspectador().toString());
        stmt.setInt(5, i.getStatusIngresso());
        
        stmt.execute();
        
        stmt.closeOnCompletion();
    }
}
