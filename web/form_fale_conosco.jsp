<%-- 
    Document   : index
    Created on : Sep 20, 2013, 4:19:46 PM
    Author     : joaomarcelo
--%>
<%@page import="controle.ControladorUsuario"%>
<%@page import="persistencia.TbCidadesDAO"%>
<%@page import="entidade.TbCidadesEntidade"%>
<%@page import="persistencia.TbEstadosDAO"%>
<%@page import="entidade.TbEstadosEntidade"%>
<%@page import="persistencia.SubCategoriaDAO"%>
<%@page import="entidade.SubcategoriaEntidade"%>
<%@page import="persistencia.CategoriaDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="entidade.CategoriaEntidade"%>
<%@page import="entidade.UsuarioEntidade"%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <% String Codmsg = request.getParameter("msg");
            if (Codmsg == null) {
                Codmsg = "";
            }
            UsuarioEntidade Logado = (UsuarioEntidade) session.getAttribute("UsuarioLogado");
            String Erro = request.getParameter("e");
            if (Erro == null) {
                Erro = "";
            }
        %>


        <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'></script>
        <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/MetodosParaODWR.js'></script>
        <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script>



        <script language="javascript" type="text/javascript">

            function validar() {

                var assunto = formFC.txt_assunto.value;
                if (assunto === "") {
                    alert('Preencha o assunto');
                    formFC.txt_assunto.focus();
                    return false;
                }
                var msg = formFC.txt_msg.value;
                if (msg === "") {
                    alert('Preencha sua mensagem');
                    formFC.txt_msg.focus();
                    return false;
                }


            }
        </script>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    </head>
    <body>



        <div id="col1_cadastro_pessoal">
            <% if (Codmsg.equals("1")) {%>
            <pre>
            <h3 style="color: #669900; font-family:Gill, Helvetica, sans-serif" >
                
Obrigado <%= Logado.getNome()%>
Sua mensagem foi enviada 
para o administrador.  
Você será contactado 
por e-mail
em breve!</h3> 
            </pre>
            <% }%>
        </div>
        <div id="cadastro">
            <form name="formFC" action="FaleConoscoServlet" method="post" >
                <input size="32" placeholder="ASSUNTO" type="text" name="txt_assunto" maxlength="45" /> <br/>
                <textarea rows="15" cols="42" placeholder="MENSAGEM" draggable="false"  name="txt_msg"></textarea>

                <input type="hidden" name="id_usuario" value="<%=Logado.getIdUsuario()%>" >
                <input type="submit" value="Enviar" class="btn_salvar" onclick="return validar()">
            </form>
        </div>


    </body>
</html>
