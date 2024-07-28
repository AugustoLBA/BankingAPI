package com.BankingAPI.controllers;

import com.BankingAPI.dto.ContaDigitalCreateDTO;
import com.BankingAPI.dto.ContaDigitalResponseDTO;
import com.BankingAPI.models.ContaDigital;
import com.BankingAPI.service.ContaDigitalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/contasDigitais")
public class ContaDigitalController {

    private final ContaDigitalService contaDigitalService;

    @PostMapping
    public ResponseEntity<ContaDigitalResponseDTO> save(@Valid @RequestBody ContaDigitalCreateDTO createDTO){
        ContaDigital contaDigital = contaDigitalService.salvar(contaDigitalService.toContaDigital(createDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(contaDigitalService.toDto(contaDigital));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaDigitalResponseDTO> findById(@PathVariable Long id){
        ContaDigital contaDigital = contaDigitalService.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(contaDigitalService.toDto(contaDigital));
    }

    @GetMapping
    public ResponseEntity<List<ContaDigitalResponseDTO>> findAll(){
        List<ContaDigital> contaDigitals = contaDigitalService.buscarTodos();
        return ResponseEntity.status(HttpStatus.OK).body(contaDigitalService.toListDto(contaDigitals));
    }
}
