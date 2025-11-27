package com.app.logistichain.servicesimpls;

import com.app.logistichain.dtos.RolDto;
import com.app.logistichain.entities.Rol;
import com.app.logistichain.exceptions.NotFoundException;
import com.app.logistichain.mapperdtos.RolMapper;
import com.app.logistichain.entities.enums.RolNombre; // ajusta si tu enum vive en entities
import com.app.logistichain.repositories.RolRepository;
import com.app.logistichain.services.RolService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor @Transactional
public class RolServiceImpl implements RolService {

    private final RolRepository repo;

    @Override
    public RolDto createRol(RolDto dto) {
        Rol e = RolMapper.mapRolDtoToRol(dto);
        if (dto.getNombre() != null) e.setNombre(RolNombre.valueOf(dto.getNombre()));
        Rol saved = repo.save(e);
        return RolMapper.mapRolToRolDto(saved);
    }

    @Override
    public RolDto updateRol(Long id, RolDto dto) {
        Rol e = repo.findById(id).orElseThrow(() -> new NotFoundException("Rol no encontrado con id " + id));
        if (dto.getNombre() != null) e.setNombre(RolNombre.valueOf(dto.getNombre()));
        Rol updated = repo.save(e);
        return RolMapper.mapRolToRolDto(updated);
    }

    @Override
    public String deleteRol(Long id) {
        Rol e = repo.findById(id).orElseThrow(() -> new NotFoundException("Rol no encontrado con id " + id));
        repo.delete(e);
        return "Rol eliminado";
    }

    @Override
    public RolDto getRol(Long id) {
        Rol e = repo.findById(id).orElseThrow(() -> new NotFoundException("Rol no encontrado con id " + id));
        return RolMapper.mapRolToRolDto(e);
    }

    @Override
    public List<RolDto> getRoles() {
        return repo.findAll().stream().map(RolMapper::mapRolToRolDto).collect(Collectors.toList());
    }
}
