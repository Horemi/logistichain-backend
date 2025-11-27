package com.app.logistichain.mapperdtos;

import com.app.logistichain.dtos.LoteDto;
import com.app.logistichain.entities.Lote;
import com.app.logistichain.entities.Producto;

public class LoteMapper {

    public static LoteDto mapLoteToLoteDto(Lote e) {
        if (e == null) return null;
        LoteDto dto = new LoteDto();
        dto.setId(e.getId());
        dto.setCodigoLote(e.getCodigoLote());
        dto.setFechaVencimiento(e.getFechaVencimiento());
        dto.setFechaRecepcion(e.getFechaRecepcion());
        dto.setStockActual(e.getStockActual());

        Producto p = e.getProducto();
        if (p != null) {
            dto.setProductoId(p.getId());
            dto.setProductoDescripcion(p.getDescripcion());
        }
        return dto;
    }

    public static Lote mapLoteDtoToLote(LoteDto dto) {
        if (dto == null) return null;
        Lote e = new Lote();
        e.setId(dto.getId());
        e.setCodigoLote(dto.getCodigoLote());
        e.setFechaVencimiento(dto.getFechaVencimiento());
        e.setFechaRecepcion(dto.getFechaRecepcion());
        e.setStockActual(dto.getStockActual());
        // producto se setea en Service usando productoId
        return e;
    }
}
