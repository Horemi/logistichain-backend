package com.app.logistichain.services;

import com.app.logistichain.dtos.KardexDto;
import java.util.List;

public interface KardexService {
    KardexDto createKardex(KardexDto dto);
    KardexDto updateKardex(Long id, KardexDto dto);
    String deleteKardex(Long id);
    KardexDto getKardex(Long id);
    List<KardexDto> getKardexes();
}
