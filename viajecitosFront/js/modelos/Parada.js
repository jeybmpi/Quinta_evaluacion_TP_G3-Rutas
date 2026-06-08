class Parada {
    constructor(id, orden, nombre, latitud, longitud, tiempo, descripcion) {
        this.id = id;
        this.orden = orden;
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
        this.tiempo = tiempo;
        this.descripcion = descripcion;
    }

    static desdeJson(json) {
        return new Parada(json.id, json.orden, json.nombre, json.latitud, json.longitud, json.tiempo, json.descripcion);
    }
}