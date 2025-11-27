package com.app.logistichain.services;

import com.app.logistichain.dtos.OrdenCompraDto;
import java.util.List;

public interface OrdenCompraService {
    OrdenCompraDto createOrdenCompra(OrdenCompraDto dto);
    OrdenCompraDto updateOrdenCompra(Long id, OrdenCompraDto dto);
    String deleteOrdenCompra(Long id);
    OrdenCompraDto getOrdenCompra(Long id);
    List<OrdenCompraDto> getOrdenesCompra();
}
