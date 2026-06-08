package viajecitos.api.aplicacion.servicios;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import viajecitos.api.dominio.entidades.Pais;
import viajecitos.api.infraestructura.repositorios.IPaisRepositorio;

@Service
public class PaisServicio {

    @Autowired
    private IPaisRepositorio repositorio;

    public List<Pais> listar() {
        return repositorio.findAll();
    }

    public Pais obtener(int id) {
        return repositorio.findById(id).orElse(null);
    }

    public Pais agregar(Pais pais) {
        pais.setId(0);
        return repositorio.save(pais);
    }

    public Pais modificar(Pais pais) {
        return repositorio.existsById(pais.getId()) ? repositorio.save(pais) : null;
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
