package com.app.logistichain.servicesimpls;

import com.app.logistichain.dtos.LoteDto;
import com.app.logistichain.entities.Lote;
import com.app.logistichain.entities.Producto;
import com.app.logistichain.exceptions.NotFoundException;
import com.app.logistichain.mapperdtos.LoteMapper;
import com.app.logistichain.repositories.LoteRepository;
import com.app.logistichain.repositories.ProductoRepository;
import com.app.logistichain.services.LoteService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class LoteServiceImpl implements LoteService {

    private final LoteRepository loteRepository;
    private final ProductoRepository productoRepository;

    @Override
    public LoteDto createLote(LoteDto loteDto) {
        Lote entity = LoteMapper.mapLoteDtoToLote(loteDto);
        Producto producto = productoRepository.findById(loteDto.getProductoId())
                .orElseThrow(() -> new NotFoundException("Producto no encontrado con id " + loteDto.getProductoId()));
        entity.setProducto(producto);

        Lote saved = loteRepository.save(entity);
        return LoteMapper.mapLoteToLoteDto(saved);
    }

    @Override
    public LoteDto updateLote(Long loteId, LoteDto loteDto) {
        Lote l = loteRepository.findById(loteId)
                .orElseThrow(() -> new NotFoundException("Lote no encontrado con id " + loteId));

        l.setCodigoLote(loteDto.getCodigoLote());
        l.setFechaVencimiento(loteDto.getFechaVencimiento());
        l.setFechaRecepcion(loteDto.getFechaRecepcion());
        l.setStockActual(loteDto.getStockActual());

        if (loteDto.getProductoId() != null) {
            Producto producto = productoRepository.findById(loteDto.getProductoId())
                    .orElseThrow(() -> new NotFoundException("Producto no encontrado con id " + loteDto.getProductoId()));
            l.setProducto(producto);
        }

        Lote updated = loteRepository.save(l);
        return LoteMapper.mapLoteToLoteDto(updated);
    }

    @Override
    public String deleteLote(Long loteId) {
        Lote l = loteRepository.findById(loteId)
                .orElseThrow(() -> new NotFoundException("Lote no encontrado con id " + loteId));
        loteRepository.delete(l);
        return "Lote eliminado";
    }

    @Override
    public LoteDto getLote(Long loteId) {
        Lote l = loteRepository.findById(loteId)
                .orElseThrow(() -> new NotFoundException("Lote no encontrado con id " + loteId));
        return LoteMapper.mapLoteToLoteDto(l);
    }

    @Override
    public List<LoteDto> getLotes() {
        return loteRepository.findAll()
                .stream()
                .map(LoteMapper::mapLoteToLoteDto)
                .collect(Collectors.toList());
    }
}
