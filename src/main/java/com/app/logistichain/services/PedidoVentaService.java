package com.app.logistichain.services;

import com.app.logistichain.dtos.PedidoVentaDto;
import java.util.List;

public interface PedidoVentaService {
    PedidoVentaDto createPedidoVenta(PedidoVentaDto dto);
    PedidoVentaDto updatePedidoVenta(Long id, PedidoVentaDto dto);
    String deletePedidoVenta(Long id);
    PedidoVentaDto getPedidoVenta(Long id);
    List<PedidoVentaDto> getPedidosVenta();
}
