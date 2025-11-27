package com.app.logistichain.controllers;

import com.app.logistichain.dtos.ClienteDto;
import com.app.logistichain.exceptions.NotFoundException;
import com.app.logistichain.services.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteDto> create(@RequestBody ClienteDto dto) {
        ClienteDto saved = clienteService.createCliente(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDto>> getAll() {
        return ResponseEntity.ok(clienteService.getClientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> getOne(@PathVariable("id") Long id) {
        return ResponseEntity.ok(clienteService.getCliente(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> update(@PathVariable("id") Long id, @RequestBody ClienteDto dto) {
        return ResponseEntity.ok(clienteService.updateCliente(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(clienteService.deleteCliente(id));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> notFound(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
