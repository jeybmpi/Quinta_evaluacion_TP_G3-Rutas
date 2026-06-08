package viajecitos.api.dominio.entidades;

import java.util.List;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "pais")
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "codigoalfa2")
    private String codigoAlfa2;

    @JsonIgnore 
    @OneToMany(mappedBy = "pais")
    private List<Ciudad> ciudades;

    public Pais() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCodigoAlfa2() { return codigoAlfa2; }
    public void setCodigoAlfa2(String codigoAlfa2) { this.codigoAlfa2 = codigoAlfa2; }

    public List<Ciudad> getCiudades() { return ciudades; }
    public void setCiudades(List<Ciudad> ciudades) { this.ciudades = ciudades; }
}