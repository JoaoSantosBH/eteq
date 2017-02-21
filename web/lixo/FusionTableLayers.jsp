<%-- 
    Document   : FusionTableLayers
    Created on : Dec 2, 2013, 2:41:57 PM
    Author     : joaomarcelo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
    <title>Fusion Tables layers</title>
    <style>
      html, body, #map-canvas {
        height: 100%;
        margin: 0px;
        padding: 0px
      }
    </style>
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>
    <script>
var map, layer;

function initialize() {
  var chicago = new google.maps.LatLng(-19.9301593, -43.96655420000002);

  map = new google.maps.Map(document.getElementById('map-canvas'), {
    center: chicago,
    zoom: 16
  });

  layer = new google.maps.FusionTablesLayer({
    query: {
      select: '\'Geocodable address\'',
      from: 'Rua Aristoteles Caldeira 102'
    }
  });
  layer.setMap(map);
}

google.maps.event.addDomListener(window, 'load', initialize);

    </script>
  </head>
  <body>
    <div id="map-canvas"></div>
  </body>
</html>