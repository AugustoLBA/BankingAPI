package com.BankingAPI.service;

import com.BankingAPI.dto.UsuarioCreateDTO;
import com.BankingAPI.dto.UsuarioResponseDTO;
import com.BankingAPI.exceptions.EntityNotFoundException;
import com.BankingAPI.models.Usuario;
import com.BankingAPI.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor // Injeção de depêndencia via lombok
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario salvar(Usuario usuario){
       return usuarioRepository.save(usuario);
    }
    @Transactional(readOnly = true)
    public Usuario buscarPorId(Long id){
       return usuarioRepository.findById(id).orElseThrow(
               ()-> new EntityNotFoundException(String.format("Id {%s} não encontrado !", id)));

    }


    public Usuario toUsuario(UsuarioCreateDTO createDTO) {
        Usuario user = new Usuario();
        BeanUtils.copyProperties(createDTO, user);
        user.setRole(Usuario.Role.valueOf(createDTO.getRole()));
        return user;
    }

    public UsuarioResponseDTO toDto(Usuario user) {
        UsuarioResponseDTO responseDTO = new UsuarioResponseDTO();
        BeanUtils.copyProperties(user, responseDTO);
        responseDTO.setRole(user.getRole().name().substring("ROLE_".length()));
        return responseDTO;
    }
}
