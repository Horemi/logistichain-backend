package com.app.logistichain.services;

import com.app.logistichain.dtos.SucursalDto;
import java.util.List;

public interface SucursalService {
    SucursalDto createSucursal(SucursalDto sucursalDto);
    SucursalDto updateSucursal(Long sucursalId, SucursalDto sucursalDto);
    String deleteSucursal(Long sucursalId);
    SucursalDto getSucursal(Long sucursalId);
    List<SucursalDto> getSucursales();
}
