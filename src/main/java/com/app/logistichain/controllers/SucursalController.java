package com.app.logistichain.controllers;

import com.app.logistichain.dtos.SucursalDto;
import com.app.logistichain.exceptions.NotFoundException;
import com.app.logistichain.services.SucursalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sucursales")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class SucursalController {

    private final SucursalService sucursalService;

    @PostMapping
    public ResponseEntity<SucursalDto> createSucursal(@RequestBody SucursalDto sucursalDto) {
        SucursalDto saved = sucursalService.createSucursal(sucursalDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<SucursalDto>> getSucursales() {
        List<SucursalDto> list = sucursalService.getSucursales();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SucursalDto> getSucursal(@PathVariable("id") Long id) {
        SucursalDto dto = sucursalService.getSucursal(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SucursalDto> updateSucursal(
            @PathVariable("id") Long id,
            @RequestBody SucursalDto sucursalDto
    ) {
        SucursalDto updated = sucursalService.updateSucursal(id, sucursalDto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSucursal(@PathVariable("id") Long id) {
        String msg = sucursalService.deleteSucursal(id);
        return ResponseEntity.ok(msg);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFound(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
