package com.BankingAPI.controllers;

import com.BankingAPI.dto.OperacaoResponseDTO;
import com.BankingAPI.models.Operacao;
import com.BankingAPI.service.OperacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor // Injeção de depêndencia via lombok
@RestController
@RequestMapping("api/v1/operacoes")
public class OperacaoController {

    private final OperacaoService operacaoService;

    @GetMapping("/{id}")
    public ResponseEntity<OperacaoResponseDTO> findById(@PathVariable Long id){
        Operacao operacao = operacaoService.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(operacaoService.toDto(operacao));
    }

    @GetMapping
    public ResponseEntity<List<OperacaoResponseDTO>> findAll(){
        List<Operacao> operacaos = operacaoService.buscarTodos();
        return ResponseEntity.status(HttpStatus.OK).body(operacaoService.toListDto(operacaos));
    }
}
