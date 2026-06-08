package viajecitos.api.aplicacion.servicios;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import viajecitos.api.dominio.entidades.Tipo;
import viajecitos.api.infraestructura.repositorios.ITipoRepositorio;

@Service
public class TipoServicio {

    @Autowired
    private ITipoRepositorio repositorio;

    public List<Tipo> listar() {
        return repositorio.findAll();
    }

    public Tipo obtener(int id) {
        return repositorio.findById(id).orElse(null);
    }

    public Tipo agregar(Tipo tipo) {
        tipo.setId(0);
        return repositorio.save(tipo);
    }

    public Tipo modificar(Tipo tipo) {
        return repositorio.existsById(tipo.getId()) ? repositorio.save(tipo) : null;
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
