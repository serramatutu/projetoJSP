/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import database.dao.*;
import database.dbo.*;

import java.io.*;

import java.security.*;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author User
 */
public class LogInServlet extends AbstractServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try 
        {
            assertParameters(request, "email", "senha");
            
            Espectador spec = Espectadores.byEmail(request.getParameter("email"));
            
            // Usuário inexistente
            if (spec == null)
            {
                response.sendRedirect("Erro.jsp?type=Login&code=1");
                return;
            }
            
            // Senha incorreta
            final MessageDigest MD5 = MessageDigest.getInstance("MD5");
            byte[] passwordHash = MD5.digest(request.getParameter("senha").getBytes());
            if (!spec.getSenha().equals(Base64.getEncoder().encodeToString(passwordHash)))
            {
                response.sendRedirect("Erro.jsp?type=Login&code=2");
                return;
            }
            
            // Se não, faz login normalmente
            request.getSession().setAttribute("user", spec);
            
            if (request.getParameter("espetaculo") == null)
                response.sendRedirect("index.jsp");
            else 
                response.sendRedirect("Compra?espetaculo=" + request.getParameter("espetaculo"));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            
            response.sendRedirect("Erro.jsp?type=Database");
            return;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            
            response.sendRedirect("Erro.jsp?type=Invalid");
            return;
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet para login";
    }
}
