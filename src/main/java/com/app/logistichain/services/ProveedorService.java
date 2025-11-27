package com.app.logistichain.services;

import com.app.logistichain.dtos.ProveedorDto;
import java.util.List;

public interface ProveedorService {
    ProveedorDto createProveedor(ProveedorDto proveedorDto);
    ProveedorDto updateProveedor(Long proveedorId, ProveedorDto proveedorDto);
    String deleteProveedor(Long proveedorId);
    ProveedorDto getProveedor(Long proveedorId);
    List<ProveedorDto> getProveedores();
}
