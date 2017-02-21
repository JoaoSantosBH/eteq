<%-- 
    Document   : newjsp
    Created on : Nov 16, 2013, 4:15:19 PM
    Author     : joaomarcelo
--%>
<%@page import="entidade.CategoriaEntidade"%>
<%@page import="entidade.SubcategoriaEntidade"%>
<%@page import="entidade.ItensSubcategoriaEntidade"%>
<%@page import="persistencia.CategoriaDAO"%>
<%@page import="persistencia.SubCategoriaDAO"%>
<%@page import="persistencia.ItensSubcategoriaDAO"%>
<%@page import="java.util.ArrayList"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="estilo.css"/> 
        <title>ETEQ</title>
    </head>
    <body>

        <% try {

                ArrayList<SubcategoriaEntidade> listaSubCateg = null;
                ArrayList<ItensSubcategoriaEntidade> listaItens = null;
                listaSubCateg = persistencia.SubCategoriaDAO.getSubCategoriasMulheres();
        %>

        <div id="mulheres_subcategorias">

            <%  Integer i = 1;
                for (SubcategoriaEntidade S : listaSubCateg) {// CRIANDO COLUNAS SUBCATEGORIAS
                    String palavra = "mul_sub_0" + i.toString();

                    if (i == listaSubCateg.size()) {
                        palavra = "mul_sub_" + i.toString();

                    }%>
            <div id="<%= palavra%>"><%= S.getTituloSubcategoria()%></div>
            <%  i++;
                }%>
        </div>

        <div id="mulheres_conteudo">


            <% Integer s = 1;//id do iten
                
                for (SubcategoriaEntidade Sub : listaSubCateg) {//CRIANDO AS COLUNAS COM OS ITENS
                    String nome = "mulheres_col_0" + s.toString();
                    if (s == 10) {
                        nome = "mulheres_col_" + s.toString();
                    }
                    listaItens = persistencia.ItensSubcategoriaDAO.getIntensSubCategoriasPorId(1, s);
            %>

            <div id="<%=nome%>">

                <!--AQUI FOR dos ITENS_SUB-->
                <% int z = 1;
                    for (ItensSubcategoriaEntidade iten : listaItens) {
                        String link = "resultado_lista.jsp?id_cat=" +iten.getIdCategoria() + "&id_itens_sub=" + iten.getIdItensSubcategoria();

                %>

                <div style="width:100px; height: 30px; ;left:<%= s * 10%>px; top: 20px;">
                    <a href="<%=link%>"><%= iten.getNomeItens()%></a>
                </div>

                <%z++;
                    }%>
            </div>
            <% s++;
                }%>


        </div>


        <% } catch (Exception e) {%>

        <h2> Erro Inesperado. Contate o Administrador do Sistema. </h2>
        <p> <%= e%> </p>

        <% }%>
    </body>
</html>
