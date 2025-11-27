package com.app.logistichain.controllers;

import com.app.logistichain.dtos.LoteDto;
import com.app.logistichain.exceptions.NotFoundException;
import com.app.logistichain.services.LoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lotes")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class LoteController {

    private final LoteService loteService;

    @PostMapping
    public ResponseEntity<LoteDto> createLote(@RequestBody LoteDto loteDto) {
        LoteDto saved = loteService.createLote(loteDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<LoteDto>> getLotes() {
        return ResponseEntity.ok(loteService.getLotes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoteDto> getLote(@PathVariable("id") Long id) {
        return ResponseEntity.ok(loteService.getLote(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoteDto> updateLote(@PathVariable("id") Long id, @RequestBody LoteDto loteDto) {
        LoteDto updated = loteService.updateLote(id, loteDto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLote(@PathVariable("id") Long id) {
        String msg = loteService.deleteLote(id);
        return ResponseEntity.ok(msg);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFound(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
