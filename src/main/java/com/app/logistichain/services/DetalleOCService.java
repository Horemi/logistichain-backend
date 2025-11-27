package com.app.logistichain.services;

import com.app.logistichain.dtos.DetalleOCDto;
import java.util.List;

public interface DetalleOCService {
    DetalleOCDto createDetalleOC(DetalleOCDto dto);
    DetalleOCDto updateDetalleOC(Long id, DetalleOCDto dto);
    String deleteDetalleOC(Long id);
    DetalleOCDto getDetalleOC(Long id);
    List<DetalleOCDto> getDetallesOC();
}
