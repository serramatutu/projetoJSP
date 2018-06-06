package server;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.SQLException;

import database.dbo.*;
import database.dao.*;
import java.text.SimpleDateFormat;
import java.util.regex.*;

public class CadastroServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendError(405);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        try {
            
            assertParameters("cpf", "email", "nomeCompleto", "senha", "sexo", 
                    "telefone");
        
            if (Espectadores.byCpf(request.getParameter("cpf")) != null)
            {
                // CPF já cadastrado
                response.sendRedirect("Erro.jsp?type=Register&code=1");
                return;
            }
            
            if (Espectadores.byEmail(request.getParameter("email")).length > 0)
            {
                // Email já cadastrado
                response.sendRedirect("Erro.jsp?type=Register&code=1");
                return;
            }
            
            Espectador spec = new Espectador();

            try {
                spec.setCpf(validateCpf(request.getParameter("cpf")));
            } 
            catch (Exception e)
            {
                // Formato inválido para o CPF
                response.sendRedirect("Erro.jsp?type=Register&code=3");
            }

            spec.setDataNasc(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dataNasc")));
            spec.setEmail(request.getParameter("email"));
            spec.setNomeCompleto(request.getParameter("nomeCompleto"));
            spec.setSenha(request.getParameter("senha"));
            spec.setSexo(request.getParameter("sexo").charAt(0));
            spec.setTelefone(request.getParameter("telefone"));

            request.getSession().setAttribute("user", spec);

            response.sendRedirect("index.jsp");
                
        } catch (SQLException e) {
            
            e.printStackTrace();
            
            // Redireciona para a página de erro
            response.sendRedirect("Erro.jsp?type=Database");
            
        }
        catch (Exception e)
        {
            e.printStackTrace();

            // Formulário inválido
            response.sendRedirect("Erro.jsp?type=Invalid");
        }
    }

    /**
     * Valida um CPF
     * 
     * @param cpf CPF a ser validado
     * @return O CPF
     * @throws Exception caso o CPF seja inválido
     */
    private String validateCpf(String cpf) throws Exception {
        Pattern r = Pattern.compile("(\\d{3})\\.(\\d{3})\\.(\\d{3})-(\\d{2})");
        Matcher m = r.matcher(cpf);
                
        if (!m.matches())
            throw new Exception("CPF inválido");

        String s = m.group(1) + m.group(2) + m.group(3);
        
        int[] ints = new int[s.length() + 1];
        for (int i = 0; i < s.length(); i++)
            ints[i] = s.charAt(i) - '0';

        int n = 0;
        for (int i = 10; i > 1; i--)
            n += i * ints[10 - i];

        if (n * 10 % 11 % 10 != (m.group(4).charAt(0) - '0'))
            throw new Exception("CPF inválido");

        ints[s.length()] = (m.group(4).charAt(0) - '0');

        n = 0;
        for (int i = 11; i > 1; i--)
            n += i * ints[11 - i];

        if (n * 10 % 11 % 10 != (m.group(4).charAt(1) - '0'))
            throw new Exception("CPF inválido");
        
        return cpf;
    }
    
    @Override
    public String getServletInfo() {
        return "Servlet para cadastro de usuários";
    }
}
