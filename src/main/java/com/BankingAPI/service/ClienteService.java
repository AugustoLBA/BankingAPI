package com.BankingAPI.service;

import com.BankingAPI.dto.ClienteCreateDTO;
import com.BankingAPI.dto.ClienteResponseDTO;
import com.BankingAPI.exceptions.EntityNotFoundException;
import com.BankingAPI.exceptions.UsernameUniqueViolationException;
import com.BankingAPI.models.Cliente;
import com.BankingAPI.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor // Injeção de depêndencia via lombok
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    private final UsuarioService usuarioService;


    @Transactional
    public Cliente salvar(Cliente cliente){
        try {
            return clienteRepository.save(cliente);
        }catch (DataIntegrityViolationException e){
            throw new UsernameUniqueViolationException(String.format("Analista com CPF: {%s} já cadastrado.", cliente.getCpf()));
        }
    }

    @Transactional(readOnly = true)
    public Cliente buscarPorId(Long id){
        return clienteRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException(String.format("Id {%s} não encontrado !", id)));
    }
    public Cliente toCliente(ClienteCreateDTO createDTO){
        Cliente cliente = new Cliente();
        BeanUtils.copyProperties(createDTO,cliente);
        cliente.setUsuario(usuarioService.buscarPorId(createDTO.getUsuarioId()));
        return cliente;
    }

    public ClienteResponseDTO toDto(Cliente cliente){
        ClienteResponseDTO responseDTO = new ClienteResponseDTO();
        BeanUtils.copyProperties(cliente,responseDTO);
        responseDTO.setUsuarioId(cliente.getUsuario().getId());
        return responseDTO;
    }

    public List<ClienteResponseDTO> toListDto(List<Cliente> clienteList){
        return clienteList.stream().map(responseDto -> toDto(responseDto)).collect(Collectors.toList());
    }
}
