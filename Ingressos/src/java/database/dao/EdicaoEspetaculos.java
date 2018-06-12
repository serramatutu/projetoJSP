package database.dao;

import static database.dao.BaseDao.getMultiple;
import database.dbo.EdicaoEspetaculo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class EdicaoEspetaculos extends BaseDao<EdicaoEspetaculo> {
    private static class EspetaculosDaoOperations extends BaseDaoOperations<EdicaoEspetaculo> {
        public EdicaoEspetaculo fromResultSet(ResultSet rs) throws SQLException {
            EdicaoEspetaculo e = new EdicaoEspetaculo();
            e.setDataEspetaculo(rs.getTimestamp("dataEspetaculo").toLocalDateTime());
            e.setEspetaculo(UUID.fromString(rs.getString("espetaculo")));
            e.setId(UUID.fromString(rs.getString("id")));
            e.setLocalEvento(UUID.fromString(rs.getString("localEvento")));

            return e;
        }
    }
    
    public static EdicaoEspetaculo[] all() throws SQLException {
        EspetaculosDaoOperations ops = new EspetaculosDaoOperations();
        ArrayList<EdicaoEspetaculo> l = getMultiple("SELECT * FROM EdicaoEspetaculo", ops);
        return l.toArray(new EdicaoEspetaculo[l.size()]);
    }
    
    public static EdicaoEspetaculo[] allByEspetaculo(UUID espetaculo) throws SQLException {
        EspetaculosDaoOperations ops = new EspetaculosDaoOperations();
        ops.setParams(new Object[] { espetaculo });
        ArrayList<EdicaoEspetaculo> l = getMultiple("SELECT * FROM EdicaoEspetaculo WHERE Espetaculo = ?", ops);
        return l.toArray(new EdicaoEspetaculo[l.size()]);
    }
    
    public static EdicaoEspetaculo byId(UUID id) throws SQLException {
        EspetaculosDaoOperations ops = new EspetaculosDaoOperations();
        ops.setParams(new Object[] { id });
        return getSingle("SELECT * FROM EdicaoEspetaculo WHERE Id = ?", ops);
    }
}
