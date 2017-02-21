<%-- 
    Document   : validar_cadastro
    Created on : Feb 25, 2014, 2:28:06 PM
    Author     : joaosantos
--%>


<%@page import="entidade.UsuarioEntidade"%>
<%@page import="persistencia.UsuarioDAO"%>
<%@page import="util.MD5"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="estilo.css"/> 
        <title>JSP Page</title>
        <%
            UsuarioEntidade Autenticado = null;
            String nome="";
            String Erro = request.getParameter("e");
            if (Erro == null) {
                Erro = "";
            }
            String email = request.getParameter("e-mail");
            String hash = request.getParameter("hash");

            Integer id = UsuarioDAO.pegarIdPorEmail(email);
            MD5 md5 = new MD5();
            String hash_teste = md5.criarMD5ParaEnviarLink(id.toString());

            if (hash.equals(hash_teste)) {

               
                UsuarioDAO.validarUsuarioPorLinkEmail(email);
                nome = UsuarioDAO.pegarAtributoNomeDeUsuario(email);
            }

        %>



</head>
<body>
    <div id="pai"> <!-- P  A  I  -->

        <div id="baner"><!-- b a n e r  -->

            <a class="logomarca" href="index.jsp"></a>
       


            <!-- b o t a o    C A D A S T R O  -->
            <a class="cadastro" href="contrato_utilizacao.jsp" toptions="type = iframe, width = 510, height = 500,effect= appear, modal=1" ></a>

   <!-- b o t a o    L O G I N  -->
            <a class="login" href="identifique_se.jsp" ></a>
        </div><!-- F  I  M   b a n e r  -->


        <div id="menu"><!-- m e n u  -->

            <!-- b o t o e s    M E N U  -->
            <a class="mulheres" href="#a1"></a>
            <a class="homens" href="#a2"></a>
            <a class="criancas" href="#a3"></a>
            <a class="casa" href="#a4"></a>
            <a class="info" href="#a5"></a>
            <a class="outros" href="#a6"></a>
            <a class="eutenho" href="cadastrar_patrimonio.jsp"></a>

            <div class="busca">

                <div class="campobuscaleft">
                    <img src="img/btn_busca.png"/>
                </div>

                <div class="campobuscaright">
                    <form method="post" action="BuscaServlet">

                        <input id="query" name="txt_query" placeholder="digite um termo para a busca" type="text" value=""/>
                    </form> 
                </div>    


            </div>


        </div><!-- m e n u  -->



        <div id="conteudo">
            <!--          c o n t e u d o  <iframe src="inicial.html"  name="content"></iframe>-->

          <%if (hash.equals(hash_teste)) {%>
            <h1>Obrigado <%= nome %> <br/><h2>agora você já pode logar em nosso sistema! Seja Bem Vindo!
                    <%} else {%>
                    <h1 style="color: red;">Não foi possivel validar seu cadastro! Por favor verifique se seu e-mail está correto!  
                    
                    <%}%> 
  
        </div><!-- c o n t e u d o  -->


        <div id="rodape"> <!-- r o d a p e -->

            <div id="funcio"><a class="funcionamento" href="instrucao.jsp">COMO FUNCIONA</a></div>
            <div id="priva"> <a class="privacidade" href="privacidade.jsp">PRIVACIDADE</a></div>
            <div id="term"><a class="termos" href="contrato_utilizacao.jsp" target="_blank">TERMOS DE SERVIÇO</a></div>
            <div id="fale"><a class="faleconosco" href="fale_conosco.jsp">FALE CONOSCO</a></div>
            
        </div> <!-- r o d a p e -->



    </div> <!-- P  A  I  -->
</body>
</html>
