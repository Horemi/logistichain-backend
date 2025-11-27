package com.app.logistichain.servicesimpls;

import com.app.logistichain.dtos.PedidoVentaDto;
import com.app.logistichain.entities.Cliente;
import com.app.logistichain.entities.PedidoVenta;
import com.app.logistichain.exceptions.NotFoundException;
import com.app.logistichain.mapperdtos.PedidoVentaMapper;
import com.app.logistichain.entities.enums.EstadoPedido; // ajusta paquete
import com.app.logistichain.repositories.ClienteRepository;
import com.app.logistichain.repositories.PedidoVentaRepository;
import com.app.logistichain.services.PedidoVentaService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor @Transactional
public class PedidoVentaServiceImpl implements PedidoVentaService {

    private final PedidoVentaRepository repo;
    private final ClienteRepository clienteRepo;

    @Override
    public PedidoVentaDto createPedidoVenta(PedidoVentaDto dto) {
        PedidoVenta e = PedidoVentaMapper.mapPedidoVentaDtoToPedidoVenta(dto);
        if (dto.getEstado() != null) e.setEstado(EstadoPedido.valueOf(dto.getEstado()));
        Cliente c = clienteRepo.findById(dto.getClienteId())
                .orElseThrow(() -> new NotFoundException("Cliente no encontrado con id " + dto.getClienteId()));
        e.setCliente(c);
        PedidoVenta saved = repo.save(e);
        return PedidoVentaMapper.mapPedidoVentaToPedidoVentaDto(saved);
    }

    @Override
    public PedidoVentaDto updatePedidoVenta(Long id, PedidoVentaDto dto) {
        PedidoVenta e = repo.findById(id).orElseThrow(() -> new NotFoundException("PedidoVenta no encontrado con id " + id));
        e.setFechaEmision(dto.getFechaEmision());
        e.setPrecioTotal(dto.getPrecioTotal());
        e.setDireccionDespacho(dto.getDireccionDespacho());
        e.setVendedorId(dto.getVendedorId());
        if (dto.getEstado() != null) e.setEstado(EstadoPedido.valueOf(dto.getEstado()));
        if (dto.getClienteId() != null) {
            Cliente c = clienteRepo.findById(dto.getClienteId())
                    .orElseThrow(() -> new NotFoundException("Cliente no encontrado con id " + dto.getClienteId()));
            e.setCliente(c);
        }
        PedidoVenta updated = repo.save(e);
        return PedidoVentaMapper.mapPedidoVentaToPedidoVentaDto(updated);
    }

    @Override
    public String deletePedidoVenta(Long id) {
        PedidoVenta e = repo.findById(id).orElseThrow(() -> new NotFoundException("PedidoVenta no encontrado con id " + id));
        repo.delete(e);
        return "Pedido de venta eliminado";
    }

    @Override
    public PedidoVentaDto getPedidoVenta(Long id) {
        PedidoVenta e = repo.findById(id).orElseThrow(() -> new NotFoundException("PedidoVenta no encontrado con id " + id));
        return PedidoVentaMapper.mapPedidoVentaToPedidoVentaDto(e);
    }

    @Override
    public List<PedidoVentaDto> getPedidosVenta() {
        return repo.findAll().stream().map(PedidoVentaMapper::mapPedidoVentaToPedidoVentaDto).collect(Collectors.toList());
    }
}
