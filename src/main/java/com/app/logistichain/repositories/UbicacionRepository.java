package com.app.logistichain.repositories;

import com.app.logistichain.entities.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UbicacionRepository extends JpaRepository<Ubicacion, Long> {
    List<Ubicacion> findByAlmacenId(Long almacenId);
    Optional<Ubicacion> findByCodigo(String codigo);
    boolean existsByCodigo(String codigo);
}
