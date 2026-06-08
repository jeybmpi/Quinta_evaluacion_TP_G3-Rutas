package viajecitos.api.aplicacion.servicios;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import viajecitos.api.dominio.entidades.Parada;
import viajecitos.api.infraestructura.repositorios.IParadaRepositorio;

@Service
public class ParadaServicio {

    @Autowired
    private IParadaRepositorio repositorio;

    public List<Parada> listar() {
        return repositorio.findAll();
    }

    public List<Parada> listarPorRuta(int idRuta) {
        return repositorio.findByRuta_IdOrderByOrdenAsc(idRuta);
    }

    public Parada obtener(int id) {
        return repositorio.findById(id).orElse(null);
    }

    public Parada agregar(Parada parada) {
        parada.setId(0);
        return repositorio.save(parada);
    }

    public Parada modificar(Parada parada) {
        return repositorio.existsById(parada.getId()) ? repositorio.save(parada) : null;
    }

    public boolean eliminar(int id) {
        try {
            repositorio.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}