package com.app.logistichain.services;

import com.app.logistichain.dtos.RecepcionDto;
import java.util.List;

public interface RecepcionService {
    RecepcionDto createRecepcion(RecepcionDto dto);
    RecepcionDto updateRecepcion(Long id, RecepcionDto dto);
    String deleteRecepcion(Long id);
    RecepcionDto getRecepcion(Long id);
    List<RecepcionDto> getRecepciones();
}
