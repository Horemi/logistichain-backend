package com.app.logistichain.servicesimpls;

import com.app.logistichain.dtos.AlmacenDto;
import com.app.logistichain.entities.Almacen;
import com.app.logistichain.entities.Sucursal;
import com.app.logistichain.exceptions.NotFoundException;
import com.app.logistichain.mapperdtos.AlmacenMapper;
import com.app.logistichain.repositories.AlmacenRepository;
import com.app.logistichain.repositories.SucursalRepository;
import com.app.logistichain.services.AlmacenService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AlmacenServiceImpl implements AlmacenService {

    private final AlmacenRepository almacenRepository;
    private final SucursalRepository sucursalRepository;

    @Override
    public AlmacenDto createAlmacen(AlmacenDto almacenDto) {
        Almacen entity = AlmacenMapper.mapAlmacenDtoToAlmacen(almacenDto);
        Sucursal sucursal = sucursalRepository.findById(almacenDto.getSucursalId())
                .orElseThrow(() -> new NotFoundException("Sucursal no encontrada con id " + almacenDto.getSucursalId()));
        entity.setSucursal(sucursal);

        Almacen saved = almacenRepository.save(entity);
        return AlmacenMapper.mapAlmacenToAlmacenDto(saved);
    }

    @Override
    public AlmacenDto updateAlmacen(Long almacenId, AlmacenDto almacenDto) {
        Almacen a = almacenRepository.findById(almacenId)
                .orElseThrow(() -> new NotFoundException("Almacén no encontrado con id " + almacenId));

        a.setNombre(almacenDto.getNombre());
        a.setTipo(almacenDto.getTipo());

        if (almacenDto.getSucursalId() != null) {
            Sucursal sucursal = sucursalRepository.findById(almacenDto.getSucursalId())
                    .orElseThrow(() -> new NotFoundException("Sucursal no encontrada con id " + almacenDto.getSucursalId()));
            a.setSucursal(sucursal);
        }

        Almacen updated = almacenRepository.save(a);
        return AlmacenMapper.mapAlmacenToAlmacenDto(updated);
    }

    @Override
    public String deleteAlmacen(Long almacenId) {
        Almacen a = almacenRepository.findById(almacenId)
                .orElseThrow(() -> new NotFoundException("Almacén no encontrado con id " + almacenId));
        almacenRepository.delete(a);
        return "Almacén eliminado";
    }

    @Override
    public AlmacenDto getAlmacen(Long almacenId) {
        Almacen a = almacenRepository.findById(almacenId)
                .orElseThrow(() -> new NotFoundException("Almacén no encontrado con id " + almacenId));
        return AlmacenMapper.mapAlmacenToAlmacenDto(a);
    }

    @Override
    public List<AlmacenDto> getAlmacenes() {
        return almacenRepository.findAll()
                .stream()
                .map(AlmacenMapper::mapAlmacenToAlmacenDto)
                .collect(Collectors.toList());
    }
}
