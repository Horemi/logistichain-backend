package com.app.logistichain.repositories;

import com.app.logistichain.entities.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    Optional<Empresa> findByIdentificadorFiscal(String identificadorFiscal);
    boolean existsByNombre(String nombre);
}
