<%-- 
    Document   : senha
    Created on : Apr 21, 2014, 6:37:42 PM
    Author     : joaosantos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="estilo.css"/>
        <script language="javascript" type="text/javascript">
            function validar() {
                var email = form1.txt_email.value;
                if (email === "") {
                    alert('Digite seu email');
                    form1.txt_email.focus();
                    return false;
                }
            }
        </script>
        <%String msg = request.getParameter("msg");
            if (msg == null) {
                msg = "";
            }

        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ETEQ</title>
    </head>


    <body>
        <div id="senha">

            
            <form name="form1" method="POST" action="EsqueciMinhaSenhaServlet">
                <input type="text" placeholder="DIGITE O EMAIL CADASTRADO" name="txt_email"/>
                <input class="btn-cad" type="submit" value="enviar" onclick="return validar()"/>

            </form>

            <% if (msg.equals("1")) {%>

            <h2>Uma mensagem foi enviada para seu e-mail</h2>

            <%}%>
        </div>
    </body>
</html>
