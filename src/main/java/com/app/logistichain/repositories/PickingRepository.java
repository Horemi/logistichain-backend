package com.app.logistichain.repositories;

import com.app.logistichain.entities.Picking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PickingRepository extends JpaRepository<Picking, Long> {
    List<Picking> findByPedidoId(Long pedidoId);
    List<Picking> findByPickerAsignadoId(Long pickerId);
}
