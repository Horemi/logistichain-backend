package com.app.logistichain.services;

import com.app.logistichain.dtos.ItemPickingDto;
import java.util.List;

public interface ItemPickingService {
    ItemPickingDto createItemPicking(ItemPickingDto dto);
    ItemPickingDto updateItemPicking(Long id, ItemPickingDto dto);
    String deleteItemPicking(Long id);
    ItemPickingDto getItemPicking(Long id);
    List<ItemPickingDto> getItemsPicking();
}
