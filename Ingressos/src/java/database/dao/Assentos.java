package database.dao;

import database.DatabaseConnection;
import database.dbo.Assento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class Assentos extends BaseDao<Assento> {
    private static class AssentosDaoOperations extends BaseDaoOperations<Assento> {
        public Assento fromResultSet(ResultSet rs) throws SQLException {
            Assento a = new Assento();
            a.setId(UUID.fromString(rs.getString("id")));
            a.setSetor(UUID.fromString(rs.getString("setor")));
            a.setPosicao(rs.getInt("posicao"));
            a.setFileira(rs.getInt("fileira"));

            return a;
        }
    }
    
    public static Assento[] getAvailable(UUID edicaoEspetaculo) throws SQLException {
        AssentosDaoOperations ops = new AssentosDaoOperations();
        ops.setParams(new Object[] { edicaoEspetaculo });
        ArrayList<Assento> l = getMultiple("SELECT * FROM vw_AssentosDisponiveis(?)", ops);
        return l.toArray(new Assento[l.size()]);
    }
}
