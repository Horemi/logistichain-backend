package com.app.logistichain.repositories;

import com.app.logistichain.entities.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
    Optional<Proveedor> findByIdentificadorFiscal(String identificadorFiscal);
    boolean existsByNombre(String nombre);
}
