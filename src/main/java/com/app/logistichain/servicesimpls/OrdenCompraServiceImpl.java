package com.app.logistichain.servicesimpls;

import com.app.logistichain.dtos.OrdenCompraDto;
import com.app.logistichain.entities.OrdenCompra;
import com.app.logistichain.entities.Proveedor;
import com.app.logistichain.exceptions.NotFoundException;
import com.app.logistichain.entities.enums.EstadoOrdenCompra; // Ajusta si tu enum vive en otro paquete
import com.app.logistichain.mapperdtos.OrdenCompraMapper;
import com.app.logistichain.repositories.OrdenCompraRepository;
import com.app.logistichain.repositories.ProveedorRepository;
import com.app.logistichain.services.OrdenCompraService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor @Transactional
public class OrdenCompraServiceImpl implements OrdenCompraService {

    private final OrdenCompraRepository ordenCompraRepository;
    private final ProveedorRepository proveedorRepository;

    @Override
    public OrdenCompraDto createOrdenCompra(OrdenCompraDto dto) {
        OrdenCompra e = OrdenCompraMapper.mapOrdenCompraDtoToOrdenCompra(dto);
        if (dto.getEstado() != null) e.setEstado(EstadoOrdenCompra.valueOf(dto.getEstado()));
        Proveedor proveedor = proveedorRepository.findById(dto.getProveedorId())
                .orElseThrow(() -> new NotFoundException("Proveedor no encontrado con id " + dto.getProveedorId()));
        e.setProveedor(proveedor);
        OrdenCompra saved = ordenCompraRepository.save(e);
        return OrdenCompraMapper.mapOrdenCompraToOrdenCompraDto(saved);
    }

    @Override
    public OrdenCompraDto updateOrdenCompra(Long id, OrdenCompraDto dto) {
        OrdenCompra e = ordenCompraRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("OrdenCompra no encontrada con id " + id));

        e.setFechaEmision(dto.getFechaEmision());
        e.setFechaEntregaEsperada(dto.getFechaEntregaEsperada());
        e.setCostoTotal(dto.getCostoTotal());
        e.setUsuarioCreadorId(dto.getUsuarioCreadorId());
        if (dto.getEstado() != null) e.setEstado(EstadoOrdenCompra.valueOf(dto.getEstado()));
        if (dto.getProveedorId() != null) {
            Proveedor p = proveedorRepository.findById(dto.getProveedorId())
                    .orElseThrow(() -> new NotFoundException("Proveedor no encontrado con id " + dto.getProveedorId()));
            e.setProveedor(p);
        }
        OrdenCompra updated = ordenCompraRepository.save(e);
        return OrdenCompraMapper.mapOrdenCompraToOrdenCompraDto(updated);
    }

    @Override
    public String deleteOrdenCompra(Long id) {
        OrdenCompra e = ordenCompraRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("OrdenCompra no encontrada con id " + id));
        ordenCompraRepository.delete(e);
        return "Orden de compra eliminada";
    }

    @Override
    public OrdenCompraDto getOrdenCompra(Long id) {
        OrdenCompra e = ordenCompraRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("OrdenCompra no encontrada con id " + id));
        return OrdenCompraMapper.mapOrdenCompraToOrdenCompraDto(e);
    }

    @Override
    public List<OrdenCompraDto> getOrdenesCompra() {
        return ordenCompraRepository.findAll().stream()
                .map(OrdenCompraMapper::mapOrdenCompraToOrdenCompraDto)
                .collect(Collectors.toList());
    }
}
