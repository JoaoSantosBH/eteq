<%-- 
    Document   : TesteAPIgoogle
    Created on : Dec 2, 2013, 1:50:00 PM
    Author     : joaomarcelo
--%>

<%@page import="controle.ControladorEndereco"%>
<%@page import="util.GoogleMapsApi"%>
<%@page import="entidade.UsuarioEntidade"%>
<%@page import="persistencia.AdminDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
    <title>Complex icons</title>
    <style>
        html, body, #map-canvas {
            height: 100%;
            margin: 0px;
            padding: 0px
        }
    </style>
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>
    <%
        String admin = AdminDAO.pegarNomeAdmin();
        UsuarioEntidade Logado = (UsuarioEntidade) session.getAttribute("UsuarioLogado");
        String foto = "";
        String Erro = request.getParameter("e");
        String categoria = request.getParameter("id_cat");
        String subCategoria = request.getParameter("id_sub");
        String vetorJs = GoogleMapsApi.montarVetorJS(Logado.getIdUsuario().toString());
        String cordenada = ControladorEndereco.pegarCoordenadaUsuarioAtual(Logado.getIdUsuario().toString());
        if (Erro == null) {
            Erro = "";
        }

    %>


    <script>
// The following example creates complex markers to indicate beaches near
// Sydney, NSW, Australia. Note that the anchor is set to
// (0,32) to correspond to the base of the flagpole.

        function initialize() {


            var mapOptions = {
                zoom: 14,
                center: new google.maps.LatLng(<%= cordenada%>)
            }
            var map = new google.maps.Map(document.getElementById('map-canvas'),
                    mapOptions);

            setMarkers(map, beaches);
        }

        /**
         * Data for the markers consisting of a name, a LatLng and a zIndex for
         * the order in which these markers should display on top of each
         * other.
         * ['Geladeira Brastemp',  -19.9301337, -43.9665309, 1],  
         ['livro',  -20.4310268, -54.5855369, 1]
         */
        var beaches = [<%= vetorJs%>];

        function setMarkers(map, locations) {
            // Add markers to the map

            // Marker sizes are expressed as a Size of X,Y
            // where the origin of the image (0,0) is located
            // in the top left of the image.

            // Origins, anchor positions and coordinates of the marker
            // increase in the X direction to the right and in
            // the Y direction down.
            var image = {
                url: '../img/marcador.png',
                // This marker is 20 pixels wide by 32 pixels tall.
                size: new google.maps.Size(40, 40),
                // The origin for this image is 0,0.
                origin: new google.maps.Point(0, 0),
                // The anchor for this image is the base of the flagpole at 0,32.
                anchor: new google.maps.Point(0, 32)
            };
            // Shapes define the clickable region of the icon.
            // The type defines an HTML &lt;area&gt; element 'poly' which
            // traces out a polygon as a series of X,Y points. The final
            // coordinate closes the poly by connecting to the first
            // coordinate.
            var shape = {
                coord: [1, 1, 1, 20, 18, 20, 18, 1],
                type: 'poly'
            };
            var markers = new Array();
            var infowindow = new google.maps.InfoWindow({
               
            });
            for (var i = 0; i < locations.length; i++) {

                var beach = locations[i];
                var myLatLng = new google.maps.LatLng(beach[1], beach[2]);

                var marker = new google.maps.Marker({
                    position: myLatLng,
                    map: map,
                    icon: image,
                    shape: shape,
                    title: beach[0],
                    zIndex: beach[3],
                });

                markers.push(marker);

                google.maps.event.addListener(marker, 'click', (function(marker, i) {
                    return function() {
                        infowindow.setContent(locations[i][4]);
                        infowindow.open(map, marker);
                    }
                })(marker, i));

            }
        }

        google.maps.event.addDomListener(window, 'load', initialize);

    </script>
</head>
<body>
    <div id="map-canvas"></div>
     
</body>
</html>