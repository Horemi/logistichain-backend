package com.app.logistichain.servicesimpls;

import com.app.logistichain.dtos.ItemPickingDto;
import com.app.logistichain.entities.*;
import com.app.logistichain.exceptions.NotFoundException;
import com.app.logistichain.mapperdtos.ItemPickingMapper;
import com.app.logistichain.repositories.*;
import com.app.logistichain.services.ItemPickingService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor @Transactional
public class ItemPickingServiceImpl implements ItemPickingService {

    private final ItemPickingRepository repo;
    private final PickingRepository pickingRepo;
    private final DetallePVRepository dpvRepo;
    private final ProductoRepository prodRepo;
    private final LoteRepository loteRepo;
    private final UbicacionRepository ubicRepo;

    @Override
    public ItemPickingDto createItemPicking(ItemPickingDto dto) {
        ItemPicking e = ItemPickingMapper.mapItemPickingDtoToItemPicking(dto);
        Picking picking = pickingRepo.findById(dto.getPickingId())
                .orElseThrow(() -> new NotFoundException("Picking no encontrado con id " + dto.getPickingId()));
        DetallePV dpv = dpvRepo.findById(dto.getDetallePvId())
                .orElseThrow(() -> new NotFoundException("DetallePV no encontrado con id " + dto.getDetallePvId()));
        Producto p = prodRepo.findById(dto.getProductoId())
                .orElseThrow(() -> new NotFoundException("Producto no encontrado con id " + dto.getProductoId()));
        Lote l = loteRepo.findById(dto.getLoteId())
                .orElseThrow(() -> new NotFoundException("Lote no encontrado con id " + dto.getLoteId()));
        Ubicacion u = ubicRepo.findById(dto.getUbicacionId())
                .orElseThrow(() -> new NotFoundException("Ubicación no encontrada con id " + dto.getUbicacionId()));

        e.setPicking(picking);
        e.setDetallePv(dpv);
        e.setProducto(p);
        e.setLote(l);
        e.setUbicacion(u);

        ItemPicking saved = repo.save(e);
        return ItemPickingMapper.mapItemPickingToItemPickingDto(saved);
    }

    @Override
    public ItemPickingDto updateItemPicking(Long id, ItemPickingDto dto) {
        ItemPicking e = repo.findById(id).orElseThrow(() -> new NotFoundException("ItemPicking no encontrado con id " + id));
        e.setCantidadARecoger(dto.getCantidadARecoger());
        e.setCantidadRecogida(dto.getCantidadRecogida());

        if (dto.getPickingId() != null) {
            Picking picking = pickingRepo.findById(dto.getPickingId())
                    .orElseThrow(() -> new NotFoundException("Picking no encontrado con id " + dto.getPickingId()));
            e.setPicking(picking);
        }
        if (dto.getDetallePvId() != null) {
            DetallePV dpv = dpvRepo.findById(dto.getDetallePvId())
                    .orElseThrow(() -> new NotFoundException("DetallePV no encontrado con id " + dto.getDetallePvId()));
            e.setDetallePv(dpv);
        }
        if (dto.getProductoId() != null) {
            Producto p = prodRepo.findById(dto.getProductoId())
                    .orElseThrow(() -> new NotFoundException("Producto no encontrado con id " + dto.getProductoId()));
            e.setProducto(p);
        }
        if (dto.getLoteId() != null) {
            Lote l = loteRepo.findById(dto.getLoteId())
                    .orElseThrow(() -> new NotFoundException("Lote no encontrado con id " + dto.getLoteId()));
            e.setLote(l);
        }
        if (dto.getUbicacionId() != null) {
            Ubicacion u = ubicRepo.findById(dto.getUbicacionId())
                    .orElseThrow(() -> new NotFoundException("Ubicación no encontrada con id " + dto.getUbicacionId()));
            e.setUbicacion(u);
        }
        ItemPicking updated = repo.save(e);
        return ItemPickingMapper.mapItemPickingToItemPickingDto(updated);
    }

    @Override
    public String deleteItemPicking(Long id) {
        ItemPicking e = repo.findById(id).orElseThrow(() -> new NotFoundException("ItemPicking no encontrado con id " + id));
        repo.delete(e);
        return "ItemPicking eliminado";
    }

    @Override
    public ItemPickingDto getItemPicking(Long id) {
        ItemPicking e = repo.findById(id).orElseThrow(() -> new NotFoundException("ItemPicking no encontrado con id " + id));
        return ItemPickingMapper.mapItemPickingToItemPickingDto(e);
    }

    @Override
    public List<ItemPickingDto> getItemsPicking() {
        return repo.findAll().stream().map(ItemPickingMapper::mapItemPickingToItemPickingDto).collect(Collectors.toList());
    }
}
