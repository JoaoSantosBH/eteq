<%-- 
    Document   : login
    Created on : Sep 20, 2013, 4:31:26 PM
    Author     : joaomarcelo
--%>
<%@page import="org.apache.catalina.User"%>
<%@page import="entidade.UsuarioEntidade"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script type="text/javascript" src="http://gettopup.com/releases/latest/top_up-min.js"></script>

        <!-- L O G I N   F A C E B O O K-->
        <script>

            // This is called with the results from from FB.getLoginStatus().
            function statusChangeCallback(response) {
                console.log('statusChangeCallback');
                console.log(response);
                // The response object is returned with a status field that lets the
                // app know the current login status of the person.
                // Full docs on the response object can be found in the documentation
                // for FB.getLoginStatus().
                if (response.status === 'connected') {
                    // Logged into your app and Facebook.

                    console.log(response.authResponse.accessToken);
                    window.location = "http://www.eutenhoeuquero.com.br/LoginWithFacebookServlet?token=" + response.authResponse.accessToken;

                } else if (response.status === 'not_authorized') {
                    // The person is logged into Facebook, but not your app.
                    document.getElementById('status').innerHTML = 'Please log ' +
                            'into this app.';
                } else {
                    // The person is not logged into Facebook, so we're not sure if
                    // they are logged into this app or not.
                    document.getElementById('status').innerHTML = 'Please log ' +
                            'into Facebook.';
                }
            }

            // This function is called when someone finishes with the Login
            // Button.  See the onlogin handler attached to it in the sample
            // code below.
            function checkLoginState() {
                FB.getLoginStatus(function(response) {
                    statusChangeCallback(response);
                });
            }

            window.fbAsyncInit = function() {
                FB.init({
                    appId: '1591094347781520',
                    cookie: true, // enable cookies to allow the server to access 
                    // the session
                    xfbml: true, // parse social plugins on this page
                    version: 'v2.0' // use version 2.0
                });

                // Now that we've initialized the JavaScript SDK, we call 
                // FB.getLoginStatus().  This function gets the state of the
                // person visiting this page and can return one of three states to
                // the callback you provide.  They can be:
                //
                // 1. Logged into your app ('connected')
                // 2. Logged into Facebook, but not your app ('not_authorized')
                // 3. Not logged into Facebook and can't tell if they are logged into
                //    your app or not.
                //
                // These three cases are handled in the callback function.

                FB.getLoginStatus(function(response) {
                    statusChangeCallback(response);
                });

            };

            // Load the SDK asynchronously
            (function(d, s, id) {
                var js, fjs = d.getElementsByTagName(s)[0];
                if (d.getElementById(id))
                    return;
                js = d.createElement(s);
                js.id = id;
                js.src = "//connect.facebook.net/en_US/sdk.js";
                fjs.parentNode.insertBefore(js, fjs);
            }(document, 'script', 'facebook-jssdk'));

            // Here we run a very simple test of the Graph API after login is
            // successful.  See statusChangeCallback() for when this call is made.



        </script>
        <!-- L O G I N   F A C E B O O K-->




        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" media="screen" href="login.css"/>
        <script language="javascript" type="text/javascript">
            function validar() {
                var email = form1.txt_email.value;
                if (email === "") {
                    alert('Digite um email');
                    form1.txt_email.focus();
                    return false;
                }
                var senha = form1.txt_senha.value;
                if (senha === "") {
                    alert('Preencha a senha');
                    form1.txt_senha.focus();
                    return false;
                }


            }
        </script>
        <title>ETEQ</title>
        <% String Erro = request.getParameter("e");
            if (Erro == null) {
                Erro = "";
            }
            UsuarioEntidade Logado = (UsuarioEntidade) session.getAttribute("UsuarioLogado");
        %>

    </head>
    <body>

        <div id="login">
            <span><h1 style="color: #999999;position: relative;left: 0px;">Acesse sua conta</h1></span>

            <div id="embrulho">

                <form name="form1" method="post" action="LoginServlet">

                    <br/><input placeholder="E-MAIL" type="text" name="txt_email" /><br/>
                    <br/><input placeholder="SENHA" type="password" name="txt_senha" /><br/>
                    <input type="submit" value="login" class="btn-log" onclick="return validar()"/>


                </form>   

            </div>
            <a href="senha.jsp" toptions="type = iframe, width = 500, height = 150,resizable=0, modal=1" >esqueci minha senha</a></br>
            <!-- L O G I N   F A C E B O O K-->


            <fb:login-button autologoutlink="true" scope="public_profile,email" onlogin="checkLoginState();"></fb:login-button>



            <!--<div class="fb-login-button" data-max-rows="1" data-size="icon" onclick="checkLoginState();" data-show-faces="false" data-auto-logout-link="false"></div>-->


            <!-- L O G I N   F A C E B O O K-->

            <% if (Erro.equals("1")) {%>
            <h2 style="color:#ff9966; font-family:Gill, Helvetica, sans-serif"> Nome ou Senha Incorretos. </h2> 

            <% }
                    if (Erro.equals("2")) {%>
            <h2 style="color: #ff9966; font-family:Gill, Helvetica, sans-serif "> Erro no Banco de Dados. </h2> 

            <% } else if (Erro.equals("3")) {%>
            <h2 style="color: #ff9966; font-family:Gill, Helvetica, sans-serif "> Usuario nao confirmado </h2>
            <% }%>


        </div>






    </body>
</html>
