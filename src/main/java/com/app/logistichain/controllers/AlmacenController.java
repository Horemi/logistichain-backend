package com.app.logistichain.controllers;

import com.app.logistichain.dtos.AlmacenDto;
import com.app.logistichain.exceptions.NotFoundException;
import com.app.logistichain.services.AlmacenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/almacenes")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AlmacenController {

    private final AlmacenService almacenService;

    @PostMapping
    public ResponseEntity<AlmacenDto> createAlmacen(@RequestBody AlmacenDto almacenDto) {
        AlmacenDto saved = almacenService.createAlmacen(almacenDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<AlmacenDto>> getAlmacenes() {
        List<AlmacenDto> list = almacenService.getAlmacenes();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlmacenDto> getAlmacen(@PathVariable("id") Long id) {
        AlmacenDto dto = almacenService.getAlmacen(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlmacenDto> updateAlmacen(
            @PathVariable("id") Long id,
            @RequestBody AlmacenDto almacenDto
    ) {
        AlmacenDto updated = almacenService.updateAlmacen(id, almacenDto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAlmacen(@PathVariable("id") Long id) {
        String msg = almacenService.deleteAlmacen(id);
        return ResponseEntity.ok(msg);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFound(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
