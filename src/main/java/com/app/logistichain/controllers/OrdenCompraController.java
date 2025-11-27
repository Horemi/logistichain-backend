package com.app.logistichain.controllers;

import com.app.logistichain.dtos.OrdenCompraDto;
import com.app.logistichain.exceptions.NotFoundException;
import com.app.logistichain.services.OrdenCompraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordenes-compra")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class OrdenCompraController {

    private final OrdenCompraService ordenCompraService;

    @PostMapping
    public ResponseEntity<OrdenCompraDto> create(@RequestBody OrdenCompraDto dto) {
        OrdenCompraDto saved = ordenCompraService.createOrdenCompra(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<OrdenCompraDto>> getAll() {
        return ResponseEntity.ok(ordenCompraService.getOrdenesCompra());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenCompraDto> getOne(@PathVariable("id") Long id) {
        return ResponseEntity.ok(ordenCompraService.getOrdenCompra(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdenCompraDto> update(@PathVariable("id") Long id, @RequestBody OrdenCompraDto dto) {
        return ResponseEntity.ok(ordenCompraService.updateOrdenCompra(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(ordenCompraService.deleteOrdenCompra(id));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> notFound(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
