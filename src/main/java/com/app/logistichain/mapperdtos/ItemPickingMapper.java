package com.app.logistichain.mapperdtos;

import com.app.logistichain.dtos.ItemPickingDto;
import com.app.logistichain.entities.*;

public class ItemPickingMapper {

    public static ItemPickingDto mapItemPickingToItemPickingDto(ItemPicking e) {
        if (e == null) return null;
        ItemPickingDto dto = new ItemPickingDto();
        dto.setId(e.getId());
        dto.setCantidadARecoger(e.getCantidadARecoger());
        dto.setCantidadRecogida(e.getCantidadRecogida());

        Picking picking = e.getPicking();
        if (picking != null) dto.setPickingId(picking.getId());

        DetallePV dpv = e.getDetallePv();
        if (dpv != null) dto.setDetallePvId(dpv.getId());

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

        Ubicacion u = e.getUbicacion();
        if (u != null) {
            dto.setUbicacionId(u.getId());
            dto.setUbicacionCodigo(u.getCodigo());
        }

        return dto;
    }

    public static ItemPicking mapItemPickingDtoToItemPicking(ItemPickingDto dto) {
        if (dto == null) return null;
        ItemPicking e = new ItemPicking();
        e.setId(dto.getId());
        e.setCantidadARecoger(dto.getCantidadARecoger());
        e.setCantidadRecogida(dto.getCantidadRecogida());
        // picking, detallePv, producto, lote, ubicacion se setean en Service
        return e;
    }
}
