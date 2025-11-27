package com.app.logistichain.mapperdtos;

import com.app.logistichain.dtos.ProveedorDto;
import com.app.logistichain.entities.Proveedor;

public class ProveedorMapper {

    public static ProveedorDto mapProveedorToProveedorDto(Proveedor e) {
        if (e == null) return null;
        ProveedorDto dto = new ProveedorDto();
        dto.setId(e.getId());
        dto.setNombre(e.getNombre());
        dto.setIdentificadorFiscal(e.getIdentificadorFiscal());
        dto.setDireccion(e.getDireccion());
        dto.setTelefono(e.getTelefono());
        dto.setEmailContacto(e.getEmailContacto());
        return dto;
    }

    public static Proveedor mapProveedorDtoToProveedor(ProveedorDto dto) {
        if (dto == null) return null;
        Proveedor e = new Proveedor();
        e.setId(dto.getId());
        e.setNombre(dto.getNombre());
        e.setIdentificadorFiscal(dto.getIdentificadorFiscal());
        e.setDireccion(dto.getDireccion());
        e.setTelefono(dto.getTelefono());
        e.setEmailContacto(dto.getEmailContacto());
        return e;
    }
}
