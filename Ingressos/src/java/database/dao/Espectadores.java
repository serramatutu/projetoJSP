package database.dao;

import database.*;
import database.dbo.*;
import java.sql.*;
import java.util.ArrayList;

public class Espectadores {
    
    private Espectadores() {}
    
    private static Espectador fromResultSet(ResultSet rs)
            throws SQLException
    {
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
    
    public static Espectador byCpf(String cpf)
            throws SQLException
    {
        Connection conn = DatabaseConnection.getConnection();
        
        PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM Espectador WHERE cpf = ?"
        );
        stmt.setString(1, cpf);
        
        ResultSet rs = stmt.executeQuery();
        
        stmt.closeOnCompletion();
        
        Espectador e = rs.next() ? fromResultSet(rs) : null;
        
        rs.close();
        conn.close();
        
        return e;
    }
    
    public static Espectador[] byEmail(String email)
            throws SQLException
    {
        Connection conn = DatabaseConnection.getConnection();
        
        PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM Espectador WHERE email = ?"
        );
        stmt.setString(1, email);
        
        ResultSet rs = stmt.executeQuery();
        
        stmt.closeOnCompletion();
        
        ArrayList<Espectador> es = new ArrayList<>();
        
        while (rs.next())
        {
            es.add(fromResultSet(rs));
        }
        
        rs.close();
        conn.close();
        
        return (Espectador[])es.toArray();
    }
}
