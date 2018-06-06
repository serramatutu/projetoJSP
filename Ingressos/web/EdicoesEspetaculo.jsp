<%@page import="java.util.UUID"%>
<%@page import="database.dbo.LocalEvento"%>
<%@page import="database.dao.LocalEventos"%>
<%@page import="database.dbo.Espetaculo"%>
<%@page import="database.dao.Espetaculos"%>
<%@page import="database.dbo.EdicaoEspetaculo"%>
<%@page import="database.dao.EdicaoEspetaculos"%>
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
                        UUID idEspetaculo = UUID.fromString(request.getParameter("espetaculo"));
                        Espetaculo espetaculo = Espetaculos.getById(idEspetaculo);
                        EdicaoEspetaculo[] es = EdicaoEspetaculos.getAllByEspetaculo(idEspetaculo);

                        for (EdicaoEspetaculo edicao : es)
                        {  
                            LocalEvento local = LocalEventos.getById(edicao.getLocalEvento());
                    %>
                    
                    <div class="item">
                        <div class="caption">
                            <%= edicao.getDataEspetaculo() %>
                        </div>
                        <div class="body">
                            <div>
                                <strong>Local:</strong> <%= local.getNome() %>
                            </div>
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
