package com.BankingAPI.controllers;

import com.BankingAPI.dto.ContaDigitalCreateDTO;
import com.BankingAPI.dto.ContaDigitalResponseDTO;
import com.BankingAPI.models.ContaDigital;
import com.BankingAPI.service.ContaDigitalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
