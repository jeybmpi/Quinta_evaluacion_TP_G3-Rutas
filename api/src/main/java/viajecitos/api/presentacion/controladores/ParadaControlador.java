package viajecitos.api.presentacion.controladores;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import viajecitos.api.aplicacion.servicios.ParadaServicio;
import viajecitos.api.dominio.entidades.Parada;

@RestController
@RequestMapping("/api/paradas")
@CrossOrigin(origins = "*")
public class ParadaControlador {

    @Autowired
    private ParadaServicio servicio;

    @GetMapping("/")
    public List<Parada> listar() {
        return servicio.listar();
    }

    @GetMapping("/{id}")
    public Parada obtener(@PathVariable int id) {
        return servicio.obtener(id);
    }

    @GetMapping("/ruta/{idRuta}")
    public List<Parada> listarPorRuta(@PathVariable int idRuta) {
    return servicio.listarPorRuta(idRuta);
    }

    @PostMapping("/")
    public Parada agregar(@RequestBody Parada parada) {
        return servicio.agregar(parada);
    }

    @PutMapping("/")
    public Parada modificar(@RequestBody Parada parada) {
        return servicio.modificar(parada);
    }

    @DeleteMapping("/{id}")
    public boolean eliminar(@PathVariable int id) {
        return servicio.eliminar(id);
    }
}