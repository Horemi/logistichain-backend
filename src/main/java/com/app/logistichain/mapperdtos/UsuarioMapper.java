package com.app.logistichain.mapperdtos;

import com.app.logistichain.dtos.UsuarioDto;
import com.app.logistichain.entities.Rol;
import com.app.logistichain.entities.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioMapper {

    public static UsuarioDto mapUsuarioToUsuarioDto(Usuario e) {
        if (e == null) return null;
        UsuarioDto dto = new UsuarioDto();
        dto.setId(e.getId());
        dto.setNombreUsuario(e.getNombreUsuario());
        // Por seguridad, NO devolvemos la contraseña; si la necesitas, comenta la siguiente línea
        dto.setContrasena(null);
        dto.setNombreCompleto(e.getNombreCompleto());
        dto.setEmail(e.getEmail());
        dto.setActivo(e.getActivo());

        List<Long> rolIds = new ArrayList<>();
        List<String> rolNombres = new ArrayList<>();
        if (e.getRoles() != null) {
            for (Rol r : e.getRoles()) {
                if (r != null) {
                    rolIds.add(r.getId());
                    rolNombres.add(r.getNombre() != null ? r.getNombre().name() : null);
                }
            }
        }
        dto.setRolIds(rolIds);
        dto.setRolNombres(rolNombres);
        return dto;
    }

    public static Usuario mapUsuarioDtoToUsuario(UsuarioDto dto) {
        if (dto == null) return null;
        Usuario e = new Usuario();
        e.setId(dto.getId());
        e.setNombreUsuario(dto.getNombreUsuario());
        e.setContrasena(dto.getContrasena()); // hashing en Service
        e.setNombreCompleto(dto.getNombreCompleto());
        e.setEmail(dto.getEmail());
        e.setActivo(dto.getActivo());
        // roles se asignan en Service usando rolIds
        return e;
    }
}
