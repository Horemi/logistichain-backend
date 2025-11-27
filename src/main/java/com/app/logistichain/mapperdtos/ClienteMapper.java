package com.app.logistichain.mapperdtos;

import com.app.logistichain.dtos.ClienteDto;
import com.app.logistichain.entities.Cliente;

public class ClienteMapper {

    public static ClienteDto mapClienteToClienteDto(Cliente e) {
        if (e == null) return null;
        ClienteDto dto = new ClienteDto();
        dto.setId(e.getId());
        dto.setNombre(e.getNombre());
        dto.setIdentificadorFiscal(e.getIdentificadorFiscal());
        dto.setDireccion(e.getDireccion());
        dto.setTelefono(e.getTelefono());
        dto.setEmail(e.getEmail());
        return dto;
    }

    public static Cliente mapClienteDtoToCliente(ClienteDto dto) {
        if (dto == null) return null;
        Cliente e = new Cliente();
        e.setId(dto.getId());
        e.setNombre(dto.getNombre());
        e.setIdentificadorFiscal(dto.getIdentificadorFiscal());
        e.setDireccion(dto.getDireccion());
        e.setTelefono(dto.getTelefono());
        e.setEmail(dto.getEmail());
        return e;
    }
}
