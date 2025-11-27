package com.app.logistichain.repositories;

import com.app.logistichain.entities.PedidoVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PedidoVentaRepository extends JpaRepository<PedidoVenta, Long> {
    List<PedidoVenta> findByClienteId(Long clienteId);
    List<PedidoVenta> findByEstado(String estado);
}
