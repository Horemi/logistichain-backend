package com.app.logistichain.mapperdtos;

import com.app.logistichain.dtos.AlmacenDto;
import com.app.logistichain.entities.Almacen;
import com.app.logistichain.entities.Sucursal;

public class AlmacenMapper {

    /**
     * Convierte una entidad Almacen a AlmacenDTO.
     */
    public static AlmacenDto mapAlmacenToAlmacenDto(Almacen almacen) {
        if (almacen == null) return null;

        AlmacenDto dto = new AlmacenDto();
        dto.setId(almacen.getId());
        dto.setNombre(almacen.getNombre());
        dto.setTipo(almacen.getTipo());

        Sucursal sucursal = almacen.getSucursal();
        if (sucursal != null) {
            dto.setSucursalId(sucursal.getId());
            dto.setSucursalNombre(sucursal.getNombre());
        }

        return dto;
    }

    /**
     * Convierte un DTO a entidad Almacen.
     * Nota: la sucursal se asigna en el Service usando sucursalId.
     */
    public static Almacen mapAlmacenDtoToAlmacen(AlmacenDto dto) {
        if (dto == null) return null;

        Almacen a = new Almacen();
        a.setId(dto.getId());
        a.setNombre(dto.getNombre());
        a.setTipo(dto.getTipo());
        // sucursal se setea en Service
        return a;
    }

}
