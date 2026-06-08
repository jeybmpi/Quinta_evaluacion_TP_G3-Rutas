package viajecitos.api.infraestructura.repositorios;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import viajecitos.api.dominio.entidades.Parada;

@Repository
public interface IParadaRepositorio extends JpaRepository<Parada, Integer> {

    List<Parada> findByRuta_IdOrderByOrdenAsc(int idRuta);
}