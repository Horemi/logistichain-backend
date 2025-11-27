package com.app.logistichain.mapperdtos;

import com.app.logistichain.dtos.RolDto;
import com.app.logistichain.entities.Rol;

public class RolMapper {

    public static RolDto mapRolToRolDto(Rol e) {
        if (e == null) return null;
        RolDto dto = new RolDto();
        dto.setId(e.getId());
        dto.setNombre(e.getNombre() != null ? e.getNombre().name() : null);
        return dto;
    }

    public static Rol mapRolDtoToRol(RolDto dto) {
        if (dto == null) return null;
        Rol e = new Rol();
        e.setId(dto.getId());
        // nombre enum se setea en Service a partir de dto.getNombre()
        return e;
    }
}
