package com.app.logistichain.controllers;

import com.app.logistichain.dtos.RecepcionDto;
import com.app.logistichain.exceptions.NotFoundException;
import com.app.logistichain.services.RecepcionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recepciones")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class RecepcionController {

    private final RecepcionService recepcionService;

    @PostMapping
    public ResponseEntity<RecepcionDto> create(@RequestBody RecepcionDto dto) {
        RecepcionDto saved = recepcionService.createRecepcion(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<RecepcionDto>> getAll() {
        return ResponseEntity.ok(recepcionService.getRecepciones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecepcionDto> getOne(@PathVariable("id") Long id) {
        return ResponseEntity.ok(recepcionService.getRecepcion(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecepcionDto> update(@PathVariable("id") Long id, @RequestBody RecepcionDto dto) {
        return ResponseEntity.ok(recepcionService.updateRecepcion(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(recepcionService.deleteRecepcion(id));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> notFound(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
