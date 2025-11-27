package com.app.logistichain.services;

import com.app.logistichain.dtos.UbicacionDto;
import java.util.List;

public interface UbicacionService {
    UbicacionDto createUbicacion(UbicacionDto ubicacionDto);
    UbicacionDto updateUbicacion(Long ubicacionId, UbicacionDto ubicacionDto);
    String deleteUbicacion(Long ubicacionId);
    UbicacionDto getUbicacion(Long ubicacionId);
    List<UbicacionDto> getUbicaciones();
}
