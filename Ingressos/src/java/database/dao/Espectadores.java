package database.dao;

import database.*;
import database.dbo.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.function.Function;

public class Espectadores extends BaseDao<Espectador> {
    
    private Espectadores() {}
    
    private static class EspectadoresDaoOperations extends BaseDaoOperations<Espectador> {
        public Espectador fromResultSet(ResultSet rs) throws SQLException {
            Espectador result = new Espectador();
            result.setCpf(rs.getString("cpf"));
            result.setEmail(rs.getString("email"));
            result.setDataNasc(rs.getDate("dataNasc"));
            result.setNomeCompleto(rs.getString("nomeCompleto"));
            result.setSenha(rs.getString("senha"));
            result.setSexo(rs.getString("sexo").charAt(0));
            result.setTelefone(rs.getString("telefone"));
        
            return result;
        }
    }
    
    public static Espectador byCpf(String cpf)
            throws SQLException
    {
        EspectadoresDaoOperations ops = new EspectadoresDaoOperations();
        ops.setParams(new Object[] { cpf });
        Espectador e = getSingle("SELECT * FROM Espectador WHERE cpf = ?", ops);
        return e;
    }
    
    public static Espectador byEmail(String email)
            throws SQLException
    {
        EspectadoresDaoOperations ops = new EspectadoresDaoOperations();
        ops.setParams(new Object[] { email });
        Espectador e = getSingle("SELECT * FROM Espectador WHERE email = ?", ops);
        return e;
    }
    
    public static void insert(Espectador e)
            throws SQLException
    {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO Espectador (cpf, nomeCompleto, email, telefone, sexo, "
                            + "dataNasc, senha) VALUES (?, ?, ?, ?, ?, ?, ?)"
            );
            
            stmt.setString(1, e.getCpf());
            stmt.setString(2, e.getNomeCompleto());
            stmt.setString(3, e.getEmail());
            stmt.setString(4, e.getTelefone());
            stmt.setString(5, "" + e.getSexo());
            stmt.setObject(6, e.getDataNasc());
            stmt.setString(7, e.getSenha());
            
            stmt.execute();
        }
    }
}
