package com.app.logistichain.controllers;

import com.app.logistichain.dtos.PickingDto;
import com.app.logistichain.exceptions.NotFoundException;
import com.app.logistichain.services.PickingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pickings")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PickingController {

    private final PickingService pickingService;

    @PostMapping
    public ResponseEntity<PickingDto> create(@RequestBody PickingDto dto) {
        PickingDto saved = pickingService.createPicking(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<PickingDto>> getAll() {
        return ResponseEntity.ok(pickingService.getPickings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PickingDto> getOne(@PathVariable("id") Long id) {
        return ResponseEntity.ok(pickingService.getPicking(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PickingDto> update(@PathVariable("id") Long id, @RequestBody PickingDto dto) {
        return ResponseEntity.ok(pickingService.updatePicking(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(pickingService.deletePicking(id));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> notFound(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
