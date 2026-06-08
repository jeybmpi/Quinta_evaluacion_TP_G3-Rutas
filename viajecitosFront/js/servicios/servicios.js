app.service("ApiServicio", function ($http) {
    const URL_BASE = "http://localhost:8080/api";
    
    const urlSinCache = (url) => `${url}?_=${new Date().getTime()}`;

    this.listarCiudades = () => $http.get(urlSinCache(`${URL_BASE}/ciudades/`)).then(res => res.data);
    this.listarTipos = () => $http.get(urlSinCache(`${URL_BASE}/tipos/`)).then(res => res.data);

    this.listarRutasPorCiudad = (idCiudad) => {
        return $http.get(urlSinCache(`${URL_BASE}/rutas/ciudad/${idCiudad}`)).then(res => {
            return res.data.map(json => Ruta.desdeJson(json));
        });
    };
    
    this.guardarRuta = (ruta) => {
        if(ruta.id) return $http.put(`${URL_BASE}/rutas/`, ruta).then(res => res.data);
        return $http.post(`${URL_BASE}/rutas/`, ruta).then(res => res.data);
    };
    
    this.eliminarRuta = (id) => $http.delete(`${URL_BASE}/rutas/${id}`).then(res => res.data);

    this.listarParadasPorRuta = (idRuta) => {
        return $http.get(urlSinCache(`${URL_BASE}/paradas/ruta/${idRuta}`)).then(res => {
            return res.data.map(json => Parada.desdeJson(json));
        });
    };
    
    this.guardarParada = (parada) => {
        if(parada.id) return $http.put(`${URL_BASE}/paradas/`, parada).then(res => res.data);
        return $http.post(`${URL_BASE}/paradas/`, parada).then(res => res.data);
    };
    
    this.eliminarParada = (id) => $http.delete(`${URL_BASE}/paradas/${id}`).then(res => res.data);
});