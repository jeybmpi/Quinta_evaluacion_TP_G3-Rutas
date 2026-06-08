package viajecitos.api.infraestructura.repositorios;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import viajecitos.api.dominio.entidades.Tipo;

@Repository
public interface ITipoRepositorio extends JpaRepository<Tipo, Integer> {
}