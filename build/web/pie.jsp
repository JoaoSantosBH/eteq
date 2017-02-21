<%-- 
    Document   : pie
    Created on : May 2, 2014, 4:00:40 PM
    Author     : joaosantos
--%>

<%@page import="persistencia.AdminDAO"%>
<%@page import="entidade.UsuarioEntidade"%>
<%@page import="entidade.PatrimonioEntidade"%>
<%@page import="fronteira.EmitirRelatorio"%>
<%@page import="fronteira.MetodosParaODWR"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script type='text/javascript' src='/dwr/interface/MetodosParaODWR.js'></script>
        <script type='text/javascript' src='/dwr/engine.js'></script>
        <script type='text/javascript' src='/dwr/util.js'></script>
        <script type="text/javascript" src="https://www.google.com/jsapi"></script>
        <script>


        </script>
        <script type="text/javascript">
            google.load("visualization", "1", {packages: ["corechart"]});
            google.setOnLoadCallback(drawChart);

            function drawChart() {

                var vetorVindoJava = <%= EmitirRelatorio.emitirRelatorioAnunciosConcluidos()%>
                var data = google.visualization.arrayToDataTable(vetorVindoJava);

                var options = {
                    title: 'Relatorio Negociacoes - EuTenhoEuQuero',
                    is3D: true,
                    backgroundColor: '#d8d7d2',
                };

                var chart = new google.visualization.PieChart(document.getElementById('piechart'));
                chart.draw(data, options);
            }
        </script>
        <%
            String admin = AdminDAO.pegarNomeAdmin();
            UsuarioEntidade Logado = (UsuarioEntidade) session.getAttribute("UsuarioLogado");
            String foto = "";

            PatrimonioEntidade p = null;
            String Erro = request.getParameter("e");
            if (Erro == null) {
                Erro = "";
            }

        %>
    </head>
    <body>
        <% if (Logado != null && Logado.getNome().equals(admin)) { %>
        <div id="piechart" style="width: 1000px; height: 435px; position: relative; background: transparent;"></div>
        <%} else {%>
        <jsp:include page="login.jsp">
            <jsp:param name="opcao" value="2"/>
        </jsp:include>  
        <%}%>
    </body>
</html>