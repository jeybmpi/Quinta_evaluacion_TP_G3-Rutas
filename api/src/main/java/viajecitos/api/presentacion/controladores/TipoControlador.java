package viajecitos.api.presentacion.controladores;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import viajecitos.api.aplicacion.servicios.TipoServicio;
import viajecitos.api.dominio.entidades.Tipo;

@RestController
@RequestMapping("/api/tipos")
@CrossOrigin(origins = "*")
public class TipoControlador {

    @Autowired
    private TipoServicio servicio;

    @GetMapping("/")
    public List<Tipo> listar() {
        return servicio.listar();
    }

    @GetMapping("/{id}")
    public Tipo obtener(@PathVariable int id) {
        return servicio.obtener(id);
    }

    @PostMapping("/")
    public Tipo agregar(@RequestBody Tipo tipo) {
        return servicio.agregar(tipo);
    }

    @PutMapping("/")
    public Tipo modificar(@RequestBody Tipo tipo) {
        return servicio.modificar(tipo);
    }

    @DeleteMapping("/{id}")
    public boolean eliminar(@PathVariable int id) {
        return servicio.eliminar(id);
    }
}