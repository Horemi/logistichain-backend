package com.app.logistichain.controllers;

import com.app.logistichain.dtos.RolDto;
import com.app.logistichain.exceptions.NotFoundException;
import com.app.logistichain.services.RolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class RolController {

    private final RolService rolService;

    @PostMapping
    public ResponseEntity<RolDto> create(@RequestBody RolDto dto) {
        RolDto saved = rolService.createRol(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<RolDto>> getAll() {
        return ResponseEntity.ok(rolService.getRoles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolDto> getOne(@PathVariable("id") Long id) {
        return ResponseEntity.ok(rolService.getRol(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolDto> update(@PathVariable("id") Long id, @RequestBody RolDto dto) {
        return ResponseEntity.ok(rolService.updateRol(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(rolService.deleteRol(id));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> notFound(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
