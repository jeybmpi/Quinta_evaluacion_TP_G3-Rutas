package viajecitos.api.aplicacion.servicios;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import viajecitos.api.aplicacion.dtos.RutaDTO;
import viajecitos.api.aplicacion.dtos.TipoDTO;
import viajecitos.api.dominio.entidades.Ruta;
import viajecitos.api.infraestructura.repositorios.IRutaRepositorio;

@Service
public class RutaServicio {

    @Autowired
    private IRutaRepositorio repositorio;

    private RutaDTO convertirADto(Ruta ruta) {
        RutaDTO dto = new RutaDTO();
        dto.setId(ruta.getId());
        dto.setNombre(ruta.getNombre());
        dto.setDescripcion(ruta.getDescripcion());

        if (ruta.getTipo() != null) {
            dto.setTipo(new TipoDTO(ruta.getTipo().getId(), ruta.getTipo().getNombre()));
        }
        return dto;
    }

    public List<RutaDTO> listar() {
        return repositorio.findAll()
                .stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    public List<RutaDTO> listarPorCiudad(int idCiudad) {
        return repositorio.findByCiudad_Id(idCiudad)
                .stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    public RutaDTO obtener(int id) {
        Ruta ruta = repositorio.findById(id).orElse(null);
        return (ruta != null) ? convertirADto(ruta) : null;
    }

    public Ruta agregar(Ruta ruta) {
        ruta.setId(0);
        return repositorio.save(ruta);
    }

    public Ruta modificar(Ruta ruta) {
        return repositorio.existsById(ruta.getId()) ? repositorio.save(ruta) : null;
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