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
    private static class ResultSetConverter implements Function<ResultSet, Espetaculo> {
        public Espetaculo apply(ResultSet rs) {
            Espetaculo e = new Espetaculo();
            try {
                e.setClassificacaoIndicativa(rs.getInt("classificacaoIndicativa"));
                e.setNome(rs.getString("nome"));
                e.setId(UUID.fromString(rs.getString("id")));
            }
            catch (SQLException ex) {
                return null;
            }

            return e;
        }
    }
    
    public static Espetaculo[] getAll() throws SQLException {
        ArrayList<Espetaculo> l = getMultiple("SELECT * FROM Espetaculo", new ResultSetConverter());
        return l.toArray(new Espetaculo[l.size()]);
    }
}
