package com.app.logistichain.servicesimpls;

import com.app.logistichain.dtos.DetallePVDto;
import com.app.logistichain.entities.DetallePV;
import com.app.logistichain.entities.PedidoVenta;
import com.app.logistichain.entities.Producto;
import com.app.logistichain.exceptions.NotFoundException;
import com.app.logistichain.mapperdtos.DetallePVMapper;
import com.app.logistichain.repositories.DetallePVRepository;
import com.app.logistichain.repositories.PedidoVentaRepository;
import com.app.logistichain.repositories.ProductoRepository;
import com.app.logistichain.services.DetallePVService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor @Transactional
public class DetallePVServiceImpl implements DetallePVService {

    private final DetallePVRepository repo;
    private final PedidoVentaRepository pvRepo;
    private final ProductoRepository prodRepo;

    @Override
    public DetallePVDto createDetallePV(DetallePVDto dto) {
        DetallePV e = DetallePVMapper.mapDetallePVDtoToDetallePV(dto);
        PedidoVenta pv = pvRepo.findById(dto.getPedidoVentaId())
                .orElseThrow(() -> new NotFoundException("PedidoVenta no encontrado con id " + dto.getPedidoVentaId()));
        Producto p = prodRepo.findById(dto.getProductoId())
                .orElseThrow(() -> new NotFoundException("Producto no encontrado con id " + dto.getProductoId()));
        e.setPedido(pv);
        e.setProducto(p);
        DetallePV saved = repo.save(e);
        return DetallePVMapper.mapDetallePVToDetallePVDto(saved);
    }

    @Override
    public DetallePVDto updateDetallePV(Long id, DetallePVDto dto) {
        DetallePV e = repo.findById(id).orElseThrow(() -> new NotFoundException("DetallePV no encontrado con id " + id));
        e.setCantidad(dto.getCantidad());
        e.setPrecioUnitarioVenta(dto.getPrecioUnitarioVenta());
        if (dto.getPedidoVentaId() != null) {
            PedidoVenta pv = pvRepo.findById(dto.getPedidoVentaId())
                    .orElseThrow(() -> new NotFoundException("PedidoVenta no encontrado con id " + dto.getPedidoVentaId()));
            e.setPedido(pv);
        }
        if (dto.getProductoId() != null) {
            Producto p = prodRepo.findById(dto.getProductoId())
                    .orElseThrow(() -> new NotFoundException("Producto no encontrado con id " + dto.getProductoId()));
            e.setProducto(p);
        }
        DetallePV updated = repo.save(e);
        return DetallePVMapper.mapDetallePVToDetallePVDto(updated);
    }

    @Override
    public String deleteDetallePV(Long id) {
        DetallePV e = repo.findById(id).orElseThrow(() -> new NotFoundException("DetallePV no encontrado con id " + id));
        repo.delete(e);
        return "Detalle de pedido eliminado";
    }

    @Override
    public DetallePVDto getDetallePV(Long id) {
        DetallePV e = repo.findById(id).orElseThrow(() -> new NotFoundException("DetallePV no encontrado con id " + id));
        return DetallePVMapper.mapDetallePVToDetallePVDto(e);
    }

    @Override
    public List<DetallePVDto> getDetallesPV() {
        return repo.findAll().stream().map(DetallePVMapper::mapDetallePVToDetallePVDto).collect(Collectors.toList());
    }
}
