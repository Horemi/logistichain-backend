package com.app.logistichain.servicesimpls;

import com.app.logistichain.dtos.PickingDto;
import com.app.logistichain.entities.PedidoVenta;
import com.app.logistichain.entities.Picking;
import com.app.logistichain.exceptions.NotFoundException;
import com.app.logistichain.mapperdtos.PickingMapper;
import com.app.logistichain.entities.enums.EstadoPicking; // ajusta paquete
import com.app.logistichain.repositories.PedidoVentaRepository;
import com.app.logistichain.repositories.PickingRepository;
import com.app.logistichain.services.PickingService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor @Transactional
public class PickingServiceImpl implements PickingService {

    private final PickingRepository repo;
    private final PedidoVentaRepository pvRepo;

    @Override
    public PickingDto createPicking(PickingDto dto) {
        Picking e = PickingMapper.mapPickingDtoToPicking(dto);
        if (dto.getEstado() != null) e.setEstado(EstadoPicking.valueOf(dto.getEstado()));
        PedidoVenta pv = pvRepo.findById(dto.getPedidoVentaId())
                .orElseThrow(() -> new NotFoundException("PedidoVenta no encontrado con id " + dto.getPedidoVentaId()));
        e.setPedido(pv);
        Picking saved = repo.save(e);
        return PickingMapper.mapPickingToPickingDto(saved);
    }

    @Override
    public PickingDto updatePicking(Long id, PickingDto dto) {
        Picking e = repo.findById(id).orElseThrow(() -> new NotFoundException("Picking no encontrado con id " + id));
        e.setFechaAsignacion(dto.getFechaAsignacion());
        e.setPickerAsignadoId(dto.getPickerAsignadoId());
        if (dto.getEstado() != null) e.setEstado(EstadoPicking.valueOf(dto.getEstado()));
        if (dto.getPedidoVentaId() != null) {
            PedidoVenta pv = pvRepo.findById(dto.getPedidoVentaId())
                    .orElseThrow(() -> new NotFoundException("PedidoVenta no encontrado con id " + dto.getPedidoVentaId()));
            e.setPedido(pv);
        }
        Picking updated = repo.save(e);
        return PickingMapper.mapPickingToPickingDto(updated);
    }

    @Override
    public String deletePicking(Long id) {
        Picking e = repo.findById(id).orElseThrow(() -> new NotFoundException("Picking no encontrado con id " + id));
        repo.delete(e);
        return "Picking eliminado";
    }

    @Override
    public PickingDto getPicking(Long id) {
        Picking e = repo.findById(id).orElseThrow(() -> new NotFoundException("Picking no encontrado con id " + id));
        return PickingMapper.mapPickingToPickingDto(e);
    }

    @Override
    public List<PickingDto> getPickings() {
        return repo.findAll().stream().map(PickingMapper::mapPickingToPickingDto).collect(Collectors.toList());
    }
}
