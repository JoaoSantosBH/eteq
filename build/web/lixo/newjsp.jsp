<%-- 
    Document   : newjsp
    Created on : Apr 9, 2014, 4:16:25 PM
    Author     : joaosantos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script language="javascript" type="text/javascript"> 
            function validar() { 
            var nome = form1.nome.value; 
            var email = form1.email.value; 
            var senha = form1.senha.value; 
            var rep_senha = form1.rep_senha.value;   
            if (nome == "") { 
            alert('Preencha o campo com seu nome'); 
            form1.nome.focus(); return false; 
            } 
            } 
        </script> 
    </head>
    <body>
        <form name="form1" action="enviar.php" method="post"> 
            Nome: <input name="nome" type="text"><br /><br /> 
            Email: <input name="email" type="text"><br /><br /> 
            Senha: <input name="senha" type="password"><br /><br /> 
            Repitir Senha: <input name="rep_senha" type="password"><br /><br /> 
            <input type="submit"  onclick="return validar()"> </form> 
    </body>
</html>
