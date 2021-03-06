package database.dao;

import database.DatabaseConnection;
import database.dbo.Espetaculo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.function.Function;

public class Espetaculos extends BaseDao<Espetaculo> {
    private static class EspetaculosDaoOperations extends BaseDaoOperations<Espetaculo> {
        public Espetaculo fromResultSet(ResultSet rs) throws SQLException {
            Espetaculo e = new Espetaculo();
            e.setClassificacaoIndicativa(rs.getInt("classificacaoIndicativa"));
            e.setNome(rs.getString("nome"));
            e.setId(UUID.fromString(rs.getString("id")));
            e.setDescricao(rs.getString("descricao"));

            return e;
        }
    }
    
    public static Espetaculo[] all() throws SQLException {
        EspetaculosDaoOperations ops = new EspetaculosDaoOperations();
        ArrayList<Espetaculo> l = getMultiple("SELECT * FROM Espetaculo", ops);
        return l.toArray(new Espetaculo[l.size()]);
    }
    
    public static Espetaculo byId(UUID id) throws SQLException {
        EspetaculosDaoOperations ops = new EspetaculosDaoOperations();
        ops.setParams(new Object[] { id });
        return getSingle("SELECT * FROM Espetaculo WHERE Id = ?", ops);
    }
}
