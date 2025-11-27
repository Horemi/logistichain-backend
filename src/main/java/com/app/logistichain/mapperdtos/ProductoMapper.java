package com.app.logistichain.mapperdtos;

import com.app.logistichain.dtos.ProductoDto;
import com.app.logistichain.entities.Producto;

public class ProductoMapper {

    public static ProductoDto mapProductoToProductoDto(Producto e) {
        if (e == null) return null;
        ProductoDto dto = new ProductoDto();
        dto.setId(e.getId());
        dto.setSku(e.getSku());
        dto.setGtin(e.getGtin());
        dto.setDescripcion(e.getDescripcion());
        dto.setMarca(e.getMarca());
        dto.setUnidadMedida(e.getUnidadMedida());
        dto.setCostoPromedio(e.getCostoPromedio());
        dto.setPrecioVentaBase(e.getPrecioVentaBase());
        dto.setPesoKg(e.getPesoKg());
        dto.setManejaLote(e.getManejaLote());
        dto.setManejaFechaVencimiento(e.getManejaFechaVencimiento());
        dto.setStockMinimo(e.getStockMinimo());
        dto.setStockMaximo(e.getStockMaximo());
        return dto;
    }

    public static Producto mapProductoDtoToProducto(ProductoDto dto) {
        if (dto == null) return null;
        Producto e = new Producto();
        e.setId(dto.getId());
        e.setSku(dto.getSku());
        e.setGtin(dto.getGtin());
        e.setDescripcion(dto.getDescripcion());
        e.setMarca(dto.getMarca());
        e.setUnidadMedida(dto.getUnidadMedida());
        e.setCostoPromedio(dto.getCostoPromedio());
        e.setPrecioVentaBase(dto.getPrecioVentaBase());
        e.setPesoKg(dto.getPesoKg());
        e.setManejaLote(dto.getManejaLote());
        e.setManejaFechaVencimiento(dto.getManejaFechaVencimiento());
        e.setStockMinimo(dto.getStockMinimo());
        e.setStockMaximo(dto.getStockMaximo());
        return e;
    }
}
