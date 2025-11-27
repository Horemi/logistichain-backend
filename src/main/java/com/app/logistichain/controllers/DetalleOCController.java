package com.app.logistichain.controllers;

import com.app.logistichain.dtos.DetalleOCDto;
import com.app.logistichain.exceptions.NotFoundException;
import com.app.logistichain.services.DetalleOCService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalles-oc")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class DetalleOCController {

    private final DetalleOCService detalleOCService;

    @PostMapping
    public ResponseEntity<DetalleOCDto> create(@RequestBody DetalleOCDto dto) {
        DetalleOCDto saved = detalleOCService.createDetalleOC(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<DetalleOCDto>> getAll() {
        return ResponseEntity.ok(detalleOCService.getDetallesOC());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleOCDto> getOne(@PathVariable("id") Long id) {
        return ResponseEntity.ok(detalleOCService.getDetalleOC(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleOCDto> update(@PathVariable("id") Long id, @RequestBody DetalleOCDto dto) {
        return ResponseEntity.ok(detalleOCService.updateDetalleOC(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(detalleOCService.deleteDetalleOC(id));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> notFound(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
