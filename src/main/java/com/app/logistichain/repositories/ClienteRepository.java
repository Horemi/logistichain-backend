package com.app.logistichain.repositories;

import com.app.logistichain.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByIdentificadorFiscal(String identificadorFiscal);
    boolean existsByEmail(String email);
}
