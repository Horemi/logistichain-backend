package com.app.logistichain.servicesimpls;

import com.app.logistichain.dtos.UbicacionDto;
import com.app.logistichain.entities.Almacen;
import com.app.logistichain.entities.Ubicacion;
import com.app.logistichain.exceptions.NotFoundException;
import com.app.logistichain.mapperdtos.UbicacionMapper;
import com.app.logistichain.repositories.AlmacenRepository;
import com.app.logistichain.repositories.UbicacionRepository;
import com.app.logistichain.services.UbicacionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UbicacionServiceImpl implements UbicacionService {

    private final UbicacionRepository ubicacionRepository;
    private final AlmacenRepository almacenRepository;

    @Override
    public UbicacionDto createUbicacion(UbicacionDto ubicacionDto) {
        Ubicacion entity = UbicacionMapper.mapUbicacionDtoToUbicacion(ubicacionDto);
        Almacen almacen = almacenRepository.findById(ubicacionDto.getAlmacenId())
                .orElseThrow(() -> new NotFoundException("Almacén no encontrado con id " + ubicacionDto.getAlmacenId()));
        entity.setAlmacen(almacen);

        Ubicacion saved = ubicacionRepository.save(entity);
        return UbicacionMapper.mapUbicacionToUbicacionDto(saved);
    }

    @Override
    public UbicacionDto updateUbicacion(Long ubicacionId, UbicacionDto ubicacionDto) {
        Ubicacion u = ubicacionRepository.findById(ubicacionId)
                .orElseThrow(() -> new NotFoundException("Ubicación no encontrada con id " + ubicacionId));

        u.setCodigo(ubicacionDto.getCodigo());
        u.setPasillo(ubicacionDto.getPasillo());
        u.setEstante(ubicacionDto.getEstante());
        u.setNivel(ubicacionDto.getNivel());

        if (ubicacionDto.getAlmacenId() != null) {
            Almacen almacen = almacenRepository.findById(ubicacionDto.getAlmacenId())
                    .orElseThrow(() -> new NotFoundException("Almacén no encontrado con id " + ubicacionDto.getAlmacenId()));
            u.setAlmacen(almacen);
        }

        Ubicacion updated = ubicacionRepository.save(u);
        return UbicacionMapper.mapUbicacionToUbicacionDto(updated);
    }

    @Override
    public String deleteUbicacion(Long ubicacionId) {
        Ubicacion u = ubicacionRepository.findById(ubicacionId)
                .orElseThrow(() -> new NotFoundException("Ubicación no encontrada con id " + ubicacionId));
        ubicacionRepository.delete(u);
        return "Ubicación eliminada";
    }

    @Override
    public UbicacionDto getUbicacion(Long ubicacionId) {
        Ubicacion u = ubicacionRepository.findById(ubicacionId)
                .orElseThrow(() -> new NotFoundException("Ubicación no encontrada con id " + ubicacionId));
        return UbicacionMapper.mapUbicacionToUbicacionDto(u);
    }

    @Override
    public List<UbicacionDto> getUbicaciones() {
        return ubicacionRepository.findAll()
                .stream()
                .map(UbicacionMapper::mapUbicacionToUbicacionDto)
                .collect(Collectors.toList());
    }
}
