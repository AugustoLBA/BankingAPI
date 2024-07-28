package com.BankingAPI.controllers;

import com.BankingAPI.dto.AgenciaCreateDTO;
import com.BankingAPI.dto.AgenciaResponseDTO;
import com.BankingAPI.models.Agencia;
import com.BankingAPI.service.AgenciaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequiredArgsConstructor // Injeção de depêndencia via lombok
@RestController
@RequestMapping("api/v1/agencias")
public class AgenciaController {

    private final AgenciaService agenciaService;

    @PostMapping
    public ResponseEntity<AgenciaResponseDTO> save(@Valid @RequestBody AgenciaCreateDTO createDTO){
        Agencia agencia = agenciaService.salvar(agenciaService.toAgencia);
        return ResponseEntity.status(HttpStatus.CREATED).body(agenciaService.toDto(agencia));
    }
}
