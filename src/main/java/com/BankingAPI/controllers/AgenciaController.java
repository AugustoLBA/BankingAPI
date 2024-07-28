package com.BankingAPI.controllers;

import com.BankingAPI.dto.AgenciaCreateDTO;
import com.BankingAPI.dto.AgenciaResponseDTO;
import com.BankingAPI.models.Agencia;
import com.BankingAPI.service.AgenciaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor // Injeção de depêndencia via lombok
@RestController
@RequestMapping("api/v1/agencias")
public class AgenciaController {

    private final AgenciaService agenciaService;

    @PostMapping
    public ResponseEntity<AgenciaResponseDTO> save(@Valid @RequestBody AgenciaCreateDTO createDTO){
        Agencia agencia = agenciaService.salvar(agenciaService.toAgencia(createDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(agenciaService.toDto(agencia));
    }

    @GetMapping
    public ResponseEntity<List<AgenciaResponseDTO>> getAll(){
        List<AgenciaResponseDTO> responseDTOS = agenciaService.toListDto(agenciaService.buscarTodos());
        return ResponseEntity.status(HttpStatus.OK).body(responseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgenciaResponseDTO> findById(@PathVariable Long id){
        AgenciaResponseDTO responseDTO = agenciaService.toDto(agenciaService.buscarPorId(id));
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        agenciaService.deletarPorId(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
