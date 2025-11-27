package com.app.logistichain.servicesimpls;

import com.app.logistichain.dtos.UsuarioDto;
import com.app.logistichain.entities.Rol;
import com.app.logistichain.entities.Usuario;
import com.app.logistichain.exceptions.NotFoundException;
import com.app.logistichain.mapperdtos.UsuarioMapper;
import com.app.logistichain.repositories.RolRepository;
import com.app.logistichain.repositories.UsuarioRepository;
import com.app.logistichain.services.UsuarioService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor @Transactional
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    @Override
    public UsuarioDto createUsuario(UsuarioDto dto) {
        Usuario e = UsuarioMapper.mapUsuarioDtoToUsuario(dto);

        if (dto.getRolIds() != null && !dto.getRolIds().isEmpty()) {
            Set<Rol> roles = new HashSet<>();
            for (Long rid : dto.getRolIds()) {
                Rol r = rolRepository.findById(rid).orElseThrow(() -> new NotFoundException("Rol no encontrado con id " + rid));
                roles.add(r);
            }
            e.setRoles(roles);
        }
        Usuario saved = usuarioRepository.save(e);
        return UsuarioMapper.mapUsuarioToUsuarioDto(saved);
    }

    @Override
    public UsuarioDto updateUsuario(Long id, UsuarioDto dto) {
        Usuario e = usuarioRepository.findById(id).orElseThrow(() -> new NotFoundException("Usuario no encontrado con id " + id));
        e.setNombreUsuario(dto.getNombreUsuario());
        e.setNombreCompleto(dto.getNombreCompleto());
        e.setEmail(dto.getEmail());
        e.setActivo(dto.getActivo());

        e.getRoles().clear();
        if (dto.getRolIds() != null && !dto.getRolIds().isEmpty()) {
            Set<Rol> roles = new HashSet<>();
            for (Long rid : dto.getRolIds()) {
                Rol r = rolRepository.findById(rid).orElseThrow(() -> new NotFoundException("Rol no encontrado con id " + rid));
                roles.add(r);
            }
            e.setRoles(roles);
        }
        Usuario updated = usuarioRepository.save(e);
        return UsuarioMapper.mapUsuarioToUsuarioDto(updated);
    }

    @Override
    public String deleteUsuario(Long id) {
        Usuario e = usuarioRepository.findById(id).orElseThrow(() -> new NotFoundException("Usuario no encontrado con id " + id));
        usuarioRepository.delete(e);
        return "Usuario eliminado";
    }

    @Override
    public UsuarioDto getUsuario(Long id) {
        Usuario e = usuarioRepository.findById(id).orElseThrow(() -> new NotFoundException("Usuario no encontrado con id " + id));
        return UsuarioMapper.mapUsuarioToUsuarioDto(e);
    }

    @Override
    public List<UsuarioDto> getUsuarios() {
        return usuarioRepository.findAll().stream().map(UsuarioMapper::mapUsuarioToUsuarioDto).collect(Collectors.toList());
    }
}
