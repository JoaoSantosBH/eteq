<%-- 
    Document   : index
    Created on : Sep 20, 2013, 4:19:46 PM
    Author     : joaomarcelo
--%>
<%@page import="controle.ControladorUsuario"%>
<%@page import="entidade.UsuarioEntidade"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="estilo.css"/> 

        <!--TOPUP IMAGES-->
        <script type="text/javascript" src="http://gettopup.com/releases/latest/top_up-min.js"></script>
        <script src="javascript/jquery.carouFredSel-6.2.0-packed.js" type="text/javascript"/></script>


    <title>EU Tenho EU Quero</title>
    <%

        UsuarioEntidade Logado = (UsuarioEntidade) session.getAttribute("UsuarioLogado");
        String foto = "";
        String Erro = request.getParameter("e");
        if (Erro == null) {
            Erro = "";
        }

    %>


</head>
<body>

    <div id="pai"> <!-- P  A  I  -->

        <div id="baner"><!-- b a n e r  -->

            <a class="logomarca" href="index.jsp"></a>


            <% if (Logado != null) { //usu·rio logado na sess„o %>

            <div id="painelUsuario">

                <% foto = ControladorUsuario.pegarFotoUsuario(Logado.getIdUsuario().toString());
                    boolean fotoAprovada = ControladorUsuario.verificarSeFotoFoiAprovada(Logado.getIdUsuario().toString());

                    if (fotoAprovada) {%>

                    <a href="editar_perfil.jsp"><img src="<%=foto%>" height="35" width="35"></a>

                <%} else {%>
                 <a href="editar_perfil.jsp"><img src="/img/foto_default.png" height="35" width="35"></a>

                <%}%>
                <h2><%= Logado.getNome()%></h2>
                <% String data = fronteira.FormataDataHtml.formatarDataParaDisplay(Logado.getUltimoAcesso().toString());%>
                ultimo acesso <%= data%>

            </div>
            <a class="logout" href="LogoutServlet" ></a>
            <% } else {%>



            <!-- b o t a o    C A D A S T R O  -->
            <a class="cadastro" href="cadastre_se.jsp" ></a>

            <!-- b o t a o    L O G I N  -->
            <a class="login" href="identifique_se.jsp" ></a>
            <%}%>

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

            <div id="a0">
                <div id="a1"><!-- A1-->
                    <div id="a2"><!-- A2-->
                        <div id="a3"><!-- A3-->
                            <div id="a4">
                                <div id="a5">
                                    <div id="a6">
                                        <div id="a7">
                                            <div id="a8">

                                                <div id="i0" class="page">

                                                    <jsp:include page="login.jsp">
                                                        <jsp:param name="opcao" value="2"/>
                                                    </jsp:include>

                                                </div>


                                                <div id="i1"  class="page"><!-- M U L H E R E S -->
                                                    <jsp:include page="mulheres.jsp">
                                                        <jsp:param name="opcao" value="2"/>
                                                    </jsp:include>
                                                </div><!-- F I M   M U L H E R E S -->  

                                                <div id="i2" class="page"> <!-- H O M E N S -->
                                                    <jsp:include page="homens.jsp">
                                                        <jsp:param name="opcao" value="3"/>
                                                    </jsp:include>
                                                </div><!-- F I M  H O M E N S -->

                                                <div id="i3" class="page"><!-- C R I A N C A S -->
                                                    <jsp:include page="criancas.jsp">
                                                        <jsp:param name="opcao" value="3"/>
                                                    </jsp:include>
                                                </div><!-- F I M  C R I A N C A S -->

                                                <div id="i4" class="page"><!-- C A S A -->
                                                    <jsp:include page="casa.jsp">
                                                        <jsp:param name="opcao" value="3"/>
                                                    </jsp:include>                                                    
                                                </div><!-- F I M  C A S A -->


                                                <div id="i5" class="page"><!-- I N F O -->
                                                    <jsp:include page="infoeletro.jsp">
                                                        <jsp:param name="opcao" value="3"/>
                                                    </jsp:include>
                                                </div><!-- F I M  I N F O -->


                                                <div id="i6" class="page"><!-- O U T R O S -->
                                                    <jsp:include page="outros.jsp">
                                                        <jsp:param name="opcao" value="3"/>
                                                    </jsp:include>
                                                </div><!-- F I M  O U T R O S -->

                                                <div id="i7" class="page"><!-- L O G I N -->

                                                
                                                </div><!-- F I M  L O G I N -->





                                            </div>
                                        </div>
                                    </div>
                                </div>                              
                            </div>
                        </div>

                    </div>
                </div>

            </div>











            <!--            <a href="welcomePrimefaces.jsf" toptions="type = iframe, width = 350, height = 350, modal=1">a</a>-->

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
