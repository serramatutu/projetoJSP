<%@page import="java.util.UUID"%>
<%@page import="database.dbo.Assento"%>
<%@page import="database.dao.Assentos"%>
<%@page contentType="html" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Ingressos</title>
        
        <%@include file="WEB-INF/jspf/common-head.jspf" %>
    </head>
    <body>
        <!-- Cabeçalho -->
        <%@include file="WEB-INF/jspf/header.jspf" %>
        
        <!-- Conteúdo -->
        <div class="wrapper">
            <div class="container box -lightbg -centertext">
                <div class="container">
                    Escolha seu assento:
                    
                    <%
                        Assento[] assentos = Assentos.getAvailable(UUID.fromString(request.getParameter("edicao")));

                        for (Assento ass : assentos)
                        {
                    %>
                    
                    <input type="checkbox" id="<%= ass.getId() %>" name="<%= "assento[" + ass.getId() + "]" %>" />
                    <label for=<%= ass.getId() %>>
                        Setor <%= ass.getSetor() %>: <%= ass.getFileira() %> - <%= ass.getPosicao() %>
                    </label>
                    
                    <%  } %>
                </div>
            </div>
            <div class="container -centertext">
                <a href="Espetaculos.jsp">Voltar Atrás</a>
            </div>
        </div>
    </body>
</html>
