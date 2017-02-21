<%-- 
    Document   : index
    Created on : Sep 20, 2013, 4:19:46 PM
    Author     : joaomarcelo
--%>
<%@page import="entidade.UsuarioEntidade"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="estilo.css"/> 
        <script>
            function validar() {
                var nome = form1.txt_nome.value;
                if (nome === "") {
                    alert('Digite o seu nome');
                    form1.txt_nome.focus();
                    return false;
                }
                var email = form1.txt_email.value;
                if (email === "") {
                    alert('Digite o seu email');
                    form1.txt_email.focus();
                    return false;
                }
                var email2 = form1.txt_email_confirma.value;
                if (email2 === "") {
                    alert('Confirme o email');
                    form1.txt_email_confirma.focus();
                    return false;
                }
                var senha = form1.txt_senha.value;
                if (senha === "") {
                    alert('Digite uma senha');
                    form1.txt_senha.focus();
                    return false;
                }
                form = document.forms[0]
                checkbox = form.checkbox
                if (concordo.checked === false) {
                    alert('Voce tem que concordar com os termos');
                    form1.li_concordo.focus();
                    return false;
                }

            }
        </script>

        <title>EU Tenho EU Quero</title>
        <%

            UsuarioEntidade Logado = (UsuarioEntidade) session.getAttribute("UsuarioLogado");
            String Erro = request.getParameter("e");
            if (Erro == null) {
                Erro = "";
            }

        %>

    </head>
    <body>

        <div id="cadastro">
            <% String Codmsg = request.getParameter("msg");
                if (Codmsg == null) {
                    Codmsg = "";
                }
            %>
            <h1>Cadastre-se</h1>

            <form name="form1" action="CadastroUsuarioServlet" method="post">

                <input placeholder="NOME" type="text" name="txt_nome" /> <br/>
                <input placeholder="E-MAIL" type="text" name="txt_email" /> <br/>
                <input placeholder="CONFIRMAR E-MAIL" type="text" name="txt_email_confirma" /> <br/>
                <input placeholder="SENHA" type="password" name="txt_senha" /><br/>

                <input type="submit" value="cadastrar"  onclick="return validar()" class="btn-cad"/> <!-- return validarCheckBox(concordo) esta inutilmente aqui-->
                <input type="reset" value="apagar" class="btn-cad"/>
                <input type="button" value="voltar" onclick="javascript:window.open('index.jsp', '_self', '');" class="btn-cad"/>

                <% if (Codmsg.equals("")) {%>

                <h3 id="texto" style="color: white; text-align: left;">
                    <input id="concordo" type="checkbox" name="li_concordo" style="width: 20px;"/> Eu li e concordo com os <a href="contrato_utilizacao.jsp" target="_blank">termos</a> de utilização do serviço</h3>

                <% } else if (Codmsg.equals("1")) {%>

                <h2 style="color: #669900; font-family:Gill, Helvetica, sans-serif "> Obrigado por se cadastrar em nosso sistema! Voce recebera um e-mail para que possa ativar sua conta e realizar o login! </h2>

                <% } else if (Codmsg.equals("2")) {%>

                <h2 style="color: #ff9966; font-family:Gill, Helvetica, sans-serif "> <%= request.getParameter("excep")%></h2>
                <h3 id="texto" style="color: white; text-align: left;">
                    <input type="checkbox" name="li_concordo" style="width: 20px;"/> Eu li e concordo com os <a href="contrato_utilizacao.jsp" target="_blank">termos</a> de utilização do serviço
                </h3>

                <% } else if (Codmsg.equals("3")) {%>

                <h2  style="color: #ff9966; font-family:Gill, Helvetica, sans-serif ">Erro no banco de dados. <h2>
                        <h3 id="texto" style="color: white; text-align: left;">
                            <input type="checkbox" name="li_concordo" style="width: 20px;"/> Eu li e concordo com os <a href="contrato_utilizacao.jsp" target="_blank">termos</a> de utilização do serviço</h3>

                        <% } else if (Codmsg.equals("4")) {%>

                        <h2 style="color: #ff9966; font-family:Gill, Helvetica, sans-serif ">Erro inexperado. </h2>
                        <h3 id="texto" style="color: white; text-align: left;">
                            <input type="checkbox" name="li_concordo" style="width: 20px;"/> Eu li e concordo com os <a href="contrato_utilizacao.jsp" target="_blank">termos</a> de utilização do serviço</h3>

                        <% } else if (Codmsg.equals("5")) {%>

                        <h2 style="color: #ff9966; font-family:Gill, Helvetica, sans-serif ">Favor preencher todos os campos </h2>
                        <h3 id="texto" style="color: white; text-align: left;">
                            <input type="checkbox" name="li_concordo" style="width: 20px;"/> Eu li e concordo com os <a href="contrato_utilizacao.jsp" target="_blank">termos</a> de utilização do serviço</h3>

                        <% } else if (Codmsg.equals("6")) {%>

                        <h2 style="color: #ff9966; font-family:Gill, Helvetica, sans-serif ">Os e-mails digitados não correspondem </h2>
                        <h3 id="texto" style="color: white; text-align: left;">
                            <input type="checkbox" name="li_concordo" style="width: 20px;"/> Eu li e concordo com os <a href="contrato_utilizacao.jsp" target="_blank">termos</a> de utilização do serviço
                        </h3>
                        <% } else if (Codmsg.equals("7")) {%>

                        <h2 style="color: #ff9966; font-family:Gill, Helvetica, sans-serif ">Voce tem que concordar com os termos de utilizacao </h2>
                        <h3 id="texto" style="color: white; text-align: left;">
                            <input type="checkbox" name="li_concordo" style="width: 20px;"/> Eu li e concordo com os <a href="contrato_utilizacao.jsp" target="_blank">termos</a> de utilização do serviço
                        </h3>

                        <% }%>


                        </form>   



                        </div>  


                        </body>
                        </html>
