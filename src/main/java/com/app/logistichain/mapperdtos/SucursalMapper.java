package com.app.logistichain.mapperdtos;

import com.app.logistichain.dtos.SucursalDto;
import com.app.logistichain.entities.Sucursal;
import com.app.logistichain.entities.Empresa;

public class SucursalMapper {

    /**
     * Convierte una entidad Sucursal a SucursalDTO.
     */
    public static SucursalDto mapSucursalToSucursalDto(Sucursal sucursal) {
        if (sucursal == null) return null;

        SucursalDto dto = new SucursalDto();
        dto.setId(sucursal.getId());
        dto.setNombre(sucursal.getNombre());
        dto.setDireccion(sucursal.getDireccion());
        dto.setTelefono(sucursal.getTelefono());

        Empresa empresa = sucursal.getEmpresa();
        if (empresa != null) {
            dto.setEmpresaId(empresa.getId());
            dto.setEmpresaNombre(empresa.getNombre());
        }

        return dto;
    }

    /**
     * Convierte un DTO a entidad Sucursal.
     * Nota: la empresa se setea en el Service con empresaId.
     */
    public static Sucursal mapSucursalDtoToSucursal(SucursalDto dto) {
        if (dto == null) return null;

        Sucursal s = new Sucursal();
        s.setId(dto.getId());
        s.setNombre(dto.getNombre());
        s.setDireccion(dto.getDireccion());
        s.setTelefono(dto.getTelefono());
        // empresa se setea en Service
        return s;
    }

}
