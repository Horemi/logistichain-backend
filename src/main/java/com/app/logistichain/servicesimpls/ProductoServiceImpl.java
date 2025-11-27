package com.app.logistichain.servicesimpls;

import com.app.logistichain.dtos.ProductoDto;
import com.app.logistichain.entities.Producto;
import com.app.logistichain.exceptions.NotFoundException;
import com.app.logistichain.mapperdtos.ProductoMapper;
import com.app.logistichain.repositories.ProductoRepository;
import com.app.logistichain.services.ProductoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    @Override
    public ProductoDto createProducto(ProductoDto productoDto) {
        Producto entity = ProductoMapper.mapProductoDtoToProducto(productoDto);
        Producto saved = productoRepository.save(entity);
        return ProductoMapper.mapProductoToProductoDto(saved);
    }

    @Override
    public ProductoDto updateProducto(Long productoId, ProductoDto productoDto) {
        Producto p = productoRepository.findById(productoId)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado con id " + productoId));

        p.setSku(productoDto.getSku());
        p.setGtin(productoDto.getGtin());
        p.setDescripcion(productoDto.getDescripcion());
        p.setMarca(productoDto.getMarca());
        p.setUnidadMedida(productoDto.getUnidadMedida());
        p.setCostoPromedio(productoDto.getCostoPromedio());
        p.setPrecioVentaBase(productoDto.getPrecioVentaBase());
        p.setPesoKg(productoDto.getPesoKg());
        p.setManejaLote(productoDto.getManejaLote());
        p.setManejaFechaVencimiento(productoDto.getManejaFechaVencimiento());
        p.setStockMinimo(productoDto.getStockMinimo());
        p.setStockMaximo(productoDto.getStockMaximo());

        Producto updated = productoRepository.save(p);
        return ProductoMapper.mapProductoToProductoDto(updated);
    }

    @Override
    public String deleteProducto(Long productoId) {
        Producto p = productoRepository.findById(productoId)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado con id " + productoId));
        productoRepository.delete(p);
        return "Producto eliminado";
    }

    @Override
    public ProductoDto getProducto(Long productoId) {
        Producto p = productoRepository.findById(productoId)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado con id " + productoId));
        return ProductoMapper.mapProductoToProductoDto(p);
    }

    @Override
    public List<ProductoDto> getProductos() {
        return productoRepository.findAll()
                .stream()
                .map(ProductoMapper::mapProductoToProductoDto)
                .collect(Collectors.toList());
    }
}
