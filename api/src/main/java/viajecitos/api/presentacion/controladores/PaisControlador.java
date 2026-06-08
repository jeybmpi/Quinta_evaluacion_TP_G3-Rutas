package viajecitos.api.presentacion.controladores;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import viajecitos.api.aplicacion.servicios.PaisServicio;
import viajecitos.api.dominio.entidades.Pais;

@RestController
@RequestMapping("/api/paises")
@CrossOrigin(origins = "*")
public class PaisControlador {

    @Autowired
    private PaisServicio servicio;

    @GetMapping("/")
    public List<Pais> listar() {
        return servicio.listar();
    }

    @GetMapping("/{id}")
    public Pais obtener(@PathVariable int id) {
        return servicio.obtener(id);
    }

    @PostMapping("/")
    public Pais agregar(@RequestBody Pais pais) {
        return servicio.agregar(pais);
    }

    @PutMapping("/")
    public Pais modificar(@RequestBody Pais pais) {
        return servicio.modificar(pais);
    }

    @DeleteMapping("/{id}")
    public boolean eliminar(@PathVariable int id) {
        return servicio.eliminar(id);
    }
}