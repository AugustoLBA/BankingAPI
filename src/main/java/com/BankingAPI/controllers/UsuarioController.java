package com.BankingAPI.controllers;

import com.BankingAPI.dto.UsuarioCreateDTO;
import com.BankingAPI.dto.UsuarioResponseDTO;
import com.BankingAPI.dto.UsuarioSenhaDTO;
import com.BankingAPI.models.Usuario;
import com.BankingAPI.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> findById(@PathVariable Long id){
        UsuarioResponseDTO responseDTO = usuarioService.toDto(usuarioService.buscarPorId(id));
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> getAll(){
        List<UsuarioResponseDTO> responseDTOS = usuarioService.toListDto(usuarioService.buscarTodos());
        return ResponseEntity.status(HttpStatus.OK).body(responseDTOS);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePasword(@Valid @PathVariable Long id, UsuarioSenhaDTO senhaDTO){
            usuarioService.atualizarSenha(id,senhaDTO);
            return ResponseEntity.noContent().build();
    }
}
