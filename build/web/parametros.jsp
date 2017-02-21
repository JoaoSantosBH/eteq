<%-- 
    Document   : index
    Created on : Sep 20, 2013, 4:19:46 PM
    Author     : joaomarcelo
--%>
<%@page import="persistencia.ParametrosDAO"%>
<%@page import="entidade.UsuarioEntidade"%>
<%@page import="entidade.AdminEntidade"%>
<%@page import="persistencia.AdminDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="estilo.css"/> 

        <script language="javascript" type="text/javascript">
            function validaAnuncio() {

                var validade = anuncio.txt_validade.value;
                if (validade === "") {
                    alert('Digite um tempo de validade em dias');
                    anuncio.txt_validade.focus();
                    return false;
                }
                var count = anuncio.txt_contador.value;
                if (count === "") {
                    alert('Digite o numero de avisos');
                    anuncio.txt_contador.focus();
                    return false;
                }
            }


            function validaAnuncio2() {

                var larg = anuncio2.txt_largurapat.value;
                if (larg === "") {
                    alert('Digite a largura da foto');
                    anuncio2.txt_largurapat.focus();
                    return false;
                }
                var alt = anuncio2.txt_alturapat.value;
                if (alt === "") {
                    alert('Digite a altura da foto');
                    anuncio2.txt_alturapat.focus();
                    return false;
                }
            }
            
            function validaAnuncio3() {

                var larg = anuncio3.txt_largura.value;
                if (larg === "") {
                    alert('Digite a largura da foto');
                    anuncio3.txt_largura.focus();
                    return false;
                }
                var alt = anuncio3.txt_altura.value;
                if (alt === "") {
                    alert('Digite a altura da foto');
                    anuncio3.txt_altura.focus();
                    return false;
                }
            }
            function validaAnuncio4() {

                var resp = anuncio4.txt_resposta.value;
                if (resp === "") {
                    alert('Digite o tempo max de resposta');
                    anuncio4.txt_resposta.focus();
                    return false;
                }
                var con = anuncio4.txt_aviso.value;
                if (con === "") {
                    alert('Digite o numero de avisos');
                    anuncio4.txt_aviso.focus();
                    return false;
                }
            }
        </script>

        <title>EU Tenho EU Quero</title>
        <%
            String admin = AdminDAO.pegarNomeAdmin();
            UsuarioEntidade Logado = (UsuarioEntidade) session.getAttribute("UsuarioLogado");
            String Erro = request.getParameter("e");
            if (Erro == null) {
                Erro = "";
            }

        %>
        <% String Codmsg = request.getParameter("msg");
            if (Codmsg == null) {
                Codmsg = "";
            }
        %>
    </head>
    <body>

        <% if (Logado != null && Logado.getNome().equals(admin)) 
            
{%>




        <div id="cadastro_patrim_container">

            <div id="col01manter_param">

                <span style="text-align: center;"><h1>Validade Anuncio</h1></span>
                <form name="anuncio" action="ParametrosAnuncioServlet" method="post">

                    <input type="text" placeholder="<%=ParametrosDAO.pegarValorParametro("1") + " dias" %>"  name="txt_validade" /> <br/>
                    <input type="hidden" value="1" name="id_validade">

                    <input  type="text" placeholder="<%=ParametrosDAO.pegarValorParametro("2") + " avisos" %>" name="txt_contador" /> <br/>
                    <input type="hidden" value="2" name="id_contador">
                    <input type="submit" value="salvar" class="btn_cad" onclick="return validaAnuncio()"/> 

                </form>   
            </div>

            <div id="col02manter_param">

                <span style="text-align: center;"> <h1>Foto Patrimonio</h1></span>

                <form name="anuncio2" action="ParametrosFotoPatrimonioServlet" method="post">

                    <input type="text" placeholder="<%=ParametrosDAO.pegarValorParametro("3") + " x largura" %>" name="txt_largurapat" /> <br/>
                    <input type="hidden" value="3" name="id_largfotopat">

                    <input type="text" placeholder="<%=ParametrosDAO.pegarValorParametro("4") + " x altura" %>" name="txt_alturapat" /> <br/>
                    <input type="hidden" value="4" name="id_altfotopat">

                    <input type="submit" value="salvar" class="btn_cad" onclick="return validaAnuncio2()"/> 

                </form>   
            </div>
            <div id="col03manter_param">

                <span style="text-align: center;"> <h1>Foto Perfil</h1></span>

                <form name="anuncio3" action="ParametrosFotoPerfilServlet" method="post">

                    <input type="text" placeholder="<%=ParametrosDAO.pegarValorParametro("5") + " x largura" %>" name="txt_largura" /> <br/>
                    <input type="hidden" value="5" name="id_largfoto">

                    <input  type="text" placeholder="<%=ParametrosDAO.pegarValorParametro("6") + " x altura" %>" name="txt_altura" /> <br/>
                    <input type="hidden" value="6" name="id_altfoto">

                    <input type="submit" value="salvar" class="btn_cad" onclick="return validaAnuncio3()"/> 

                </form>   
            </div>
            <div id="col04manter_param">
                <span style="text-align: center;"><h1>Negociacao Inativa</h1></span> 

                <form name="anuncio4" action="ParametrosNegociacaoInativaServlet" method="post">

                    <input type="text" placeholder="<%=ParametrosDAO.pegarValorParametro("7") + " dias" %>" name="txt_resposta" /> <br/>
                    <input type="hidden" value="7" name="id_inativo">

                    <input  type="text" placeholder="<%=ParametrosDAO.pegarValorParametro("8") + " avisos" %>" name="txt_aviso" /> <br/>
                    <input type="hidden" value="8" name="id_conta_aviso">

                    <input type="submit" value="salvar" class="btn_cad" onclick="return validaAnuncio4()"/> 

                </form> 



            </div>


            <% if (Codmsg.equals("")) {%>

            <h3 id="texto" style="color: white; text-align: left;">

                <% } else if (Codmsg.equals("1")) {%>

                <h2 style="color: #669900; font-family:Gill, Helvetica, sans-serif "> Parametros atualizados com sucesso! </h2>

                <% } else if (Codmsg.equals("2")) {%>

                <h2 style="color: red; font-family:Gill, Helvetica, sans-serif "> <%= request.getParameter("excep")%></h2>
                <h3 id="texto" style="color: white; text-align: left;">

                </h3>

                <% } else if (Codmsg.equals("3")) {%>

                <h2  style="color: #ff9966; font-family:Gill, Helvetica, sans-serif ">Digite os valores corretos, apenas números <h2>
                        



                            <% }%>





                            </div>
                            <% } else { %>
                            <h1><img style="top: 30%;left: 45%;" src="img/error_acesso_restrito.png"/></h1>
                            <input type="button" onclick="javascript:window.open('index.jsp', '_self', '');"  class="btn_cad" value="voltar" />
                            <%}%>

                            </body>
                            </html>
