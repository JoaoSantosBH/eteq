<%-- 
    Document   : mensagens
    Created on : Mar 5, 2014, 11:31:21 AM
    Author     : joaosantos
--%>

<%@page import="controle.ControladorUsuario"%>
<%@page import="entidade.NegociacaoEntidade"%>
<%@page import="persistencia.NegociacaoDAO"%>
<%@page import="controle.ControladorResposta"%>
<%@page import="persistencia.UsuarioDAO"%>
<%@page import="entidade.UsuarioEntidade"%>
<%@page import="persistencia.ForumDAO"%>
<%@page import="entidade.ForumEntidade"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <!--TOPUP I FRAMES-->
        <script type="text/javascript" src="http://gettopup.com/releases/latest/top_up-min.js"></script>

    </head>
    <body>
        <% UsuarioEntidade Logado = (UsuarioEntidade) session.getAttribute("UsuarioLogado");
            String id = Logado.getIdUsuario().toString();
            String foto = UsuarioDAO.pegarAtributoFotoDeUsuario(id);

            int idUser = Logado.getIdUsuario();
            String idUserStr = Logado.getIdUsuario().toString();
            String Ordem = request.getParameter("ord");
            if (Ordem == null) {
                Ordem = "";
            }

            int Pagina = 0;
            String strPagina = request.getParameter("pag");
            if (strPagina != null) {
                Pagina = Integer.parseInt(strPagina);
            }

            int QuantidadeRegistros = 5;

            int TotalRegistros = ForumDAO.getQuantidadeTotalForum(idUser);
            int MaxPaginas = (TotalRegistros + QuantidadeRegistros - 1) / QuantidadeRegistros;

            ArrayList<ForumEntidade> lst = null;
            lst = ForumDAO.getForumPaginados(Pagina, QuantidadeRegistros, id);

            String Fonte = "minhas_mensagens.jsp";

        %>
            

        <div class="mensagens_painel" >


            <% foto = ControladorUsuario.pegarFotoUsuario(Logado.getIdUsuario().toString());
                boolean fotoAprovada = ControladorUsuario.verificarSeFotoFoiAprovada(Logado.getIdUsuario().toString());

                if (fotoAprovada) {%>

            <img src="<%=foto%>" >

            <%} else {%>
            <img src="/img/foto_default.png" >

            <%}%>
            <span><h1 style="color: #999999;position: relative;padding: 0px; left: 40px;"><%=Logado.getNome()%></h1></span>
        </div>
        <div id="div_mensagem">
            <h1>Minhas Mensagens</h1>

            <% if (lst.size() == 0) { %>
            <div id="troca"> 
                <img src="img/msgs_empty.png" style="top: 0px;"/>
            </div>            
            <%}%>
            <ul>
                <%

                    try {

                        for (ForumEntidade M : lst) {
                            String link = "respostas.jsp?id=" + M.getIdForum();

                            String id_remetente = M.getIdUsuarioRemetente().toString();
                            String remetente = UsuarioDAO.pegarAtributoNomeDeUsuarioPeloId(id_remetente);

                            NegociacaoEntidade n = NegociacaoDAO.selecionarNegociacaoPorId(M.getIdNegociacao().toString());

                            if (n.getIdProdutoQuer() == 0) {
                                link = "respostas_doa.jsp?id=" + M.getIdForum();
                            }
                            boolean naoLidas = ControladorResposta.verificarSeExisteRespostaNaoLida(M.getIdUsuarioDestinatario().toString());
                            Boolean ativos = ForumDAO.verificarSeForumEstaAtivo(M.getIdForum().toString());
                            if (ativos) {%>

                <% if (!naoLidas) {
                %>
                <li> <span><a style="color: #000000;" href="<%=link%>" > <%=M.getAssunto()%> </a></span></li>
                    <%} else {%>
                <li> <a style="color: #ff9966; " href="<%=link%>" >  <%=M.getAssunto()%> </a></li>
                    <%}%>



                <% }
                    }%>
            </ul>


            <% if (Pagina != 0) {%>
            <a href="<%= Fonte%>?pag=<%= Pagina - 1%>"> <img src="img/msgs_prev.png/"> </a> 
            <% } %>

            <% for (int p = 0; p < MaxPaginas; p++) { %>

            <% if (p != Pagina) {%>
            <a href="<%= Fonte%>?pag=<%= p%>">  <%= p + 1%> </a>
            <% } else {%>
            <%= p + 1%>
            <% } %>

            <% } %>

            <% if (Pagina < MaxPaginas - 1) {%>
            <a href="<%= Fonte%>?pag=<%= Pagina + 1%>"> <img src="img/msgs_next.png/"> </a>
            <% } %>


        </div>
         
        <% } catch (Exception e) {%>

        <h2> Erro Inesperado. Contate o Administrador do Sistema. </h2>
        <p> <%= e%> </p>

        <% }%>

    </body>
</html>
