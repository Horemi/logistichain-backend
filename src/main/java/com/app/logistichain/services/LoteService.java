package com.app.logistichain.services;

import com.app.logistichain.dtos.LoteDto;
import java.util.List;

public interface LoteService {
    LoteDto createLote(LoteDto loteDto);
    LoteDto updateLote(Long loteId, LoteDto loteDto);
    String deleteLote(Long loteId);
    LoteDto getLote(Long loteId);
    List<LoteDto> getLotes();
}
