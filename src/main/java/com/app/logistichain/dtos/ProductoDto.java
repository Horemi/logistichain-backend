package com.app.logistichain.dtos;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDto {
    private Long id;
    private String sku;
    private String gtin;
    private String descripcion;
    private String marca;
    private String unidadMedida;

    private BigDecimal costoPromedio;
    private BigDecimal precioVentaBase;
    private Double pesoKg;

    private Boolean manejaLote;
    private Boolean manejaFechaVencimiento;

    private Integer stockMinimo;
    private Integer stockMaximo;
}
