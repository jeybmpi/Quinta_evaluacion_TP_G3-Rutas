package viajecitos.api.aplicacion.dtos;

public class RutaDTO {
    private int id;
    private String nombre;
    private String descripcion;
    private TipoDTO tipo;

    public RutaDTO() {}

    public RutaDTO(int id, String nombre, String descripcion, TipoDTO tipo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public TipoDTO getTipo() { return tipo; }
    public void setTipo(TipoDTO tipo) { this.tipo = tipo; }
}