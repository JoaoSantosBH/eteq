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


        <script type="text/javascript">
            var loadstart = function(data) {

                dwr.util.removeAllOptions('estados');
                dwr.util.addOptions('estados', data, 'id', 'nome');

            }
        </script>

        <script type='text/javascript'>
            var callback = function(c) {
            }
            function carregaCidades() {

                var estado = dwr.util.getValue('estados');
                MetodosParaODWR.pegarCidadesDWR(estado, scallback);
            }
            var scallback = function(data) {
                var size = data.length;
                if (size === 0) {
                    alert("Nothing found");

                } else {
                    dwr.util.removeAllOptions('cidades');

                    dwr.util.addOptions('cidades', data, 'id', 'nome');
                }
            }
        </script>

        <script language="javascript" type="text/javascript">
            //adiciona mascara ao telefone
            function MascaraTelefone(tel) {
                if (mascaraInteiro(tel) == false) {
                    event.returnValue = false;
                }
                return formataCampo(tel, '0000-0000', event);
            }
            function MascaraCep(cep) {
                if (mascaraInteiro(cep) == false) {
                    event.returnValue = false;
                }
                return formataCampo(cep, '00000-000', event);
            }
            //valida telefone
            function ValidaTelefone(tel) {
                exp = /(\d{4})-(\d{4})/g
                if (!exp.test(tel.value))
                    alert('Numero de Telefone Invalido!');

            }
            function ValidaDDD(ddd) {
                exp = /(\d{4})/g
                if (!exp.test(tel.value))
                    alert('Numero de DDD Invalido!');

            }
            function MascaraDDD(ddd) {
                if (mascaraInteiro(ddd) == false) {
                    event.returnValue = false;
                }
                return formataCampo(ddd, '000', event);
            }
            //valida numero inteiro com mascara
            function mascaraInteiro() {
                if (event.keyCode < 48 || event.keyCode > 57) {
                    event.returnValue = false;
                    return false;
                }
                return true;
            }



            //formata de forma generica os campos
            function formataCampo(campo, Mascara, evento) {
                var boleanoMascara;

                var Digitato = evento.keyCode;
                exp = /\-|\.|\/|\(|\)| /g
                campoSoNumeros = campo.value.toString().replace(exp, "");

                var posicaoCampo = 0;
                var NovoValorCampo = "";
                var TamanhoMascara = campoSoNumeros.length;
                ;

                if (Digitato !== 8) { // backspace 
                    for (i = 0; i <= TamanhoMascara; i++) {

                        boleanoMascara = ((Mascara.charAt(i) === "-"))

                        boleanoMascara = boleanoMascara

                        if (boleanoMascara) {
                            NovoValorCampo += Mascara.charAt(i);
                            TamanhoMascara++;
                        } else {
                            NovoValorCampo += campoSoNumeros.charAt(posicaoCampo);
                            posicaoCampo++;
                        }
                    }
                    campo.value = NovoValorCampo;
                    return true;
                } else {
                    return true;
                }
            }
            function validafoto() {
                var foto = fotografia.foto.value;
                if (foto === "") {
                    alert('Selecione uma foto para o perfil');
                    fotografia.foto.focus();
                    return false;
                }
            }
            function validar() {

                var logradouro = form1.txt_logradouro.value;
                if (logradouro === "") {
                    alert('Preencha o logradouro');
                    form1.txt_logradouro.focus();
                    return false;
                }
                var num = form1.txt_numero.value;
                if (num === "") {
                    alert('Preencha o numero');
                    form1.txt_numero.focus();
                    return false;
                }
                var estado = form1.estados.value;
                if (estado === "0") {
                    alert('Selecione um estado');
                    form1.estados.focus();
                    return false;
                }

                var bairro = form1.txt_bairro.value;
                if (bairro === "") {
                    alert('Preencha o bairro');
                    form1.txt_bairro.focus();
                    return false;
                }
                var cep = form1.txt_cep.value;
                if (cep === "") {
                    alert('Preencha o cep');
                    form1.txt_cep.focus();
                    return false;
                }
                var ddd = form1.txt_ddd.value;
                if (ddd === "") {
                    alert('Preencha o ddd');
                    form1.txt_ddd.focus();
                    return false;
                }
                var fixo = form1.txt_fixo.value;
                if (fixo === "") {
                    alert('Preencha o telefone');
                    form1.txt_fixo.focus();
                    return false;
                }
                var cel = form1.txt_cel.value;
                if (cel === "") {
                    alert('Preencha o celular');
                    form1.txt_cel.focus();
                    return false;
                }
            }
        </script>
        <script>
            function mascara(src, mask) {
                var i = src.value.length;
                var saida = mask.substring(0, 1);
                var texto = mask.substring(i)
                if (texto.substring(0, 1) !== saida)
                {
                    src.value += texto.substring(0, 1);
                }
            }
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    </head>
    <body onload="MetodosParaODWR.pegarTodosEstadosDWR(loadstart);">

        <div id="cadastro_pessoal_container">
            <% if (Logado != null) {%>
            <div id="col1_cadastro_pessoal">


                <% String foto = ControladorUsuario.pegarFotoUsuario(Logado.getIdUsuario().toString());
                    boolean fotoAprovada = ControladorUsuario.verificarSeFotoFoiAprovada(Logado.getIdUsuario().toString());

                    if (fotoAprovada) {%>

                <img src="<%=foto%>" >

                <%} else {%>

                <img src="/img/foto_default.png" >

                <% }%>
                <span>  <h1 style="color: #999999;position: relative;padding: 0px; left: 40px;"><%=Logado.getNome()%></h1></span>
                <form name="fotografia" method="POST" action="EditarPerfiFotoUploadlServlet" enctype="multipart/form-data">
                    Escolha uma foto: (formatos .BMP ou .JPG apenas)
                    <input type="file" name="foto" style="background: transparent;color:#FFFFFF;"/>
                    <input type="hidden" name="id_usuario" value="<%=Logado.getIdUsuario()%>" >
                    <input type="submit" value="SalvarFoto" class="btn_salvar" onclick="return validafoto()">
                </form></br>


            </div>





            <div id="col2_cadastro_pessoal">

                <!--                <div id="troca"> -->
                <img src="img/msg_edita_perfil.png" style="top: 0px;;position: relative; width: 200px; height: 200px; "/>
                <img src="img/instruperfil.png"/>
                <!--                </div>-->


                <% if (Codmsg.equals("1")) {%>

                <!--                <img src="img/sucesso_perfil_alter.png"/>-->
                `               <h2 style="color: #33cc00">PERFIL EDITADO COM SUCESSO!!</h2>

                <% } else if (Codmsg.equals("2")) {%>

                <!--<img src="img/error_inesperado.png"/>-->
                <h2 style="color: red">ERRO INESPERADO!!</h2>
                <% } else if (Codmsg.equals("3")) {%>

                <!--<img src="img/error_bd.png"/>-->
                <h2 style="color: red">ERRO NO BANCO DE DADOS, POR FAVOR TENTE NOVAMENTE MAIS TARDE!!</h2>

                <% } else if (Codmsg.equals("4")) {%>

                <!--<img src="img/error_formato.png"/>-->
                <h2 style="color: red">FORMATO INADEQUADO DA FOTO!!</h2>

                <% } else if (Codmsg.equals("5")) {%>

                <!--<img src="img/error_formato.png"/>-->
                <h2 style="color: #33cc00">FOTO ALTERADA COM SUCESSO</br> AGUARDANDO MODERACAO</h2>

                <% }%>
                

            </div>

            <div id="col3_cadastro_pessoal">

                <form name="form1" action="EditarDadosPerfilServlet" method="post" >






                    <input size="32" placeholder="LOGRADOURO" type="text" maxlength="45" name="txt_logradouro"/> <br/>
                    <input size="10" placeholder="NUMERO" type="text" name="txt_numero" maxlength="11" /> <br/>
                    <input size="32" placeholder="COMPLEMENTO" type="text" name="txt_complemento" maxlength="45"  /> <br/>
                    <input size="32" placeholder="BAIRRO" type="text" name="txt_bairro"  maxlength="45" /> <br/>
                    <input size="32" placeholder="CEP" type="text" name="txt_cep"  maxlength="9" onKeyPress="MascaraCep(form1.txt_cep);" /> <br/>

                    <select name="estados" id="estados" onchange="carregaCidades()"></select>
                    <select name="cidades" id="cidades"></select>

                    </br>
                    <input size="4" placeholder="DDD" type="text" name="txt_ddd" maxlength="3" onKeyPress="MascaraDDD(form1.txt_ddd);"  /> </br>
                    <input size="9" placeholder="FIXO" type="text" name="txt_fixo" maxlength="9" onKeyPress="MascaraTelefone(form1.txt_fixo);"   />
                    <!--<input size="9" placeholder="FIXO" type="text" name="txt_fixo" maxlength="9" onKeyPress="MascaraTelefone(form1.txt_fixo);"  onBlur="ValidaTelefone(form1.txt_fixo);" />-->
                    <input size="10" placeholder="CELULAR" type="text" name="txt_cel" maxlength="9" onKeyPress="MascaraTelefone(form1.txt_cel);"  /> </br>
                    <!--<input size="10" placeholder="CELULAR" type="text" name="txt_cel" maxlength="9" onKeyPress="MascaraTelefone(form1.txt_cel);"  onBlur="ValidaTelefone(form1.txt_cel);"/> </br>-->

                    <input type="hidden" name="id_usuario" value="<%=Logado.getIdUsuario()%>" ></br>
                    <input type="submit" value="Salvar" class="btn_salvar" onclick="return validar()">
                </form></br>
                <form name="exc" action="excluir_perfil.jsp"><input type="submit" value="Excluir perfil!" class="btn_salvar" onclick="return confirm('TEM CERTEZA ?')"></form>
            </div>




            <br/>

            <%} else {%>


            <jsp:include page="login.jsp">
                <jsp:param name="opcao" value="2"/>
            </jsp:include>

            <%}%>

        </div> 


    </body>
</html>
