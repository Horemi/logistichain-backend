package com.app.logistichain.servicesimpls;

import com.app.logistichain.dtos.ClienteDto;
import com.app.logistichain.entities.Cliente;
import com.app.logistichain.exceptions.NotFoundException;
import com.app.logistichain.mapperdtos.ClienteMapper;
import com.app.logistichain.repositories.ClienteRepository;
import com.app.logistichain.services.ClienteService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor @Transactional
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository repo;

    @Override
    public ClienteDto createCliente(ClienteDto dto) {
        Cliente e = ClienteMapper.mapClienteDtoToCliente(dto);
        Cliente saved = repo.save(e);
        return ClienteMapper.mapClienteToClienteDto(saved);
    }

    @Override
    public ClienteDto updateCliente(Long id, ClienteDto dto) {
        Cliente e = repo.findById(id).orElseThrow(() -> new NotFoundException("Cliente no encontrado con id " + id));
        e.setNombre(dto.getNombre());
        e.setIdentificadorFiscal(dto.getIdentificadorFiscal());
        e.setDireccion(dto.getDireccion());
        e.setTelefono(dto.getTelefono());
        e.setEmail(dto.getEmail());
        Cliente updated = repo.save(e);
        return ClienteMapper.mapClienteToClienteDto(updated);
    }

    @Override
    public String deleteCliente(Long id) {
        Cliente e = repo.findById(id).orElseThrow(() -> new NotFoundException("Cliente no encontrado con id " + id));
        repo.delete(e);
        return "Cliente eliminado";
    }

    @Override
    public ClienteDto getCliente(Long id) {
        Cliente e = repo.findById(id).orElseThrow(() -> new NotFoundException("Cliente no encontrado con id " + id));
        return ClienteMapper.mapClienteToClienteDto(e);
    }

    @Override
    public List<ClienteDto> getClientes() {
        return repo.findAll().stream().map(ClienteMapper::mapClienteToClienteDto).collect(Collectors.toList());
    }
}
