package com.app.logistichain.controllers;

import com.app.logistichain.dtos.UbicacionDto;
import com.app.logistichain.exceptions.NotFoundException;
import com.app.logistichain.services.UbicacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ubicaciones")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UbicacionController {

    private final UbicacionService ubicacionService;

    @PostMapping
    public ResponseEntity<UbicacionDto> createUbicacion(@RequestBody UbicacionDto ubicacionDto) {
        UbicacionDto saved = ubicacionService.createUbicacion(ubicacionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<UbicacionDto>> getUbicaciones() {
        List<UbicacionDto> list = ubicacionService.getUbicaciones();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UbicacionDto> getUbicacion(@PathVariable("id") Long id) {
        UbicacionDto dto = ubicacionService.getUbicacion(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UbicacionDto> updateUbicacion(
            @PathVariable("id") Long id,
            @RequestBody UbicacionDto ubicacionDto
    ) {
        UbicacionDto updated = ubicacionService.updateUbicacion(id, ubicacionDto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUbicacion(@PathVariable("id") Long id) {
        String msg = ubicacionService.deleteUbicacion(id);
        return ResponseEntity.ok(msg);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFound(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
