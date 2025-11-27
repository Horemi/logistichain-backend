package com.app.logistichain.services;

import com.app.logistichain.dtos.ClienteDto;
import java.util.List;

public interface ClienteService {
    ClienteDto createCliente(ClienteDto dto);
    ClienteDto updateCliente(Long id, ClienteDto dto);
    String deleteCliente(Long id);
    ClienteDto getCliente(Long id);
    List<ClienteDto> getClientes();
}
