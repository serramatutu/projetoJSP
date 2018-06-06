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
        
        <div class="wrapper">
            <div class="container box -lightbg">
                <div class="container -centertext">
                    <h2 class="caption -error">Ooooops!</h2>

                    <%
                        String err = request.getParameter("type").toLowerCase();

                        // Formulário inválido
                        if (err.equals("invalid"))
                            out.write("Formulário inválido. Tente novamente.");

                        // Problemas com o banco de dados
                        else if (err.equals("database"))
                            out.write("Estamos tendo alguns problemas com o banco "
                                    + "de dados. Tente novamente mais tarde.");

                        // Falha no cadastro
                        else if (err.equals("register"))
                        {
                            switch (Integer.parseInt(request.getParameter("code")))
                            {
                                // CPF já cadastrado
                                case 1:
                                    out.write("CPF já cadastrado.");
                                    break;

                                // Email já cadastrado
                                case 2:
                                    out.write("E-Mail já cadastrado.");
                                    break;

                                // CPF inválido
                                case 3:
                                    out.write("CPF inválido.");
                                    break;

                                // Senhas não batem
                                case 4:
                                    out.write("As senhas digitadas não conferem.");
                                    break;

                                default:
                                    response.sendRedirect("index.jsp");
                            } 
                        }
                        else
                            response.sendRedirect("index.jsp");
                    %>
                    
                    <br />
                    <br />
                    <br />
                    
                    <a href="#" onclick="window.history.back()">Voltar Atrás</a>
                </div>
            </div>
        </div>
    </body>
</html>