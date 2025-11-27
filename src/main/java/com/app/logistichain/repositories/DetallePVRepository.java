package com.app.logistichain.repositories;

import com.app.logistichain.entities.DetallePV;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DetallePVRepository extends JpaRepository<DetallePV, Long> {
    List<DetallePV> findByPedidoId(Long pedidoId);
}
