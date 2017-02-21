<%-- 
    Document   : redefinir_senha
    Created on : Apr 22, 2014, 1:56:42 PM
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
                var senha = form1.txt_senha.value;
                if (senha === "") {
                    alert('Digite uma senha');
                    form1.txt_senha.focus();
                    return false;
                }
                var confsenha = form1.txt_confirma.value;
                if (confsenha === "") {
                    alert('Confirme a senha');
                    form1.txt_confirma.focus();
                    return false;
                }
            }
        </script>
        
        <%String msg = request.getParameter("msg");
            if (msg == null) {
                msg = "";
            }

            String hash = request.getParameter("hash");
            String email = request.getParameter("email");
        %>
    </head>
    <body>
        <form name="form1" method="POST" action="RedefinirSenhaUsuarioServlet">

            <input type="text" placeholder="Digite a nova senha" name="txt_senha"/>
            <input type="text" placeholder="Confirme a nova senha" name="txt_confirma"/>
            <input type="hidden" name="txt_email" value="<%=email%>"/>
            <input type="hidden" name="txt_hash" value="<%= hash%>"/>


            <input type="submit" value="Trocar Senha" onclick="return validar()"/>

        </form>
            
            <% if(msg.equals("1")){ %>
            <h2>Sua senha foi redefinida com sucesso!</h2>
            <%} else if(msg.equals("2")) {%>
             <h2>Senha nao foi redefinida, tente novamente!</h2>
            <%}%>
            
    </body>
</html>
