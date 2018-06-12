package database.dao;

import database.DatabaseConnection;
import static database.dao.BaseDao.getMultiple;
import database.dbo.Espetaculo;
import database.dbo.LocalEvento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.function.Function;

public class LocalEventos extends BaseDao<LocalEvento> {
    private static class LocalEventosDaoOperations extends BaseDaoOperations<LocalEvento> {
        public LocalEvento fromResultSet(ResultSet rs) throws SQLException {
            LocalEvento l = new LocalEvento();
            l.setId(UUID.fromString(rs.getString("id")));
            l.setCEP(rs.getString("cep"));
            l.setNome(rs.getString("nome"));

            return l;
        }
    }
    
    public static LocalEvento[] all() throws SQLException {
        LocalEventosDaoOperations ops = new LocalEventosDaoOperations();
        ArrayList<LocalEvento> l = getMultiple("SELECT * FROM LocalEvento", ops);
        return l.toArray(new LocalEvento[l.size()]);
    }
    
    public static LocalEvento byId(UUID id) throws SQLException {
        LocalEventosDaoOperations ops = new LocalEventosDaoOperations();
        ops.setParams(new Object[] { id });
        return getSingle("SELECT * FROM LocalEvento WHERE Id = ?", ops);
    }
}
