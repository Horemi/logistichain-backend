package com.app.logistichain.mapperdtos;

import com.app.logistichain.dtos.PedidoVentaDto;
import com.app.logistichain.entities.PedidoVenta;
import com.app.logistichain.entities.Cliente;

public class PedidoVentaMapper {

    public static PedidoVentaDto mapPedidoVentaToPedidoVentaDto(PedidoVenta e) {
        if (e == null) return null;
        PedidoVentaDto dto = new PedidoVentaDto();
        dto.setId(e.getId());
        dto.setFechaEmision(e.getFechaEmision());
        dto.setEstado(e.getEstado() != null ? e.getEstado().name() : null);
        dto.setPrecioTotal(e.getPrecioTotal());
        dto.setDireccionDespacho(e.getDireccionDespacho());
        dto.setVendedorId(e.getVendedorId());

        Cliente c = e.getCliente();
        if (c != null) {
            dto.setClienteId(c.getId());
            dto.setClienteNombre(c.getNombre());
        }
        return dto;
    }

    public static PedidoVenta mapPedidoVentaDtoToPedidoVenta(PedidoVentaDto dto) {
        if (dto == null) return null;
        PedidoVenta e = new PedidoVenta();
        e.setId(dto.getId());
        e.setFechaEmision(dto.getFechaEmision());
        // estado enum se setea en Service
        e.setPrecioTotal(dto.getPrecioTotal());
        e.setDireccionDespacho(dto.getDireccionDespacho());
        e.setVendedorId(dto.getVendedorId());
        // cliente se setea en Service con clienteId
        return e;
    }
}
