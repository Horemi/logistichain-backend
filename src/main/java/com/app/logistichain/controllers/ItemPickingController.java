package com.app.logistichain.controllers;

import com.app.logistichain.dtos.ItemPickingDto;
import com.app.logistichain.exceptions.NotFoundException;
import com.app.logistichain.services.ItemPickingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items-picking")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ItemPickingController {

    private final ItemPickingService itemPickingService;

    @PostMapping
    public ResponseEntity<ItemPickingDto> create(@RequestBody ItemPickingDto dto) {
        ItemPickingDto saved = itemPickingService.createItemPicking(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<ItemPickingDto>> getAll() {
        return ResponseEntity.ok(itemPickingService.getItemsPicking());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemPickingDto> getOne(@PathVariable("id") Long id) {
        return ResponseEntity.ok(itemPickingService.getItemPicking(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemPickingDto> update(@PathVariable("id") Long id, @RequestBody ItemPickingDto dto) {
        return ResponseEntity.ok(itemPickingService.updateItemPicking(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(itemPickingService.deleteItemPicking(id));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> notFound(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
