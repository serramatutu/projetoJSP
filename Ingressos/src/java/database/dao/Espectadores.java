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
    
    public static boolean insert(Espectador e)
            throws SQLException
    {
        EspectadoresDaoOperations ops = new EspectadoresDaoOperations();
        ops.setParams(new Object[] {
            e.getCpf(),
            e.getNomeCompleto(),
            e.getEmail(),
            e.getTelefone(),
            "" + e.getSexo(),
            e.getDataNasc(),
            e.getSenha()
        });
        
        return executeNonQuery("INSERT INTO Espectador (cpf, nomeCompleto, email, telefone, sexo, "
                               + "dataNasc, senha) VALUES (?, ?, ?, ?, ?, ?, ?)", ops);
    }
}
