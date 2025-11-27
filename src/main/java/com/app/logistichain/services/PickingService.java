package com.app.logistichain.services;

import com.app.logistichain.dtos.PickingDto;
import java.util.List;

public interface PickingService {
    PickingDto createPicking(PickingDto dto);
    PickingDto updatePicking(Long id, PickingDto dto);
    String deletePicking(Long id);
    PickingDto getPicking(Long id);
    List<PickingDto> getPickings();
}
