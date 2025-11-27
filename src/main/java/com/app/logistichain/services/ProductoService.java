package com.app.logistichain.services;

import com.app.logistichain.dtos.ProductoDto;
import java.util.List;

public interface ProductoService {
    ProductoDto createProducto(ProductoDto productoDto);
    ProductoDto updateProducto(Long productoId, ProductoDto productoDto);
    String deleteProducto(Long productoId);
    ProductoDto getProducto(Long productoId);
    List<ProductoDto> getProductos();
}
