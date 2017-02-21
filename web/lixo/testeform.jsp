<%-- 
    Document   : testeform
    Created on : Jun 8, 2014, 5:00:17 PM
    Author     : joaosantos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>
            function validarTroca() {
            
            var exibir = document.formTrocar.radiotroca.value;
            alert(exibir);
            if (exibir == "") {
                window.alert("Escolha se quer exibir o e-mail.");
                document.formTrocar.radiotroca.focus();
                return false;
            }
          }  
        </script>
    </head>
    <body>

        <form name="formTrocar" action="TrocarServlet" method="POST" >



            <select name="radiotroca" >
                <option value=""></option>
                <option value="1"> 1 </option>
                <option value="2"> 2 </option>
                <option value="3"> 3 </option>
                <option value="4"> 4 </option>
                <option value="5"> 5 </option>
                <option value="6"> 6 </option>

            </select>

            <!--<input id="seleciona" type="radio" name="radiotroca"  value="feijao"/>feijao></br>-->
            <!--            <input id="seleciona" type="radio" name="radiotroca"  value="cenoura"/>cenoura></br>
                        <input id="seleciona" type="radio" name="radiotroca"  value="beterraba"/>beterraba></br>-->
            <input type="submit" value="proporTroca" class="btn-cad"  onclick="return validarTroca()" />
        </form>
    </body>
</html>
