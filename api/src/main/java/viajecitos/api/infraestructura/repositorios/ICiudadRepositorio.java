package viajecitos.api.infraestructura.repositorios;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import viajecitos.api.dominio.entidades.Ciudad;

@Repository
public interface ICiudadRepositorio extends JpaRepository<Ciudad, Integer> {
}