package com.app.logistichain.mapperdtos;

import com.app.logistichain.dtos.EmpresaDto;
import com.app.logistichain.entities.Empresa;

public class EmpresaMapper {

    /**
     * Convierte una entidad Empresa a su DTO.
     */
    public static EmpresaDto mapEmpresaToEmpresaDto(Empresa empresa) {
        if (empresa == null) return null;

        EmpresaDto dto = new EmpresaDto();
        dto.setId(empresa.getId());
        dto.setNombre(empresa.getNombre());
        dto.setIdentificadorFiscal(empresa.getIdentificadorFiscal());
        dto.setDireccionPrincipal(empresa.getDireccionPrincipal());
        dto.setTelefono(empresa.getTelefono());
        dto.setEmail(empresa.getEmail());
        dto.setFechaCreacion(empresa.getFechaCreacion());
        return dto;
    }

    /**
     * Convierte un DTO Empresa a su entidad.
     * Nota: las sucursales se agregan desde el Service.
     */
    public static Empresa mapEmpresaDtoToEmpresa(EmpresaDto dto) {
        if (dto == null) return null;

        Empresa e = new Empresa();
        e.setId(dto.getId());
        e.setNombre(dto.getNombre());
        e.setIdentificadorFiscal(dto.getIdentificadorFiscal());
        e.setDireccionPrincipal(dto.getDireccionPrincipal());
        e.setTelefono(dto.getTelefono());
        e.setEmail(dto.getEmail());
        e.setFechaCreacion(dto.getFechaCreacion());
        return e;
    }


}
