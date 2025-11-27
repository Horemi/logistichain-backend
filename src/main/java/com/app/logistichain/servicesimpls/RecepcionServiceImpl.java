package com.app.logistichain.servicesimpls;

import com.app.logistichain.dtos.RecepcionDto;
import com.app.logistichain.entities.OrdenCompra;
import com.app.logistichain.entities.Recepcion;
import com.app.logistichain.exceptions.NotFoundException;
import com.app.logistichain.mapperdtos.RecepcionMapper;
import com.app.logistichain.entities.enums.EstadoRecepcion; // Ajusta paquete de enum
import com.app.logistichain.repositories.OrdenCompraRepository;
import com.app.logistichain.repositories.RecepcionRepository;
import com.app.logistichain.services.RecepcionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor @Transactional
public class RecepcionServiceImpl implements RecepcionService {

    private final RecepcionRepository recepcionRepository;
    private final OrdenCompraRepository ordenCompraRepository;

    @Override
    public RecepcionDto createRecepcion(RecepcionDto dto) {
        Recepcion e = RecepcionMapper.mapRecepcionDtoToRecepcion(dto);
        if (dto.getEstado() != null) e.setEstado(EstadoRecepcion.valueOf(dto.getEstado()));
        if (dto.getOrdenCompraId() != null) {
            OrdenCompra oc = ordenCompraRepository.findById(dto.getOrdenCompraId())
                    .orElseThrow(() -> new NotFoundException("OrdenCompra no encontrada con id " + dto.getOrdenCompraId()));
            e.setOrdenCompra(oc);
        }
        Recepcion saved = recepcionRepository.save(e);
        return RecepcionMapper.mapRecepcionToRecepcionDto(saved);
    }

    @Override
    public RecepcionDto updateRecepcion(Long id, RecepcionDto dto) {
        Recepcion e = recepcionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Recepción no encontrada con id " + id));

        e.setFechaRecepcion(dto.getFechaRecepcion());
        e.setGuiaDespachoProveedor(dto.getGuiaDespachoProveedor());
        e.setUsuarioReceptorId(dto.getUsuarioReceptorId());
        if (dto.getEstado() != null) e.setEstado(EstadoRecepcion.valueOf(dto.getEstado()));
        if (dto.getOrdenCompraId() != null) {
            OrdenCompra oc = ordenCompraRepository.findById(dto.getOrdenCompraId())
                    .orElseThrow(() -> new NotFoundException("OrdenCompra no encontrada con id " + dto.getOrdenCompraId()));
            e.setOrdenCompra(oc);
        }
        Recepcion updated = recepcionRepository.save(e);
        return RecepcionMapper.mapRecepcionToRecepcionDto(updated);
    }

    @Override
    public String deleteRecepcion(Long id) {
        Recepcion e = recepcionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Recepción no encontrada con id " + id));
        recepcionRepository.delete(e);
        return "Recepción eliminada";
    }

    @Override
    public RecepcionDto getRecepcion(Long id) {
        Recepcion e = recepcionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Recepción no encontrada con id " + id));
        return RecepcionMapper.mapRecepcionToRecepcionDto(e);
    }

    @Override
    public List<RecepcionDto> getRecepciones() {
        return recepcionRepository.findAll().stream()
                .map(RecepcionMapper::mapRecepcionToRecepcionDto)
                .collect(Collectors.toList());
    }
}
