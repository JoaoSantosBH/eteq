<%-- 
    Document   : index
    Created on : Sep 20, 2013, 4:19:46 PM
    Author     : joaomarcelo
--%>
<%@page import="persistencia.UsuarioDAO"%>
<%@page import="controle.ControladorUsuario"%>
<%@page import="persistencia.ForumDAO"%>
<%@page import="entidade.RespostaEntidade"%>
<%@page import="controle.ControladorResposta"%>
<%@page import="java.util.List"%>


<%@page import="java.util.ArrayList"%>

<%@page import="persistencia.AdminDAO"%>
<%@page import="entidade.UsuarioEntidade"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="estilo.css"/> 

        <!--TOPUP IMAGES-->
        <script type="text/javascript" src="http://gettopup.com/releases/latest/top_up-min.js"></script>
        <script src="javascript/jquery.carouFredSel-6.2.0-packed.js" type="text/javascript"/></script>
    <script language="javascript" type="text/javascript">
        function validarResp() {
            var resp = form1resp.txt_resposta.value;
            if (resp === "") {
                alert('Digite uma resposta');
                form1resp.txt_resposta.focus();
                return false;
            }



        }
    </script>
    <title>EU Tenho EU Quero</title>
    <%
        String admin = AdminDAO.pegarNomeAdmin();
        UsuarioEntidade Logado = (UsuarioEntidade) session.getAttribute("UsuarioLogado");
        String foto = "";
        String Erro = request.getParameter("e");
        String idForum = request.getParameter("id");
        String idUsuario = "";
        if(Logado !=null){idUsuario = Logado.getIdUsuario().toString();}
        
        Boolean forum = ForumDAO.verificarSeForumEstaAtivo(idForum);
        if(forum == null) {forum = false;}

        List<RespostaEntidade> lst = ControladorResposta.pegarRespostas(idForum);

        if (Erro == null) {
            Erro = "";
        }

    %>


</head>
<body>

    <div id="pai"> <!-- P  A  I  -->

        <div id="baner"><!-- b a n e r  -->

            <a class="logomarca" href="index.jsp"></a>


            <% if (Logado != null) { //usu·rio logado na sess?o %>

            <div id="painelUsuario"><!-- P  A  I  N  E  L ...  U  S  U  A  R  I  O ....  L  O  G  A  D  O    -->

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

                <!-- p e r f i l   -->
                <a class="perfil" href="editar_perfil.jsp" ></a>
                <!-- p e r f i l   -->

                <!-- m e n s a g e n s   -->
                <a class="mensagens" href="minhas_mensagens.jsp" ></a>
                <!-- m e n s a g e n s   -->

                <!-- i t e n s   -->
                <a class="itens" href="itens.jsp" ></a>
                <!-- i t e n s   -->

                <!-- a r o u n d   -->
                <a class="around" href="around.jsp" ></a>
                <!-- a r o u n d   -->

                <!-- h i s td   -->
                <a class="hist" href="historico.jsp" ></a>
                <!-- H I S T   -->

                <% if (Logado.getNome().equals(admin)) {%>
                <!-- a d m i n    -->
                <a class="admin" href="manter_parametros.jsp" ></a>
                <!-- a d m i n    -->

                <!-- m a n t e r    -->
                <a class="manter" href="manter.jsp" ></a>
                <!-- m a n t e r    -->

                <!-- r e l a t o r i o    -->
                <a class="relatorio" href="relatorio.jsp" ></a>
                <!-- r e l a t o r i o    -->                
                <%}%>

            </div><!-- P  A  I  N  E  L ...  U  S  U  A  R  I  O ....  L  O  G  A  D  O    -->

            <a class="logout" href="LogoutServlet" ></a>
            <a class="buscaDoacao" href="lista_doacoes.jsp" ></a>
            <a class="buscaTroca" href="lista_trocas.jsp" ></a>

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

                                                <%if (Logado  == null && forum == false) {%>

                                                    <jsp:include page="login.jsp">
                                                        <jsp:param name="opcao" value="2"/>
                                                    </jsp:include>                                                
                                                
                                                <%} else {%>

                                                    <div class="mensagens_painel" >
                                                        <% foto = ControladorUsuario.pegarFotoUsuario(Logado.getIdUsuario().toString());
                                                            boolean fotoAprovada = ControladorUsuario.verificarSeFotoFoiAprovada(Logado.getIdUsuario().toString());

                                                            if (fotoAprovada) {%>

                                                        <img src="<%=foto%>">

                                                        <%} else {%>
                                                        <img src="/img/foto_default.png" >

                                                        <%}%>

                                                        <span><h1 style="color: #999999;position: relative;padding: 0px; left: 40px;"><%=Logado.getNome()%></h1></span>

                                                        <% if (forum) {%> <!-- VERIFICA SE O FORUM ESTA ATIVO -->
                                                        <form name="form1resp" method="POST" action="RespostasServlet">
                                                            <input type="text" name="txt_resposta"/>
                                                            <input type="submit" class="btn-cad"  value="ENVIAR" onclick="return validarResp()"/>
                                                            <input type="hidden" name="id_forum" value="<%= idForum%>"/>
                                                            <input type="hidden" name="id_usuario" value="<%= Logado.getIdUsuario()%>"/>

                                                        </form>
                                                        <form method="POST" action="CancelarTrocaServlet">
                                                            <input type="hidden" name="id_forum" value="<%= idForum%>"/>
                                                            <input type="submit" class="btn-cad"  value="cancelar troca" onclick="return confirm('Confirma cancelamento ?')"/>
                                                        </form>
                                                        <form method="POST" action="FinalizarTrocaServlet">
                                                            <input type="hidden" name="id_forum" value="<%= idForum%>"/>
                                                            <input type="submit" class="btn-cad" value="finalizar troca" onclick="return confirm('Confirma troca finalizada ?')"/>
                                                        </form>
                                                        <%}%>
                                                    </div>

                                                    <div id="respostaForum">

                                                        <% for (RespostaEntidade r : lst) {%>



                                                        <% String fot = ControladorUsuario.pegarFotoUsuario(r.getIdUsuarioRemetente().toString());
                                                            boolean fotoApro = UsuarioDAO.verificaSeFotoFoiAprovada(r.getIdUsuarioRemetente().toString());
                                                            String nomeUsu = ControladorUsuario.pegarNomeProponenteTroca(r.getIdUsuarioRemetente().toString());
                                                        %>
                                                        <div style="position: relative;left: 30px; top: 10px;border: 1px; border-bottom-style: dashed;">
                                                            <% if (fotoApro) {%>
                                                            <img src="<%= fot%>" width="50" height="50"/><%=nomeUsu %>
                                                            
                                                            <%} else {%>
                                                                    <img src="/img/foto_default.png" width="50" height="50" ><%=nomeUsu %>
                                                                    
                                                                    <%}%>
                                                           
                                                            <h2><%=r.getTextoResposta()%> ::</h2>
                                                            <%=fronteira.FormataDataHtml.formatarDataParaDisplay(r.getDataResposta().toString())%> 

                                                        </div></br>




                                                        <%
                                                                if (!idUsuario.equals(r.getIdUsuarioRemetente())) {
                                                                    ControladorResposta.alterarRespostaParaLida(r.getIdResposta().toString());
                                                                }

                                                            }%>


                                                    </div>
                                                    <%}%>  <!-- -->
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
