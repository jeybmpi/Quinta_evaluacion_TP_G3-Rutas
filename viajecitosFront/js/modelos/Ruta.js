class Ruta {
    constructor(id, nombre, descripcion, tipo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo; 
    }

    static desdeJson(json) {
        return new Ruta(json.id, json.nombre, json.descripcion, json.tipo);
    }
}