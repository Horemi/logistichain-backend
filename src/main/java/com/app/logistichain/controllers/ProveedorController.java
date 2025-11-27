package com.app.logistichain.controllers;

import com.app.logistichain.dtos.ProveedorDto;
import com.app.logistichain.exceptions.NotFoundException;
import com.app.logistichain.services.ProveedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ProveedorController {

    private final ProveedorService proveedorService;

    @PostMapping
    public ResponseEntity<ProveedorDto> createProveedor(@RequestBody ProveedorDto proveedorDto) {
        ProveedorDto saved = proveedorService.createProveedor(proveedorDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<ProveedorDto>> getProveedores() {
        return ResponseEntity.ok(proveedorService.getProveedores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProveedorDto> getProveedor(@PathVariable("id") Long id) {
        return ResponseEntity.ok(proveedorService.getProveedor(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProveedorDto> updateProveedor(@PathVariable("id") Long id, @RequestBody ProveedorDto proveedorDto) {
        ProveedorDto updated = proveedorService.updateProveedor(id, proveedorDto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProveedor(@PathVariable("id") Long id) {
        String msg = proveedorService.deleteProveedor(id);
        return ResponseEntity.ok(msg);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFound(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
