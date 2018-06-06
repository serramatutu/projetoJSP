package database.dao;

import database.DatabaseConnection;
import database.dbo.Assento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class Assentos {
    private static Assento fromResultSet(ResultSet rs) throws SQLException {
        Assento a = new Assento();
        a.setId(UUID.fromString(rs.getString("id")));
        a.setSetor(UUID.fromString(rs.getString("setor")));
        a.setPosicao(rs.getInt("posicao"));
        a.setFileira(rs.getInt("fileira"));
        
        return a;
    }
    
    public static Assento[] getAvailable(UUID edicaoEspetaculo) throws SQLException {
        Assento[] available;
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
            "SELECT * FROM vw_AssentosDisponiveis(?)");
            stmt.setString(1, edicaoEspetaculo.toString());
            
            ResultSet rs = stmt.executeQuery();
            stmt.closeOnCompletion();
            
            ArrayList<Assento> availableList = new ArrayList<Assento>();
            while (rs.next())
                availableList.add(fromResultSet(rs));
            available = new Assento[availableList.size()];
            available = availableList.toArray(available);
            
            rs.close();
        }
        
        return available;
    }
}
