app.controller("PrincipalControlador", function ($scope, ApiServicio, $timeout) {
    $scope.ciudades = [];
    $scope.tipos = [];
    $scope.rutas = [];
    $scope.paradas = [];

    $scope.ciudadSeleccionada = null;
    $scope.rutaSeleccionada = null;

    $scope.formRuta = {};
    $scope.formParada = {};

    let mapa = null;
    let capaMarcadores = null;

    $scope.inicializar = () => {
        ApiServicio.listarCiudades().then(data => $scope.ciudades = data);
        ApiServicio.listarTipos().then(data => $scope.tipos = data);
    };

    $scope.seleccionarCiudad = (ciudad) => {
        $scope.ciudadSeleccionada = ciudad;
        $scope.rutaSeleccionada = null;
        $scope.paradas = [];
        $scope.formRuta = { ciudad: { id: ciudad.id } }; 
        
        if (mapa) {
            mapa.remove();
            mapa = null;
            capaMarcadores = null;
        }
        
        ApiServicio.listarRutasPorCiudad(ciudad.id).then(data => {
            $scope.rutas = data;
        });
    };

    $scope.guardarRuta = () => {
        if (!$scope.formRuta.nombre || !$scope.formRuta.idTipoTemp) {
            alert("⚠️ Por favor ingresa el nombre de la ruta y selecciona un Tipo.");
            return;
        }

        $scope.formRuta.tipo = { id: $scope.formRuta.idTipoTemp }; 
        
        ApiServicio.guardarRuta($scope.formRuta).then(() => {
            let idCiudadActual = $scope.ciudadSeleccionada.id;

            ApiServicio.listarRutasPorCiudad(idCiudadActual).then(data => {
                $scope.rutas = data; 
                $scope.formRuta = { ciudad: { id: idCiudadActual } }; 
                $scope.formRuta.idTipoTemp = ""; 
            });
        }).catch(err => alert("Error del servidor al guardar la ruta."));
    };

    $scope.editarRuta = (ruta) => {
        $scope.formRuta = angular.copy(ruta); 
        $scope.formRuta.idTipoTemp = ruta.tipo.id; 
        
        let collapseElement = document.getElementById('formRutaCollapse');
        if (collapseElement) new bootstrap.Collapse(collapseElement, {toggle: false}).show();
        
        window.scrollTo({ top: 0, behavior: 'smooth' });
    };

    $scope.eliminarRuta = (id) => {
        if (confirm("¿Estás seguro de eliminar esta ruta?")) {
            ApiServicio.eliminarRuta(id).then(() => {
                $scope.seleccionarCiudad($scope.ciudadSeleccionada);
            });
        }
    };

    $scope.seleccionarRuta = (ruta) => {
        $scope.rutaSeleccionada = ruta;
        $scope.formParada = { ruta: { id: ruta.id }, orden: $scope.paradas.length + 1 };
        
        ApiServicio.listarParadasPorRuta(ruta.id).then(data => {
            $scope.paradas = data;
            $timeout($scope.dibujarMapa, 200); 
        });
    };

    $scope.guardarParada = () => {
        if (!$scope.formParada.nombre || !$scope.formParada.orden || !$scope.formParada.latitud || !$scope.formParada.longitud) {
            alert("⚠️ Por favor llena el orden, el nombre y las coordenadas de la parada.");
            return;
        }

        ApiServicio.guardarParada($scope.formParada).then(() => {
            let idRutaActual = $scope.rutaSeleccionada.id;

            ApiServicio.listarParadasPorRuta(idRutaActual).then(data => {
                $scope.paradas = data; 
                $scope.formParada = { ruta: { id: idRutaActual }, orden: $scope.paradas.length + 1 };
                $timeout($scope.dibujarMapa, 200); 
            });
        }).catch(err => alert("Error del servidor al guardar la parada."));
    };

    $scope.editarParada = (parada) => {
        $scope.formParada = angular.copy(parada);
        $scope.formParada.ruta = { id: $scope.rutaSeleccionada.id };
        
        let collapseElement = document.getElementById('formParadaCollapse');
        if (collapseElement) new bootstrap.Collapse(collapseElement, {toggle: false}).show();
    };

    $scope.eliminarParada = (id) => {
        if (confirm("¿Estás seguro de eliminar esta parada?")) {
            ApiServicio.eliminarParada(id).then(() => {
                $scope.seleccionarRuta($scope.rutaSeleccionada);
            });
        }
    };

    $scope.dibujarMapa = () => {
        if (!mapa) {
            mapa = L.map('mapa').setView([0, 0], 2);
            L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                attribution: '© OpenStreetMap'
            }).addTo(mapa);
            capaMarcadores = L.featureGroup().addTo(mapa);
        }

        capaMarcadores.clearLayers(); 
        let puntos = [];

        $scope.paradas.forEach(parada => {
            let latlng = [parada.latitud, parada.longitud];
            puntos.push(latlng);
            
            let desc = parada.descripcion ? parada.descripcion : '';
            let marker = L.marker(latlng).bindPopup(`<b>${parada.orden}. ${parada.nombre}</b><br>${desc}`);
            capaMarcadores.addLayer(marker);
        });

        if (puntos.length > 0) {
            L.polyline(puntos, {color: 'blue', weight: 3, dashArray: '5, 10'}).addTo(capaMarcadores);
            mapa.fitBounds(capaMarcadores.getBounds(), {padding: [50, 50]});
        } else if ($scope.ciudadSeleccionada) {
            mapa.setView([$scope.ciudadSeleccionada.latitud, $scope.ciudadSeleccionada.longitud], 12);
        }

        $timeout(() => {
            if (mapa) mapa.invalidateSize();
        }, 300);
    };

    $scope.inicializar();
});