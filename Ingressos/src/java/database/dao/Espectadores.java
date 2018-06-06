package database.dao;

import database.*;
import database.dbo.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.function.Function;

public class Espectadores {
    
    private Espectadores() {}
    
    private static class ResultSetConverter implements Function<ResultSet, Espectador> {
        public Espectador apply(ResultSet rs) {
            Espectador result = new Espectador();
            try {
                result.setCpf(rs.getString("cpf"));
                result.setEmail(rs.getString("email"));
                result.setDataNasc(rs.getDate("dataNasc"));
                result.setNomeCompleto(rs.getString("nomeCompleto"));
                result.setSenha(rs.getString("senha"));
                result.setSexo(rs.getString("sexo").charAt(0));
                result.setTelefone(rs.getString("telefone"));
            }
            catch (SQLException e) {
                return null;
            }
        
            return result;
        }
    }
    
    public static Espectador byCpf(String cpf)
            throws SQLException
    {
        Espectador e;
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * FROM Espectador WHERE cpf = ?"
            );  
            
            stmt.setString(1, cpf);
            
            ResultSet rs = stmt.executeQuery();
            stmt.closeOnCompletion();
            
            e = rs.next() ? fromResultSet(rs) : null;
            
            rs.close();
            conn.close();
        }
        
        return e;
    }
    
    public static Espectador byEmail(String email)
            throws SQLException
    {
        Espectador e = null;
        
        try (Connection conn = DatabaseConnection.getConnection()) {
            
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * FROM Espectador WHERE email = ?"
            );  stmt.setString(1, email);
            
            ResultSet rs = stmt.executeQuery();
            stmt.closeOnCompletion();
            
            if (rs.next())
                e = fromResultSet(rs);
            
            rs.close();
        }
        
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
