package database.dao;

import database.dbo.Setor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class Setores extends BaseDao<Setor> {
    private static class SetoresDaoOperations extends BaseDaoOperations<Setor> {

        @Override
        public Setor fromResultSet(ResultSet rs) throws SQLException {
            Setor s = new Setor();
            s.setId(UUID.fromString(rs.getString("id")));
            s.setNome(rs.getString("nome"));
            s.setAltura(rs.getFloat("altura"));
            s.setLargura(rs.getFloat("largura"));
            s.setX(rs.getFloat("x"));
            s.setY(rs.getFloat("y"));
            s.setLocalEvento(UUID.fromString(rs.getString("localEvento")));
            s.setAlturaAssentos(rs.getInt("alturaAssentos"));
            s.setLarguraAssentos(rs.getInt("larguraAssentos"));
            
            return s;
        }
    }
    
    public static Setor[] byLocalEvento(UUID localEvento) throws SQLException {
        SetoresDaoOperations ops = new SetoresDaoOperations();
        ops.setParams(new Object[] { localEvento });
        ArrayList<Setor> l = getMultiple("SELECT * FROM Setor WHERE LocalEvento = ?", ops);
        return l.toArray(new Setor[l.size()]);
    }
    
}
