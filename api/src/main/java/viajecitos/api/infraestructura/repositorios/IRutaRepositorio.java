package viajecitos.api.infraestructura.repositorios;

import java.util.List; 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import viajecitos.api.dominio.entidades.Ruta;

@Repository
public interface IRutaRepositorio extends JpaRepository<Ruta, Integer> {

    List<Ruta> findByCiudad_Id(int idCiudad);
}