package com.app.logistichain.repositories;

import com.app.logistichain.entities.Kardex;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface KardexRepository extends JpaRepository<Kardex, Long> {
    List<Kardex> findByProductoId(Long productoId);
    List<Kardex> findByAlmacenId(Long almacenId);
    List<Kardex> findByLoteId(Long loteId);
}
