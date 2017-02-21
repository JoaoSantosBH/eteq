<%-- 
    Document   : index
    Created on : Sep 20, 2013, 4:19:46 PM
    Author     : joaomarcelo
--%>
<%@page import="controle.ControladorUsuario"%>
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
        <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'></script>
        <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/MetodosParaODWR.js'></script>
        <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script>

        <script>
            function validarEdit() {
                var foto = formularioCadastro.foto.value;
                if (foto === "") {
                    alert('Escolha uma foto');
                    formularioCadastro.foto.focus();
                    return false;
                }
                var titulo = formularioCadastro.txt_titulo.value;
                if (titulo === "") {
                    alert('Digite o titulo');
                    formularioCadastro.txt_titulo.focus();
                    return false;
                }
                var desc = formularioCadastro.txt_descricao.value;
                if (desc === "") {
                    alert('Descreva o anuncio');
                    formularioCadastro.txt_descricao.focus();
                    return false;
                }
                var tipo = formularioCadastro.txt_tipo.value;
                if (tipo === "quero") {
                    alert('Selecione o tipo de negociacao');
                    formularioCadastro.txt_tipo.focus();
                    return false;
                }
                var categoria = formularioCadastro.txt_categoria.value;
                if (categoria === "0") {
                    alert('Selecione uma categoria');
                    formularioCadastro.txt_categoria.focus();
                    return false;
                }
                var subcategoria = formularioCadastro.txt_subcategoria.value;
                if (subcategoria === "0") {
                    alert('Selecione uma subcategoria');
                    formularioCadastro.txt_subcategoria.focus();
                    return false;
                }
                var item = formularioCadastro.txt_itens_subcategoria.value;
                if (item === "0") {
                    alert('Selecione um item');
                    formularioCadastro.txt_itens_subcategoria.focus();
                    return false;
                }
                var estado = formularioCadastro.txt_estado.value;
                if (estado === "") {
                    alert('Descreva o estado de conservacao');
                    formularioCadastro.txt_estado.focus();
                    return false;
                }

                var palavra = formularioCadastro.txt_palavrachave.value;
                if (palavra === "") {
                    alert('Digite uma palavra chave para seu anuncio');
                    formularioCadastro.txt_palavrachave.focus();
                    return false;
                }

            }
        </script>
        <!-- d w r --=============================================================================================-->
        <script type="text/javascript">
            var loadstart = function(data) {

                dwr.util.removeAllOptions('txt_categoria');
                dwr.util.addOptions('txt_categoria', data, 'idCategoria', 'titulo');

            }
        </script>

        <script type='text/javascript'>
            var callback = function(c) {

            }
            function carregaSubCat() {

                var categoria = dwr.util.getValue('txt_categoria');
                MetodosParaODWR.pegarSubCategoriasPeloId(categoria, scallback);
            }
            var scallback = function(data) {
                var size = data.length;
                if (size === 0) {

                } else {
                    dwr.util.removeAllOptions('txt_subcategoria');
                    dwr.util.removeAllOptions('txt_itens_subcategoria');
                    dwr.util.addOptions('txt_subcategoria', data, 'idSubcategoria', 'tituloSubcategoria');
                }
            }
            function carregaItensSubCat() {

                var itens = dwr.util.getValue('txt_subcategoria');
                var cat = dwr.util.getValue('txt_categoria');
                MetodosParaODWR.pegarItensSubcategoriaPeloId(cat, itens, scallback2);
            }
            var scallback2 = function(data) {
                var size = data.length;
                if (size === 0) {

                    alert("ZERO");
                } else {
                    dwr.util.removeAllOptions('txt_itens_subcategoria');
                    dwr.util.addOptions('txt_itens_subcategoria', data, 'idItensSubcategoria', 'nomeItens');
                }
            }
        </script>

        <!-- d w r --=============================================================================================-->


        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


        <%

            UsuarioEntidade Logado = (UsuarioEntidade) session.getAttribute("UsuarioLogado");
            String Erro = request.getParameter("e");
            if (Erro == null) {
                Erro = "";
            }

        %>

    </head>
    <body onload="MetodosParaODWR.pegarTodasCategoriasDWR(loadstart);">

        <div id="cadastro_patrim_container">
            <% String Codmsg = request.getParameter("msg");
                if (Codmsg == null) {
                    Codmsg = "";
                }
            %>

            <form name="formularioCadastro" action="cadastrarPatrimonioServlet" method="post" enctype="multipart/form-data">

                <div id="col1_cadastro_patrim">

          

                <% String foto = ControladorUsuario.pegarFotoUsuario(Logado.getIdUsuario().toString());
                                boolean fotoAprovada = ControladorUsuario.verificarSeFotoFoiAprovada(Logado.getIdUsuario().toString());

                                if (fotoAprovada) {%>

                                <img src="<%=foto%>" style="position: relative;top: 12px;left: -10px;">

                <%} else {%>
                <img src="/img/foto_default.png" style="position: relative;top: 12px;left: -10px;">

                <%}%>
                <span>  <h1 style="color: #999999;position: relative;padding: 0px; left: 30px;top: 12px;"><%=Logado.getNome()%></h1></span>
                

                </div>

                <div id="col2_cadastro_patrim">
                    <input type="file" name="foto" style="background: #ff6666;color: white;" ></BR>

                    <input size="32" placeholder="TITULO" type="text" name="txt_titulo" maxlength="45"  /> <br/>
                    <textarea rows="15" cols="30" placeholder="DESCRIÇÃO" draggable="false"  name="txt_descricao"></textarea>
                </div>

                <div id="col3_cadastro_patrim">
                    <img src="img/instrucaoET.png"/>
                    <select name="txt_tipo">
                        <option>quero</option>
                        <option value="0">doar</option>
                        <option value="1">trocar</option>
                    </select>

                    <select name="txt_categoria"  id="txt_categoria" onchange="carregaSubCat()"> 
                    </select>


                    <select name="txt_subcategoria" id="txt_subcategoria" onchange="carregaItensSubCat()">    
                    </select>      

                    <select name="txt_itens_subcategoria" id="txt_itens_subcategoria">    
                    </select>  

                    <input placeholder="ESTADO DE CONSERVAÇÃO" type="text" name="txt_estado" maxlength="45" /> 
                    <input placeholder="PALAVRA-CHAVE" type="text" name="txt_palavrachave" maxlength="45" /> 
                    <input type="hidden" name="id_usuario" value="<%=Logado.getIdUsuario()%>"/>
                    <input type="submit" value="cadastrar" class="btn_salvar" onclick="return validarEdit()">


                    <% if (Codmsg.equals("1")) {%>

                    <h2 style="color: #99ff00; font-family:Gill, Helvetica, sans-serif "> Seu patrimônio foi cadastrado com sucesso e está aguardando aprovacao! </h2>

                    <% } else if (Codmsg.equals("2")) {%>

                    <h2 style="color: #ff9966; font-family:Gill, Helvetica, sans-serif ">Erro inexperado. </h2>
                    

                        <% } else if (Codmsg.equals("3")) {%>

                        <h2 style="color: #ff9966; font-family:Gill, Helvetica, sans-serif ">Ocorreu um problema com o Banco de Dados, tente novamente mais tarde</h2>
                        

                            <% } else if (Codmsg.equals("4")) {%>

                            <h2 style="color: #ff9966; font-family:Gill, Helvetica, sans-serif ">Formato da foto deve ser JPG ou BMP</h2>
                            


                            <% }%>
                            </div>



                            </form><br/>



                            </div>  


                            </body>
                            </html>
