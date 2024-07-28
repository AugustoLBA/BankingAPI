package com.BankingAPI.service;

import com.BankingAPI.dto.AnalistaCreateDTO;
import com.BankingAPI.dto.AnalistaResponseDTO;
import com.BankingAPI.exceptions.EntityNotFoundException;
import com.BankingAPI.exceptions.UsernameUniqueViolationException;
import com.BankingAPI.models.Analista;
import com.BankingAPI.repositories.AnalistaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor // Injeção de depêndencia via lombok
@Service
public class AnalistaService {

    private final AnalistaRepository analistaRepository;

    private final UsuarioService usuarioService;

    private final AgenciaService agenciaService;

    public Analista salvar(Analista analista){
        try {
            return analistaRepository.save(analista);
        }catch (DataIntegrityViolationException ex){
            throw new UsernameUniqueViolationException(String.format("Analista com CPF: {%s} já cadastrado.", analista.getCpf()));
        }
    }
    @Transactional(readOnly = true)
    public Analista buscarPorId(Long id){
        return analistaRepository.findById(id).orElseThrow(()
        -> new EntityNotFoundException(String.format("Id {%s} não encontrado !", id)));
    }

    @Transactional(readOnly = true)
    public List<Analista> buscarTodos(){
        return analistaRepository.findAll();
    }

    public Analista toAnalista(AnalistaCreateDTO createDTO){
        Analista analista = new Analista();
        BeanUtils.copyProperties(createDTO, analista);
        analista.setAgencia(agenciaService.buscarPorId(createDTO.getAgenciaId()));
        analista.setUsuario(usuarioService.buscarPorId(createDTO.getUsuarioId()));
        return analista;
    }

    public AnalistaResponseDTO toDto(Analista analista){
        AnalistaResponseDTO responseDTO = new AnalistaResponseDTO();
        BeanUtils.copyProperties(analista, responseDTO);
        responseDTO.setAgenciaId(analista.getAgencia().getId());
        responseDTO.setUsuarioId(analista.getUsuario().getId());
        return responseDTO;
    }

    public List<AnalistaResponseDTO> toListDto(List<Analista> analistas){
        return analistas.stream().map(responseDto -> toDto(responseDto)).collect(Collectors.toList());
    }
}
