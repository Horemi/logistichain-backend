package com.app.logistichain.servicesimpls;

import com.app.logistichain.dtos.EmpresaDto;
import com.app.logistichain.entities.Empresa;
import com.app.logistichain.exceptions.NotFoundException;
import com.app.logistichain.mapperdtos.EmpresaMapper;
import com.app.logistichain.repositories.EmpresaRepository;
import com.app.logistichain.services.EmpresaService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class EmpresaServiceImpl implements EmpresaService {

    private final EmpresaRepository empresaRepository;

    @Override
    public EmpresaDto createEmpresa(EmpresaDto empresaDto) {
        Empresa entity = EmpresaMapper.mapEmpresaDtoToEmpresa(empresaDto);
        Empresa saved = empresaRepository.save(entity);
        return EmpresaMapper.mapEmpresaToEmpresaDto(saved);
    }

    @Override
    public EmpresaDto updateEmpresa(Long empresaId, EmpresaDto empresaDto) {
        Empresa e = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new NotFoundException("Empresa no encontrada con id " + empresaId));

        e.setNombre(empresaDto.getNombre());
        e.setIdentificadorFiscal(empresaDto.getIdentificadorFiscal());
        e.setDireccionPrincipal(empresaDto.getDireccionPrincipal());
        e.setTelefono(empresaDto.getTelefono());
        e.setEmail(empresaDto.getEmail());
        e.setFechaCreacion(empresaDto.getFechaCreacion());

        Empresa updated = empresaRepository.save(e);
        return EmpresaMapper.mapEmpresaToEmpresaDto(updated);
    }

    @Override
    public String deleteEmpresa(Long empresaId) {
        Empresa e = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new NotFoundException("Empresa no encontrada con id " + empresaId));
        empresaRepository.delete(e);
        return "Empresa eliminada";
    }

    @Override
    public EmpresaDto getEmpresa(Long empresaId) {
        Empresa e = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new NotFoundException("Empresa no encontrada con id " + empresaId));
        return EmpresaMapper.mapEmpresaToEmpresaDto(e);
    }

    @Override
    public List<EmpresaDto> getEmpresas() {
        return empresaRepository.findAll()
                .stream()
                .map(EmpresaMapper::mapEmpresaToEmpresaDto)
                .collect(Collectors.toList());
    }
}
