package com.BankingAPI.service;

import com.BankingAPI.dto.UsuarioCreateDTO;
import com.BankingAPI.dto.UsuarioResponseDTO;
import com.BankingAPI.dto.UsuarioSenhaDTO;
import com.BankingAPI.exceptions.EntityNotFoundException;
import com.BankingAPI.exceptions.PaswordInvalidException;
import com.BankingAPI.models.Usuario;
import com.BankingAPI.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    @Transactional(readOnly = true)
    public List<Usuario> buscarTodos(){
        return usuarioRepository.findAll();
    }
    @Transactional
    public Usuario atualizarSenha(Long id,UsuarioSenhaDTO senhaDTO){
        if(!senhaDTO.getNovaSenha().equals(senhaDTO.getConfirmaSenha())){
            throw new PaswordInvalidException("Nova senha não condiz com confirma senha!");
        }
        Usuario user = buscarPorId(id);
        if(!user.getSenha().equals(senhaDTO.getSenhaAtual())){
            throw new PaswordInvalidException("Senha invalida!");
        }
        user.setSenha(senhaDTO.getNovaSenha());
        return user;
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

    public List<UsuarioResponseDTO> toListDto(List<Usuario> usuarios){
        return usuarios.stream().map(user -> toDto(user)).collect(Collectors.toList());
    }
}
