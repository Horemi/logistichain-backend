package com.app.logistichain.mapperdtos;

import com.app.logistichain.dtos.OrdenCompraDto;
import com.app.logistichain.entities.OrdenCompra;
import com.app.logistichain.entities.Proveedor;

public class OrdenCompraMapper {

    public static OrdenCompraDto mapOrdenCompraToOrdenCompraDto(OrdenCompra e) {
        if (e == null) return null;
        OrdenCompraDto dto = new OrdenCompraDto();
        dto.setId(e.getId());
        dto.setFechaEmision(e.getFechaEmision());
        dto.setFechaEntregaEsperada(e.getFechaEntregaEsperada());
        dto.setEstado(e.getEstado() != null ? e.getEstado().name() : null);
        dto.setCostoTotal(e.getCostoTotal());
        dto.setUsuarioCreadorId(e.getUsuarioCreadorId());

        Proveedor p = e.getProveedor();
        if (p != null) {
            dto.setProveedorId(p.getId());
            dto.setProveedorNombre(p.getNombre());
        }
        return dto;
    }

    public static OrdenCompra mapOrdenCompraDtoToOrdenCompra(OrdenCompraDto dto) {
        if (dto == null) return null;
        OrdenCompra e = new OrdenCompra();
        e.setId(dto.getId());
        e.setFechaEmision(dto.getFechaEmision());
        e.setFechaEntregaEsperada(dto.getFechaEntregaEsperada());
        // estado se setea en Service (Enum desde dto.getEstado())
        e.setCostoTotal(dto.getCostoTotal());
        e.setUsuarioCreadorId(dto.getUsuarioCreadorId());
        // proveedor se setea en Service usando proveedorId
        return e;
    }
}
