package com.app.logistichain.servicesimpls;

import com.app.logistichain.dtos.DetalleOCDto;
import com.app.logistichain.entities.DetalleOC;
import com.app.logistichain.entities.OrdenCompra;
import com.app.logistichain.entities.Producto;
import com.app.logistichain.exceptions.NotFoundException;
import com.app.logistichain.mapperdtos.DetalleOCMapper;
import com.app.logistichain.repositories.DetalleOCRepository;
import com.app.logistichain.repositories.OrdenCompraRepository;
import com.app.logistichain.repositories.ProductoRepository;
import com.app.logistichain.services.DetalleOCService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor @Transactional
public class DetalleOCServiceImpl implements DetalleOCService {

    private final DetalleOCRepository detalleOCRepository;
    private final OrdenCompraRepository ordenCompraRepository;
    private final ProductoRepository productoRepository;

    @Override
    public DetalleOCDto createDetalleOC(DetalleOCDto dto) {
        DetalleOC e = DetalleOCMapper.mapDetalleOCDtoToDetalleOC(dto);
        OrdenCompra oc = ordenCompraRepository.findById(dto.getOrdenCompraId())
                .orElseThrow(() -> new NotFoundException("OrdenCompra no encontrada con id " + dto.getOrdenCompraId()));
        Producto p = productoRepository.findById(dto.getProductoId())
                .orElseThrow(() -> new NotFoundException("Producto no encontrado con id " + dto.getProductoId()));
        e.setOrdenCompra(oc);
        e.setProducto(p);
        DetalleOC saved = detalleOCRepository.save(e);
        return DetalleOCMapper.mapDetalleOCToDetalleOCDto(saved);
    }

    @Override
    public DetalleOCDto updateDetalleOC(Long id, DetalleOCDto dto) {
        DetalleOC e = detalleOCRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("DetalleOC no encontrado con id " + id));
        e.setCantidad(dto.getCantidad());
        e.setCostoUnitario(dto.getCostoUnitario());
        if (dto.getOrdenCompraId() != null) {
            OrdenCompra oc = ordenCompraRepository.findById(dto.getOrdenCompraId())
                    .orElseThrow(() -> new NotFoundException("OrdenCompra no encontrada con id " + dto.getOrdenCompraId()));
            e.setOrdenCompra(oc);
        }
        if (dto.getProductoId() != null) {
            Producto p = productoRepository.findById(dto.getProductoId())
                    .orElseThrow(() -> new NotFoundException("Producto no encontrado con id " + dto.getProductoId()));
            e.setProducto(p);
        }
        DetalleOC updated = detalleOCRepository.save(e);
        return DetalleOCMapper.mapDetalleOCToDetalleOCDto(updated);
    }

    @Override
    public String deleteDetalleOC(Long id) {
        DetalleOC e = detalleOCRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("DetalleOC no encontrado con id " + id));
        detalleOCRepository.delete(e);
        return "DetalleOC eliminado";
    }

    @Override
    public DetalleOCDto getDetalleOC(Long id) {
        DetalleOC e = detalleOCRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("DetalleOC no encontrado con id " + id));
        return DetalleOCMapper.mapDetalleOCToDetalleOCDto(e);
    }

    @Override
    public List<DetalleOCDto> getDetallesOC() {
        return detalleOCRepository.findAll().stream()
                .map(DetalleOCMapper::mapDetalleOCToDetalleOCDto)
                .collect(Collectors.toList());
    }
}
