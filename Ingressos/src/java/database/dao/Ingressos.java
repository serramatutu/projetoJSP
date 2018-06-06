package database.dao;

import database.DatabaseConnection;
import database.dbo.Ingresso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class Ingressos extends BaseDao<Ingresso> {
    private Ingressos() {}
    
    public static class IngressosDaoOperations extends BaseDaoOperations<Ingresso> {
        public Ingresso fromResultSet(ResultSet rs) throws SQLException {
            Ingresso i = new Ingresso();
            
            i.setAssento(UUID.fromString(rs.getString("assento")));
            i.setEdicaoEspetaculo(UUID.fromString(rs.getString("edicaoEspetaculo")));
            i.setEspectador(UUID.fromString(rs.getString("espectador")));
            i.setStatusIngresso(rs.getInt("statusIngresso"));
            i.setTipoIngresso(UUID.fromString(rs.getString("tipoIngresso")));
            
            return i;
        }
    }
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
