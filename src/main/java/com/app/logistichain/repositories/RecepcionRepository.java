package com.app.logistichain.repositories;

import com.app.logistichain.entities.Recepcion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RecepcionRepository extends JpaRepository<Recepcion, Long> {
    List<Recepcion> findByOrdenCompraId(Long ordenCompraId);
    List<Recepcion> findByEstado(String estado);
}
