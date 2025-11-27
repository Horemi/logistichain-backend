package com.app.logistichain.services;

import com.app.logistichain.dtos.DetallePVDto;
import java.util.List;

public interface DetallePVService {
    DetallePVDto createDetallePV(DetallePVDto dto);
    DetallePVDto updateDetallePV(Long id, DetallePVDto dto);
    String deleteDetallePV(Long id);
    DetallePVDto getDetallePV(Long id);
    List<DetallePVDto> getDetallesPV();
}
