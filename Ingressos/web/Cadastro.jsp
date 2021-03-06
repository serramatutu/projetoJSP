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
                $('.phone').mask('(00) 0000-0000');
                $(".cpf").mask('000.000.000-00');
                
                // Valida o formulário
                $("#formCadastro").submit(function(e) {
                    return validateCpf() && $('#confirmaSenha').val() == $('#senha').val();
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
                <span class="-icon">&#xe85d;</span>
                <span class="label">Cadastro</span>
            </div>
            <div class="container box -lightbg">
                <div class="spacing"></div>
                <form id="formCadastro" method="POST" action="Cadastro">
                    
                    <span class="section">
                        <span class="caption">Informações:</span>
                        <span class="description">Seus dados, usados para a emissão dos ingressos</span>
                    </span>
                    
                    <label for="nomeCompleto">Nome Completo</label>
                    <input 
                        name="nomeCompleto" 
                        type="text" 
                        maxlength="150"
                        placeholder="Fulano"
                        autocomplete="off"
                        maxlength="250"
                        required />

                    <label for="cpf">CPF</label>
                    <input 
                        name="cpf"
                        class="cpf"
                        pattern="\d{3}\.\d{3}\.\d{3}-\d{2}"
                        type="text"
                        maxlength="14"
                        placeholder="000.000.000-00"
                        autocomplete="off"
                        oninput="validateCpf(event)"
                        required />

                    <label for="dataNasc">Data de Nascimento</label>
                    <input
                        name="dataNasc"
                        class="date"
                        type="date"
                        autocomplete="off"
                        required />

                    <label for="telefone">Telefone</label>
                    <input
                        name="telefone"
                        class="phone"
                        type="text"
                        maxlength="11"
                        pattern="\(\d\d\)\s*\d{4}-\d{4}"
                        placeholder="(99) 9999-9999"
                        autocomplete="off"
                        required />
                    
                    <br>
                    
                    <span class="section">
                        <span class="caption">Dados de acesso:</span>
                        <span class="description">São as informações usadas para fazer login no site</span>
                    </span>
                    <label for="email">Email</label>
                    <input
                        name="email"
                        type="email"
                        class="email"
                        autocomplete="off"
                        placeholder="fulano@email.com"
                        maxlength="40"
                        required />
                    
                    <label for="sexo">Sexo</label>
                    <select name="sexo" required>
                        <option value="M">Masculino</option>
                        <option value="F">Feminino</option>
                    </select>
                    
                    <label for="senha">Senha</label>
                    <input
                        type="password"
                        name="senha"
                        id="senha"
                        placeholder="Senha"
                        required />
                    
                    <label for="confirmaSenha">Confirmação de senha</label>
                    <input
                        type="password"
                        name="confirmaSenha"
                        id="confirmaSenha"
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
                <div class="spacing"></div>
            </div>
        </div>
    </body>
</html>
