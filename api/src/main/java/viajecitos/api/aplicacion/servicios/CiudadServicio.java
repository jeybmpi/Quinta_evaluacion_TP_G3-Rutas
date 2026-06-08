package viajecitos.api.aplicacion.servicios;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import viajecitos.api.dominio.entidades.Ciudad;
import viajecitos.api.infraestructura.repositorios.ICiudadRepositorio;

@Service
public class CiudadServicio {

    @Autowired
    private ICiudadRepositorio repositorio;

    public List<Ciudad> listar() {
        return repositorio.findAll();
    }

    public Ciudad obtener(int id) {
        return repositorio.findById(id).orElse(null);
    }

    public Ciudad agregar(Ciudad ciudad) {
        ciudad.setId(0);
        return repositorio.save(ciudad);
    }

    public Ciudad modificar(Ciudad ciudad) {
        return repositorio.existsById(ciudad.getId()) ? repositorio.save(ciudad) : null;
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