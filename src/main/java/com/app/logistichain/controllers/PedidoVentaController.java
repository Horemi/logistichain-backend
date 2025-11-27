package com.app.logistichain.controllers;

import com.app.logistichain.dtos.PedidoVentaDto;
import com.app.logistichain.exceptions.NotFoundException;
import com.app.logistichain.services.PedidoVentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos-venta")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PedidoVentaController {

    private final PedidoVentaService pedidoVentaService;

    @PostMapping
    public ResponseEntity<PedidoVentaDto> create(@RequestBody PedidoVentaDto dto) {
        PedidoVentaDto saved = pedidoVentaService.createPedidoVenta(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<PedidoVentaDto>> getAll() {
        return ResponseEntity.ok(pedidoVentaService.getPedidosVenta());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoVentaDto> getOne(@PathVariable("id") Long id) {
        return ResponseEntity.ok(pedidoVentaService.getPedidoVenta(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoVentaDto> update(@PathVariable("id") Long id, @RequestBody PedidoVentaDto dto) {
        return ResponseEntity.ok(pedidoVentaService.updatePedidoVenta(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(pedidoVentaService.deletePedidoVenta(id));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> notFound(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
