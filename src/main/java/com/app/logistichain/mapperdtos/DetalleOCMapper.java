package com.app.logistichain.mapperdtos;

import com.app.logistichain.dtos.DetalleOCDto;
import com.app.logistichain.entities.DetalleOC;
import com.app.logistichain.entities.OrdenCompra;
import com.app.logistichain.entities.Producto;

public class DetalleOCMapper {

    public static DetalleOCDto mapDetalleOCToDetalleOCDto(DetalleOC e) {
        if (e == null) return null;
        DetalleOCDto dto = new DetalleOCDto();
        dto.setId(e.getId());
        dto.setCantidad(e.getCantidad());
        dto.setCostoUnitario(e.getCostoUnitario());

        OrdenCompra oc = e.getOrdenCompra();
        if (oc != null) dto.setOrdenCompraId(oc.getId());

        Producto p = e.getProducto();
        if (p != null) {
            dto.setProductoId(p.getId());
            dto.setProductoDescripcion(p.getDescripcion());
        }
        return dto;
    }

    public static DetalleOC mapDetalleOCDtoToDetalleOC(DetalleOCDto dto) {
        if (dto == null) return null;
        DetalleOC e = new DetalleOC();
        e.setId(dto.getId());
        e.setCantidad(dto.getCantidad());
        e.setCostoUnitario(dto.getCostoUnitario());
        // ordenCompra y producto se seteán en Service con sus IDs
        return e;
    }
}
