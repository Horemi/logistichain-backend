package com.app.logistichain.services;

import com.app.logistichain.dtos.AlmacenDto;
import java.util.List;

public interface AlmacenService {
    AlmacenDto createAlmacen(AlmacenDto almacenDto);
    AlmacenDto updateAlmacen(Long almacenId, AlmacenDto almacenDto);
    String deleteAlmacen(Long almacenId);
    AlmacenDto getAlmacen(Long almacenId);
    List<AlmacenDto> getAlmacenes();
}
