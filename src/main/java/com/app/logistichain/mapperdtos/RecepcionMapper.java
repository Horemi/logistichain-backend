package com.app.logistichain.mapperdtos;

import com.app.logistichain.dtos.RecepcionDto;
import com.app.logistichain.entities.Recepcion;
import com.app.logistichain.entities.OrdenCompra;

public class RecepcionMapper {

    public static RecepcionDto mapRecepcionToRecepcionDto(Recepcion e) {
        if (e == null) return null;
        RecepcionDto dto = new RecepcionDto();
        dto.setId(e.getId());
        dto.setFechaRecepcion(e.getFechaRecepcion());
        dto.setGuiaDespachoProveedor(e.getGuiaDespachoProveedor());
        dto.setUsuarioReceptorId(e.getUsuarioReceptorId());
        dto.setEstado(e.getEstado() != null ? e.getEstado().name() : null);

        OrdenCompra oc = e.getOrdenCompra();
        if (oc != null) dto.setOrdenCompraId(oc.getId());
        return dto;
    }

    public static Recepcion mapRecepcionDtoToRecepcion(RecepcionDto dto) {
        if (dto == null) return null;
        Recepcion e = new Recepcion();
        e.setId(dto.getId());
        e.setFechaRecepcion(dto.getFechaRecepcion());
        e.setGuiaDespachoProveedor(dto.getGuiaDespachoProveedor());
        e.setUsuarioReceptorId(dto.getUsuarioReceptorId());
        // estado enum se setea en Service; ordenCompra con ordenCompraId
        return e;
    }
}
