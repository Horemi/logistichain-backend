package com.app.logistichain.mapperdtos;

import com.app.logistichain.dtos.DetallePVDto;
import com.app.logistichain.entities.DetallePV;
import com.app.logistichain.entities.PedidoVenta;
import com.app.logistichain.entities.Producto;

public class DetallePVMapper {

    public static DetallePVDto mapDetallePVToDetallePVDto(DetallePV e) {
        if (e == null) return null;
        DetallePVDto dto = new DetallePVDto();
        dto.setId(e.getId());
        dto.setCantidad(e.getCantidad());
        dto.setPrecioUnitarioVenta(e.getPrecioUnitarioVenta());

        PedidoVenta pv = e.getPedido();
        if (pv != null) dto.setPedidoVentaId(pv.getId());

        Producto p = e.getProducto();
        if (p != null) {
            dto.setProductoId(p.getId());
            dto.setProductoDescripcion(p.getDescripcion());
        }
        return dto;
    }

    public static DetallePV mapDetallePVDtoToDetallePV(DetallePVDto dto) {
        if (dto == null) return null;
        DetallePV e = new DetallePV();
        e.setId(dto.getId());
        e.setCantidad(dto.getCantidad());
        e.setPrecioUnitarioVenta(dto.getPrecioUnitarioVenta());
        // pedido y producto se setean en Service con IDs
        return e;
    }
}
