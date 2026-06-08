package viajecitos.api.dominio.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "ruta")
public class Ruta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ruta_id_seq")
    @SequenceGenerator(name = "ruta_id_seq", sequenceName = "ruta_id_seq", allocationSize = 1)
    private int id;

    @Column(length = 50, nullable = false)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "idciudad", referencedColumnName = "id", nullable = false)
    private Ciudad ciudad;

    @ManyToOne
    @JoinColumn(name = "idtipo", referencedColumnName = "id", nullable = false)
    private Tipo tipo;

    public Ruta() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
}