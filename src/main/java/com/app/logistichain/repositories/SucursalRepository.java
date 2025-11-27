package com.app.logistichain.repositories;

import com.app.logistichain.entities.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SucursalRepository extends JpaRepository<Sucursal, Long> {
    List<Sucursal> findByEmpresaId(Long empresaId);
    boolean existsByNombreAndEmpresaId(String nombre, Long empresaId);
}
