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
    public static boolean insert(Ingresso i) throws SQLException {
        IngressosDaoOperations ops = new IngressosDaoOperations();
        ops.setParams(new Object[] {
            i.getTipoIngresso(),
            i.getEdicaoEspetaculo(),
            i.getAssento(),
            i.getEspectador(),
            i.getStatusIngresso()
        });
        return executeNonQuery("INSERT INTO Ingresso(TipoIngresso, EdicaoEspetaculo, Assento, Espectador, StatusIngresso) " +
                               "VALUES(?, ?, ?, ?, ?)", ops);
    }
}
