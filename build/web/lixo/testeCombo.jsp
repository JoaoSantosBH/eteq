<%-- 
    Document   : testeCombo
    Created on : Apr 10, 2014, 9:15:18 PM
    Author     : joaosantos
--%>

<%@page import="entidade.TbCidadesEntidade"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        
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
                alert(c.length);
            }
            function carregaCidades() {
                
                var estado = dwr.util.getValue('estados');
                alert(estado);
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

    </head>
    <body onload="MetodosParaODWR.pegarTodosEstadosDWR(loadstart);">


        <form>

            <select name="estados" id="estados" onchange="carregaCidades()">
            </select>
            <select name="cidades" id="cidades"></select>

        </form>
    </body>
</html>
