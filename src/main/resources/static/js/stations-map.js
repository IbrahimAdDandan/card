if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(getStations);
} else {
    alert("Geolocation is not supported by this browser.");
}

function getStations(position) {
    fetch('http://localhost:8090/get-sations')
            .then(function (response) {
                return response.json();
            })
            .then(function (stations) {
                stations = sortStations(stations, position);
                drawTable(stations);
                drawMap(stations, position);
//                drawMap(stations, position);
//                            console.log(JSON.stringify(stations));
//                document.getElementById("x").innerHTML = JSON.stringify(stations);

            });
}

function sortStations(stations, position) {
    for (var i = 0; i < stations.length; i++) {
        stations[i].distance = distance(stations[i].langitude, position.coords.latitude, stations[i].latitude, position.coords.longitude);

    }
    return stations.sort((s1, s2) => {
        return s1.distance - s2.distance;
    });
}

function distance(x1, x2, y1, y2) {
    var x = Math.pow(Math.abs(x2 - x1), 2);
    var y = Math.pow(Math.abs(y2 - y1), 2);
    return Math.sqrt(x + y);
}

function drawTable(stations) {
    var tableContent = '';
    var length = 3;
        if (stations.length < 3) {
            length = stations.length;
        }
    for(var i = 0; i < length; i++) {
        tableContent += '<tr><td>' + stations[i].name + '</td>';
        tableContent += '<td>' + stations[i].address + '</td>';
        tableContent += '<td>' + stations[i].ammount + '</td>';
        tableContent += '<td>' + stations[i].queueCount + '</td>';
        tableContent += '<td>' + stations[i].distance + '</td></tr> ';
    }
    document.getElementById("table-body").innerHTML = tableContent;
}

function drawMap(stations, position) {
    
    var platform = new H.service.Platform({
        app_id: 'GYykQB3jrFmGYDAwUdUD',
        app_code: '7WTZWs18fqMte1rpZnvtaA',
        useCIT: true,
        useHTTPS: true
      });
    var defaultLayers = platform.createDefaultLayers();
    var map = new H.Map(document.getElementById('map'), defaultLayers.normal.map);
    var behavior = new H.mapevents.Behavior(new H.mapevents.MapEvents(map));
    var ui = H.ui.UI.createDefault(map, defaultLayers);
    map.setCenter({lat:position.coords.latitude, lng:position.coords.longitude});
    map.setBaseLayer(defaultLayers.satellite.map);
    map.setZoom(13);
    var p = new H.map.Icon('plugins/google-map/images/person.png');
    var m = new H.map.Marker({lat:position.coords.latitude, lng:position.coords.longitude}, { icon: p });
    map.addObject(m);
    var pinIcon;
    var marker;
    var marker_image;
    var startSVG = '<svg width="100" height="24" xmlns="http://www.w3.org/2000/svg">' +
  '<text x="18" y="18" font-size="10pt" font-family="Arial" ' +
  'text-anchor="middle" fill="orange">';
    var endSVG = '</text></svg>';
    var svgIcon;
    var svgMarker;
    var length = 3;
        if (stations.length < 3) {
            length = stations.length;
        }
    for(var j = 0; j < length; j++) {
        if(stations[j].ammount > 1000) {
            marker_image ='plugins/google-map/images/green-marker.png';
        } else if (stations[j].ammount > 500) {
            marker_image ='plugins/google-map/images/orange-marker.png';
        } else {
            marker_image ='plugins/google-map/images/marker.png';
        }
        pinIcon = new H.map.Icon(marker_image);
        marker = new H.map.Marker({lat:stations[j].langitude, lng:stations[j].latitude}, { icon: pinIcon });
        map.addObject(marker);
        svgIcon = new H.map.Icon(startSVG + stations[j].name + endSVG);
        svgMarker = new H.map.Marker({lat:stations[j].langitude,lng:stations[j].latitude}, {icon:svgIcon});
        map.addObject(svgMarker);
    }
}