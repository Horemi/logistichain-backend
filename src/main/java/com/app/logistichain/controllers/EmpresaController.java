package com.app.logistichain.controllers;

import com.app.logistichain.dtos.EmpresaDto;
import com.app.logistichain.exceptions.NotFoundException;
import com.app.logistichain.services.EmpresaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empresas")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class EmpresaController {

    private final EmpresaService empresaService;

    /**
     * 🔹 Crear una nueva empresa
     */
    @PostMapping
    public ResponseEntity<EmpresaDto> createEmpresa(@RequestBody EmpresaDto empresaDto) {
        EmpresaDto saved = empresaService.createEmpresa(empresaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    /**
     * 🔹 Obtener todas las empresas
     */
    @GetMapping
    public ResponseEntity<List<EmpresaDto>> getEmpresas() {
        List<EmpresaDto> list = empresaService.getEmpresas();
        return ResponseEntity.ok(list);
    }

    /**
     * 🔹 Obtener una empresa por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<EmpresaDto> getEmpresa(@PathVariable("id") Long id) {
        EmpresaDto dto = empresaService.getEmpresa(id);
        return ResponseEntity.ok(dto);
    }

    /**
     * 🔹 Actualizar empresa
     */
    @PutMapping("/{id}")
    public ResponseEntity<EmpresaDto> updateEmpresa(
            @PathVariable("id") Long id,
            @RequestBody EmpresaDto empresaDto
    ) {
        EmpresaDto updated = empresaService.updateEmpresa(id, empresaDto);
        return ResponseEntity.ok(updated);
    }

    /**
     * 🔹 Eliminar empresa
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmpresa(@PathVariable("id") Long id) {
        String msg = empresaService.deleteEmpresa(id);
        return ResponseEntity.ok(msg);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFound(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
