package com.app.logistichain.repositories;

import com.app.logistichain.entities.DetalleOC;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DetalleOCRepository extends JpaRepository<DetalleOC, Long> {
    List<DetalleOC> findByOrdenCompraId(Long ordenCompraId);
}
