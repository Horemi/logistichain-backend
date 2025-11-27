package com.app.logistichain.servicesimpls;

import com.app.logistichain.dtos.ProveedorDto;
import com.app.logistichain.entities.Proveedor;
import com.app.logistichain.exceptions.NotFoundException;
import com.app.logistichain.mapperdtos.ProveedorMapper;
import com.app.logistichain.repositories.ProveedorRepository;
import com.app.logistichain.services.ProveedorService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProveedorServiceImpl implements ProveedorService {

    private final ProveedorRepository proveedorRepository;

    @Override
    public ProveedorDto createProveedor(ProveedorDto proveedorDto) {
        Proveedor entity = ProveedorMapper.mapProveedorDtoToProveedor(proveedorDto);
        Proveedor saved = proveedorRepository.save(entity);
        return ProveedorMapper.mapProveedorToProveedorDto(saved);
    }

    @Override
    public ProveedorDto updateProveedor(Long proveedorId, ProveedorDto proveedorDto) {
        Proveedor p = proveedorRepository.findById(proveedorId)
                .orElseThrow(() -> new NotFoundException("Proveedor no encontrado con id " + proveedorId));

        p.setNombre(proveedorDto.getNombre());
        p.setIdentificadorFiscal(proveedorDto.getIdentificadorFiscal());
        p.setDireccion(proveedorDto.getDireccion());
        p.setTelefono(proveedorDto.getTelefono());
        p.setEmailContacto(proveedorDto.getEmailContacto());

        Proveedor updated = proveedorRepository.save(p);
        return ProveedorMapper.mapProveedorToProveedorDto(updated);
    }

    @Override
    public String deleteProveedor(Long proveedorId) {
        Proveedor p = proveedorRepository.findById(proveedorId)
                .orElseThrow(() -> new NotFoundException("Proveedor no encontrado con id " + proveedorId));
        proveedorRepository.delete(p);
        return "Proveedor eliminado";
    }

    @Override
    public ProveedorDto getProveedor(Long proveedorId) {
        Proveedor p = proveedorRepository.findById(proveedorId)
                .orElseThrow(() -> new NotFoundException("Proveedor no encontrado con id " + proveedorId));
        return ProveedorMapper.mapProveedorToProveedorDto(p);
    }

    @Override
    public List<ProveedorDto> getProveedores() {
        return proveedorRepository.findAll()
                .stream()
                .map(ProveedorMapper::mapProveedorToProveedorDto)
                .collect(Collectors.toList());
    }
}
