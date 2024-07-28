package com.BankingAPI.controllers;

import com.BankingAPI.dto.AnalistaCreateDTO;
import com.BankingAPI.dto.AnalistaResponseDTO;
import com.BankingAPI.models.Analista;
import com.BankingAPI.service.AnalistaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor // Injeção de depêndencia via lombok
@RestController
@RequestMapping("api/v1/analistas")
public class AnalistaController {

    private final AnalistaService analistaService;

    @PostMapping
    public ResponseEntity<AnalistaResponseDTO> save(@Valid @RequestBody AnalistaCreateDTO createDTO){
        Analista analista = analistaService.salvar(analistaService.toAnalista(createDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(analistaService.toDto(analista));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnalistaResponseDTO> findById(@PathVariable Long id){
        Analista analista = analistaService.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(analistaService.toDto(analista));
    }

    @GetMapping
    public ResponseEntity<List<AnalistaResponseDTO>> findAll(){
        List<Analista> analistas = analistaService.buscarTodos();
        return ResponseEntity.status(HttpStatus.OK).body(analistaService.toListDto(analistas));
    }
}
