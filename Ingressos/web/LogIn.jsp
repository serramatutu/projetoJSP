<%@page contentType="html" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Ingressos</title>
        
        <%@include file="WEB-INF/jspf/common-head.jspf" %>
        <script type="text/javascript" src="scripts/cpf.js"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                // Máscaras dos inputs
                $(".cpf").mask('000.000.000-00');
            });
        </script>
    </head>
    <body>
        <!-- Cabeçalho -->
        <%@include file="WEB-INF/jspf/header.jspf" %>
        
        <!-- Conteúdo -->
        <div class="wrapper">
            <div class="box -primarybg -header">
                <span class="-icon">&#xe154;</span>
                <span class="label">Login</span>
            </div>
            <div class="container box -lightbg">
                
                <form id="formLogin" method="POST" action="LogIn">                    
                    <label for="email">Email</label>
                    <input
                        name="email"
                        type="email"
                        class="email"
                        autocomplete="off"
                        placeholder="fulano@email.com"
                        maxlength="40" 
                        required />
                    
                    <label for="senha">Senha</label>
                    <input
                        type="password"
                        name="senha"
                        id="senha"
                        placeholder="Senha"
                        required />
                    
                    <div class="submit-wrapper">
                        <input 
                            type="submit" 
                            class="-primarybg"
                            value="Entrar"
                            />
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
