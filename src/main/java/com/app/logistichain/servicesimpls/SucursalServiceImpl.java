package com.app.logistichain.servicesimpls;

import com.app.logistichain.dtos.SucursalDto;
import com.app.logistichain.entities.Empresa;
import com.app.logistichain.entities.Sucursal;
import com.app.logistichain.exceptions.NotFoundException;
import com.app.logistichain.mapperdtos.SucursalMapper;
import com.app.logistichain.repositories.EmpresaRepository;
import com.app.logistichain.repositories.SucursalRepository;
import com.app.logistichain.services.SucursalService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class SucursalServiceImpl implements SucursalService {

    private final SucursalRepository sucursalRepository;
    private final EmpresaRepository empresaRepository;

    @Override
    public SucursalDto createSucursal(SucursalDto sucursalDto) {
        Sucursal entity = SucursalMapper.mapSucursalDtoToSucursal(sucursalDto);
        Empresa empresa = empresaRepository.findById(sucursalDto.getEmpresaId())
                .orElseThrow(() -> new NotFoundException("Empresa no encontrada con id " + sucursalDto.getEmpresaId()));
        entity.setEmpresa(empresa);

        Sucursal saved = sucursalRepository.save(entity);
        return SucursalMapper.mapSucursalToSucursalDto(saved);
    }

    @Override
    public SucursalDto updateSucursal(Long sucursalId, SucursalDto sucursalDto) {
        Sucursal s = sucursalRepository.findById(sucursalId)
                .orElseThrow(() -> new NotFoundException("Sucursal no encontrada con id " + sucursalId));

        s.setNombre(sucursalDto.getNombre());
        s.setDireccion(sucursalDto.getDireccion());
        s.setTelefono(sucursalDto.getTelefono());

        if (sucursalDto.getEmpresaId() != null) {
            Empresa empresa = empresaRepository.findById(sucursalDto.getEmpresaId())
                    .orElseThrow(() -> new NotFoundException("Empresa no encontrada con id " + sucursalDto.getEmpresaId()));
            s.setEmpresa(empresa);
        }

        Sucursal updated = sucursalRepository.save(s);
        return SucursalMapper.mapSucursalToSucursalDto(updated);
    }

    @Override
    public String deleteSucursal(Long sucursalId) {
        Sucursal s = sucursalRepository.findById(sucursalId)
                .orElseThrow(() -> new NotFoundException("Sucursal no encontrada con id " + sucursalId));
        sucursalRepository.delete(s);
        return "Sucursal eliminada";
    }

    @Override
    public SucursalDto getSucursal(Long sucursalId) {
        Sucursal s = sucursalRepository.findById(sucursalId)
                .orElseThrow(() -> new NotFoundException("Sucursal no encontrada con id " + sucursalId));
        return SucursalMapper.mapSucursalToSucursalDto(s);
    }

    @Override
    public List<SucursalDto> getSucursales() {
        return sucursalRepository.findAll()
                .stream()
                .map(SucursalMapper::mapSucursalToSucursalDto)
                .collect(Collectors.toList());
    }
}
