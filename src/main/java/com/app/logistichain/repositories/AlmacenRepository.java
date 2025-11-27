package com.app.logistichain.repositories;

import com.app.logistichain.entities.Almacen;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AlmacenRepository extends JpaRepository<Almacen, Long> {
    List<Almacen> findBySucursalId(Long sucursalId);
    boolean existsByNombreAndSucursalId(String nombre, Long sucursalId);
}
