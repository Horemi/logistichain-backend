package com.app.logistichain.controllers;

import com.app.logistichain.dtos.DetallePVDto;
import com.app.logistichain.exceptions.NotFoundException;
import com.app.logistichain.services.DetallePVService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalles-pv")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class DetallePVController {

    private final DetallePVService detallePVService;

    @PostMapping
    public ResponseEntity<DetallePVDto> create(@RequestBody DetallePVDto dto) {
        DetallePVDto saved = detallePVService.createDetallePV(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<DetallePVDto>> getAll() {
        return ResponseEntity.ok(detallePVService.getDetallesPV());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetallePVDto> getOne(@PathVariable("id") Long id) {
        return ResponseEntity.ok(detallePVService.getDetallePV(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetallePVDto> update(@PathVariable("id") Long id, @RequestBody DetallePVDto dto) {
        return ResponseEntity.ok(detallePVService.updateDetallePV(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(detallePVService.deleteDetallePV(id));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> notFound(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
