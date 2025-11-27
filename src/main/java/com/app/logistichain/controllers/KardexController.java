package com.app.logistichain.controllers;

import com.app.logistichain.dtos.KardexDto;
import com.app.logistichain.exceptions.NotFoundException;
import com.app.logistichain.services.KardexService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kardex")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class KardexController {

    private final KardexService kardexService;

    @PostMapping
    public ResponseEntity<KardexDto> create(@RequestBody KardexDto dto) {
        KardexDto saved = kardexService.createKardex(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<KardexDto>> getAll() {
        return ResponseEntity.ok(kardexService.getKardexes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<KardexDto> getOne(@PathVariable("id") Long id) {
        return ResponseEntity.ok(kardexService.getKardex(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<KardexDto> update(@PathVariable("id") Long id, @RequestBody KardexDto dto) {
        return ResponseEntity.ok(kardexService.updateKardex(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(kardexService.deleteKardex(id));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> notFound(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
