package viajecitos.api.presentacion.controladores;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import viajecitos.api.aplicacion.dtos.RutaDTO;
import viajecitos.api.aplicacion.servicios.RutaServicio;
import viajecitos.api.dominio.entidades.Ruta;

@RestController
@CrossOrigin(origins = "*") // Porfin lo arreglee, aqui estaba el error, importante para la proxima vez
@RequestMapping("/api/rutas")
public class RutaControlador {

    @Autowired
    private RutaServicio servicio;

    @GetMapping("/")
    public List<RutaDTO> listar() {
        return servicio.listar();
    }

    @GetMapping("/ciudad/{idCiudad}")
    public List<RutaDTO> listarPorCiudad(@PathVariable int idCiudad) {
        return servicio.listarPorCiudad(idCiudad);
    }

    @GetMapping("/{id}")
    public RutaDTO obtener(@PathVariable int id) {
        return servicio.obtener(id);
    }

    @PostMapping("/")
    public Ruta agregar(@RequestBody Ruta ruta) {
        return servicio.agregar(ruta);
    }

    @PutMapping("/")
    public Ruta modificar(@RequestBody Ruta ruta) {
        return servicio.modificar(ruta);
    }

    @DeleteMapping("/{id}")
    public boolean eliminar(@PathVariable int id) {
        return servicio.eliminar(id);
    }
}