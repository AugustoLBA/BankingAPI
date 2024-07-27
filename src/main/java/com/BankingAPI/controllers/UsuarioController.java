package com.BankingAPI.controllers;

import com.BankingAPI.dto.UsuarioCreateDTO;
import com.BankingAPI.dto.UsuarioResponseDTO;
import com.BankingAPI.models.Usuario;
import com.BankingAPI.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor // Injecção de depêndencia via lombok
@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> save(@Valid  @RequestBody UsuarioCreateDTO createDTO){
        Usuario user = usuarioService.salvar(usuarioService.toUsuario(createDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.toDto(user));

    }
}
