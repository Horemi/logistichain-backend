package com.app.logistichain.mapperdtos;

import com.app.logistichain.dtos.KardexDto;
import com.app.logistichain.entities.*;

public class KardexMapper {

    public static KardexDto mapKardexToKardexDto(Kardex e) {
        if (e == null) return null;
        KardexDto dto = new KardexDto();
        dto.setId(e.getId());
        dto.setFecha(e.getFecha());
        dto.setTipoMovimiento(e.getTipoMovimiento() != null ? e.getTipoMovimiento().name() : null);
        dto.setCantidad(e.getCantidad());
        dto.setSaldoResultante(e.getSaldoResultante());
        dto.setReferenciaDocumento(e.getReferenciaDocumento());

        Producto p = e.getProducto();
        if (p != null) {
            dto.setProductoId(p.getId());
            dto.setProductoDescripcion(p.getDescripcion());
        }

        Lote l = e.getLote();
        if (l != null) {
            dto.setLoteId(l.getId());
            dto.setLoteCodigo(l.getCodigoLote());
        }

        Almacen a = e.getAlmacen();
        if (a != null) {
            dto.setAlmacenId(a.getId());
            dto.setAlmacenNombre(a.getNombre());
        }

        return dto;
    }

    public static Kardex mapKardexDtoToKardex(KardexDto dto) {
        if (dto == null) return null;
        Kardex e = new Kardex();
        e.setId(dto.getId());
        e.setFecha(dto.getFecha());
        // tipoMovimiento enum se setea en Service
        e.setCantidad(dto.getCantidad());
        e.setSaldoResultante(dto.getSaldoResultante());
        e.setReferenciaDocumento(dto.getReferenciaDocumento());
        // producto, lote y almacen se setean en Service
        return e;
    }
}
