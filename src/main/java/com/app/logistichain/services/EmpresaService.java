package com.app.logistichain.services;

import com.app.logistichain.dtos.EmpresaDto;
import java.util.List;

public interface EmpresaService {
    EmpresaDto createEmpresa(EmpresaDto empresaDto);
    EmpresaDto updateEmpresa(Long empresaId, EmpresaDto empresaDto);
    String deleteEmpresa(Long empresaId);
    EmpresaDto getEmpresa(Long empresaId);
    List<EmpresaDto> getEmpresas();
}
