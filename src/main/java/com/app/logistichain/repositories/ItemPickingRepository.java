package com.app.logistichain.repositories;

import com.app.logistichain.entities.ItemPicking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ItemPickingRepository extends JpaRepository<ItemPicking, Long> {
    List<ItemPicking> findByPickingId(Long pickingId);
}
