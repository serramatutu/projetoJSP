<%@page contentType="html" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Ingressos</title>
        
        <%@include file="WEB-INF/jspf/common-head.jspf" %>
        <script type="text/javascript">
            $(document).ready(function() {
                // Máscaras dos inputs
                $(".cpf").mask('000.000.000-00');
                
                // Valida o formulário
                $("#formLogin").on('submit', function(e) {
                    e.preventDefault();
                    return $("#formLogin > input[name=cpf]")[0].validity.valid ||
                            $("#formLogin > input[name=email]")[0].validity.valid;
                });
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
                
                <form id="formLogin" method="POST" action="Login">
                    <label for="cpf">CPF</label>
                    <input 
                        name="cpf"
                        class="cpf"
                        pattern="\d{3}\.\d{3}\.\d{3}-\d{2}"
                        type="text"
                        maxlength="14"
                        placeholder="000.000.000-00"
                        autocomplete="off"
                        oninput="validateCpf(event)" />
                    
                    <p class="-centertext">ou</p>
                    
                    <label for="email">Email</label>
                    <input
                        name="email"
                        type="email"
                        class="email"
                        autocomplete="off"
                        placeholder="fulano@email.com"
                        maxlength="40" />
                    
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
                            value="Registrar"
                            />
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
