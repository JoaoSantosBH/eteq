var map;
var idInfoBoxAberto;
var infoBox = [];
var markers = [];

function initialize() {
    var latlng = new google.maps.LatLng(-19.9301593, -43.96655420000002);

    var options = {
        zoom: 16,
        center: latlng,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };

    map = new google.maps.Map(document.getElementById("mapa"), options);
}

initialize();

function abrirInfoBox(id, marker) {
    if (typeof (idInfoBoxAberto) === 'number' && typeof (infoBox[idInfoBoxAberto]) === 'object') {
        infoBox[idInfoBoxAberto].close();
    }

    infoBox[id].open(map, marker);
    idInfoBoxAberto = id;
}

function carregarPontos() {

    $.getJSON('javascript/api/pontos.json', function(pontos) {

        var latlngbounds = new google.maps.LatLngBounds();
        var linka = '<a href ="';
        var linkb ='">';
        var linkfim ="</a>";
        
        $.each(pontos, function(index, ponto) {
            
            var marker = new google.maps.Marker({
                position: new google.maps.LatLng(ponto.Latitude, ponto.Longitude),
                title: "ETEQ",
                icon: 'img/marcador.png'
            });

            var myOptions = {
                content: linka  + ponto.Descricao + linkb + ponto.Descricao + linkfim,
                pixelOffset: new google.maps.Size(-150, 0)
            };

            infoBox[ponto.Id] = new InfoBox(myOptions);
            infoBox[ponto.Id].marker = marker;

            infoBox[ponto.Id].listener = google.maps.event.addListener(marker, 'click', function(e) {
                abrirInfoBox(ponto.Id, marker);
            });

            markers.push(marker);

            latlngbounds.extend(marker.position);

        });

        var markerCluster = new MarkerClusterer(map, markers);

        map.fitBounds(latlngbounds);

    });

}

carregarPontos();