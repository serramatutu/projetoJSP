/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import database.dao.Assentos;
import database.dao.EdicaoEspetaculos;
import database.dao.Setores;
import database.dbo.Assento;
import database.dbo.EdicaoEspetaculo;
import database.dbo.Setor;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AssentoServlet extends AbstractServlet {

    private class SetorAssentos {
        public SetorAssentos(int rowCount, int rowSize) {
            this.rowCount = rowCount;
            this.rowSize = rowSize;
            
            this.seats = new int[rowCount][rowSize];
        }
        
        public int[][] seats;
        public float x;
        public float y;
        public float w;
        public float h;
        public int rowCount;
        public int rowSize;
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<SetorAssentos> l = new ArrayList<SetorAssentos>();
        
        try {
            assertParameters(request, "edicaoEspetaculo");
            
            EdicaoEspetaculo edicaoEspetaculo = EdicaoEspetaculos.byId(
                UUID.fromString(request.getParameter("edicaoEspetaculo"))
            );
            
            Setor[] setores = Setores.byLocalEvento(edicaoEspetaculo.getLocalEvento());
            
            for (Setor s : setores) {
                SetorAssentos sa = new SetorAssentos(s.getAlturaAssentos(), s.getLarguraAssentos());
                for (Assento a : Assentos.getAvailable(edicaoEspetaculo.getId())) {
                    sa.seats[a.getFileira()][a.getPosicao()] = a.isOcupado() ? 1 : 0;
                }
                sa.x = s.getX();
                sa.y = s.getY();
                sa.w = s.getLargura();
                sa.h = s.getAltura();
                
                l.add(sa);
            }
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
        
        try (PrintWriter w = response.getWriter()) {
            w.print(l);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet para alterar dados de um assento";
    }

}
