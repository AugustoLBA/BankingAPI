package com.BankingAPI.controllers;

import com.BankingAPI.dto.AnalistaCreateDTO;
import com.BankingAPI.dto.AnalistaResponseDTO;
import com.BankingAPI.models.Analista;
import com.BankingAPI.service.AnalistaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

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
}
