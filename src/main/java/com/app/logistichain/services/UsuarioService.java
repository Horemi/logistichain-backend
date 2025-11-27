package com.app.logistichain.services;

import com.app.logistichain.dtos.UsuarioDto;
import java.util.List;

public interface UsuarioService {
    UsuarioDto createUsuario(UsuarioDto dto);
    UsuarioDto updateUsuario(Long id, UsuarioDto dto);
    String deleteUsuario(Long id);
    UsuarioDto getUsuario(Long id);
    List<UsuarioDto> getUsuarios();
}
