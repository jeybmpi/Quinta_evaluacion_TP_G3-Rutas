package viajecitos.api.infraestructura.repositorios;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import viajecitos.api.dominio.entidades.Pais;

@Repository
public interface IPaisRepositorio extends JpaRepository<Pais, Integer> {
}