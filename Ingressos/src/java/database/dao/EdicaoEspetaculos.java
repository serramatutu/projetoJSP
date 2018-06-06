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

            return e;
        }
    }
    
    public static EdicaoEspetaculo[] getAll() throws SQLException {
        EspetaculosDaoOperations ops = new EspetaculosDaoOperations();
        ArrayList<EdicaoEspetaculo> l = getMultiple("SELECT * FROM Espetaculo", ops);
        return l.toArray(new EdicaoEspetaculo[l.size()]);
    }
    
    public static EdicaoEspetaculo[] getAllByEspetaculo(UUID espetaculo) throws SQLException {
        EspetaculosDaoOperations ops = new EspetaculosDaoOperations();
        ops.setParams(new Object[] { espetaculo });
        ArrayList<EdicaoEspetaculo> l = getMultiple("SELECT * FROM Espetaculo WHERE Espetaculo = ?", ops);
        return l.toArray(new EdicaoEspetaculo[l.size()]);
    }
}
