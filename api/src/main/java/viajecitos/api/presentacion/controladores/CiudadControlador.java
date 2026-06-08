package viajecitos.api.presentacion.controladores;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import viajecitos.api.aplicacion.servicios.CiudadServicio;
import viajecitos.api.dominio.entidades.Ciudad;

@RestController
@RequestMapping("/api/ciudades")
@CrossOrigin(origins = "*")
public class CiudadControlador {

    @Autowired
    private CiudadServicio servicio;

    @GetMapping("/")
    public List<Ciudad> listar() {
        return servicio.listar();
    }

    @GetMapping("/{id}")
    public Ciudad obtener(@PathVariable int id) {
        return servicio.obtener(id);
    }

    @PostMapping("/")
    public Ciudad agregar(@RequestBody Ciudad ciudad) {
        return servicio.agregar(ciudad);
    }

    @PutMapping("/")
    public Ciudad modificar(@RequestBody Ciudad ciudad) {
        return servicio.modificar(ciudad);
    }

    @DeleteMapping("/{id}")
    public boolean eliminar(@PathVariable int id) {
        return servicio.eliminar(id);
    }
}