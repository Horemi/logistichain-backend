package com.app.logistichain.servicesimpls;

import com.app.logistichain.dtos.KardexDto;
import com.app.logistichain.entities.*;
import com.app.logistichain.exceptions.NotFoundException;
import com.app.logistichain.mapperdtos.KardexMapper;
import com.app.logistichain.entities.enums.TipoMovimiento; // ajusta paquete
import com.app.logistichain.repositories.*;
import com.app.logistichain.services.KardexService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor @Transactional
public class KardexServiceImpl implements KardexService {

    private final KardexRepository repo;
    private final ProductoRepository prodRepo;
    private final LoteRepository loteRepo;
    private final AlmacenRepository almRepo;

    @Override
    public KardexDto createKardex(KardexDto dto) {
        Kardex e = KardexMapper.mapKardexDtoToKardex(dto);
        if (dto.getTipoMovimiento() != null) e.setTipoMovimiento(TipoMovimiento.valueOf(dto.getTipoMovimiento()));

        Producto p = prodRepo.findById(dto.getProductoId())
                .orElseThrow(() -> new NotFoundException("Producto no encontrado con id " + dto.getProductoId()));
        e.setProducto(p);

        if (dto.getLoteId() != null) {
            Lote l = loteRepo.findById(dto.getLoteId())
                    .orElseThrow(() -> new NotFoundException("Lote no encontrado con id " + dto.getLoteId()));
            e.setLote(l);
        }

        Almacen a = almRepo.findById(dto.getAlmacenId())
                .orElseThrow(() -> new NotFoundException("Almacén no encontrado con id " + dto.getAlmacenId()));
        e.setAlmacen(a);

        Kardex saved = repo.save(e);
        return KardexMapper.mapKardexToKardexDto(saved);
    }

    @Override
    public KardexDto updateKardex(Long id, KardexDto dto) {
        Kardex e = repo.findById(id).orElseThrow(() -> new NotFoundException("Kardex no encontrado con id " + id));
        e.setFecha(dto.getFecha());
        e.setCantidad(dto.getCantidad());
        e.setSaldoResultante(dto.getSaldoResultante());
        e.setReferenciaDocumento(dto.getReferenciaDocumento());
        if (dto.getTipoMovimiento() != null) e.setTipoMovimiento(TipoMovimiento.valueOf(dto.getTipoMovimiento()));
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
        if (dto.getAlmacenId() != null) {
            Almacen a = almRepo.findById(dto.getAlmacenId())
                    .orElseThrow(() -> new NotFoundException("Almacén no encontrado con id " + dto.getAlmacenId()));
            e.setAlmacen(a);
        }
        Kardex updated = repo.save(e);
        return KardexMapper.mapKardexToKardexDto(updated);
    }

    @Override
    public String deleteKardex(Long id) {
        Kardex e = repo.findById(id).orElseThrow(() -> new NotFoundException("Kardex no encontrado con id " + id));
        repo.delete(e);
        return "Kardex eliminado";
    }

    @Override
    public KardexDto getKardex(Long id) {
        Kardex e = repo.findById(id).orElseThrow(() -> new NotFoundException("Kardex no encontrado con id " + id));
        return KardexMapper.mapKardexToKardexDto(e);
    }

    @Override
    public List<KardexDto> getKardexes() {
        return repo.findAll().stream().map(KardexMapper::mapKardexToKardexDto).collect(Collectors.toList());
    }
}
