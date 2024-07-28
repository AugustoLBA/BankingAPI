package com.BankingAPI.controllers;

import com.BankingAPI.dto.ClienteCreateDTO;
import com.BankingAPI.dto.ClienteResponseDTO;
import com.BankingAPI.models.Cliente;
import com.BankingAPI.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor // Injeção de depêndencia via lombok
@RestController
@RequestMapping("api/v1/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> save(@Valid @RequestBody ClienteCreateDTO createDTO){
        Cliente cliente = clienteService.salvar(clienteService.toCliente(createDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.toDto(cliente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> findById(@PathVariable Long id){
        Cliente cliente = clienteService.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.toDto(cliente));
    }
}
