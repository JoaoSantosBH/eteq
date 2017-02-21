<%-- 
    Document   : newjsp
    Created on : May 26, 2014, 3:23:16 PM
    Author     : joaosantos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <script>
            function alternar() {

                        alert("3000");
                        document.getElementById("map_canvas").style.display = "none";
                        document.getElementById("map_canvas2").style.display = "block";

            }

            myVar = setInterval("alternar()", 5000);
        </script>
    </head>
    <body>



        <div id="map_canvas" style="display: block" >
            HOJOJOJOJOJOJOJOJOJ



        </div>
        <div id="map_canvas2" style="display: none">
            JHGDFLIUGHSIUFD



        </div>

    </body>
</html>
