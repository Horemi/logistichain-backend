package com.app.logistichain.services;

import com.app.logistichain.dtos.RolDto;
import java.util.List;

public interface RolService {
    RolDto createRol(RolDto dto);
    RolDto updateRol(Long id, RolDto dto);
    String deleteRol(Long id);
    RolDto getRol(Long id);
    List<RolDto> getRoles();
}
