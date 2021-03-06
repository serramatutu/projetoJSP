<%@page import="database.dao.LocalEventos"%>
<%@page import="database.dbo.LocalEvento"%>
<%@page import="database.dao.EdicaoEspetaculos"%>
<%@page import="database.dbo.EdicaoEspetaculo"%>
<%@page import="database.dbo.Espetaculo"%>
<%@page import="database.dao.Espetaculos"%>
<%@page contentType="html" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Ingressos - Espetáculos</title>
        <%@include file="WEB-INF/jspf/common-head.jspf" %>
    </head>
    <body>
        <!-- Cabeçalho -->
        <%@include file="WEB-INF/jspf/header.jspf" %>
        
        
        <!-- Conteúdo -->
        <div class="wrapper">
            <div class="container box -lightbg">
                <div class="shows">
                    <%
                        Espetaculo[] es = Espetaculos.getAll();

                        for (Espetaculo e : es)
                        {  
                    %>
                    
                    <div class="item">
                        <div class="background">
                            <img src=<%= "img/espetaculos/" + e.getId() + ".png" %> />
                        </div>
                        <div class="caption">
                            <%= e.getNome() %>
                        </div>
                        <div class="body">
                            <div>
                                <strong>Classificação indicativa:</strong> <%= e.getClassificacaoIndicativa() == 0 ? "Livre" : e.getClassificacaoIndicativa() %>
                                <br /><br />
                                <strong>Descrição:</strong> <%= e.getDescricao() %>
                                <br />&nbsp;
                            </div>
                        </div>
                        <div class="buttons">
                            <form 
                                method="GET"
                                action=<%= session.getAttribute("user") == null ? "LogIn.jsp" : "Comprar.jsp" %>>
                                
                                <select name="edicao">
                                    <% 
                                        EdicaoEspetaculo[] ess = EdicaoEspetaculos.getAllByEspetaculo(e.getId());
                                        for (EdicaoEspetaculo edicao : ess) 
                                        { 
                                            LocalEvento local = LocalEventos.getById(edicao.getLocalEvento());
                                    %>

                                    <option value="<%= edicao.getId() %>">
                                        <%= edicao.getDataEspetaculo() + " - " + local.getNome() %>
                                    </option>

                                    <% } %>
                                </select>
                                    
                                <div class="submit-wrapper">
                                    <input type="submit" class="-lightbg -bold" value="Comprar" />
                                </div>
                            </form>
                        </div>
                    </div>
                    
                    <%
                        }
                    %>
                </ul>
            </div>
        </div>
    </body>
</html>
