package com.app.logistichain.mapperdtos;

import com.app.logistichain.dtos.PickingDto;
import com.app.logistichain.entities.PedidoVenta;
import com.app.logistichain.entities.Picking;

public class PickingMapper {

    public static PickingDto mapPickingToPickingDto(Picking e) {
        if (e == null) return null;
        PickingDto dto = new PickingDto();
        dto.setId(e.getId());
        dto.setFechaAsignacion(e.getFechaAsignacion());
        dto.setEstado(e.getEstado() != null ? e.getEstado().name() : null);
        dto.setPickerAsignadoId(e.getPickerAsignadoId());

        PedidoVenta pv = e.getPedido();
        if (pv != null) dto.setPedidoVentaId(pv.getId());
        return dto;
    }

    public static Picking mapPickingDtoToPicking(PickingDto dto) {
        if (dto == null) return null;
        Picking e = new Picking();
        e.setId(dto.getId());
        e.setFechaAsignacion(dto.getFechaAsignacion());
        // estado enum se setea en Service
        e.setPickerAsignadoId(dto.getPickerAsignadoId());
        // pedido se setea en Service
        return e;
    }
}
