package com.app.logistichain.mapperdtos;

import com.app.logistichain.dtos.UbicacionDto;
import com.app.logistichain.entities.Ubicacion;
import com.app.logistichain.entities.Almacen;

public class UbicacionMapper {

    /**
     * Convierte una entidad Ubicacion a UbicacionDTO.
     */
    public static UbicacionDto mapUbicacionToUbicacionDto(Ubicacion ubicacion) {
        if (ubicacion == null) return null;

        UbicacionDto dto = new UbicacionDto();
        dto.setId(ubicacion.getId());
        dto.setCodigo(ubicacion.getCodigo());
        dto.setPasillo(ubicacion.getPasillo());
        dto.setEstante(ubicacion.getEstante());
        dto.setNivel(ubicacion.getNivel());

        Almacen almacen = ubicacion.getAlmacen();
        if (almacen != null) {
            dto.setAlmacenId(almacen.getId());
            dto.setAlmacenNombre(almacen.getNombre());
        }

        return dto;
    }

    /**
     * Convierte un DTO a entidad Ubicacion.
     * Nota: el almacén se setea en el Service con almacenId.
     */
    public static Ubicacion mapUbicacionDtoToUbicacion(UbicacionDto dto) {
        if (dto == null) return null;

        Ubicacion u = new Ubicacion();
        u.setId(dto.getId());
        u.setCodigo(dto.getCodigo());
        u.setPasillo(dto.getPasillo());
        u.setEstante(dto.getEstante());
        u.setNivel(dto.getNivel());
        // almacen se setea en Service
        return u;
    }

}
