package com.BankingAPI.service;

import com.BankingAPI.dto.AgenciaCreateDTO;
import com.BankingAPI.dto.AgenciaResponseDTO;
import com.BankingAPI.exceptions.EntityNotFoundException;
import com.BankingAPI.exceptions.UsernameUniqueViolationException;
import com.BankingAPI.models.Agencia;
import com.BankingAPI.repositories.AgenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor // Injeção de depêndencia via lombok
@Service
public class AgenciaService {

    private final AgenciaRepository agenciaRepository;

    public Agencia salvar(Agencia agencia){
        try{
          return agenciaRepository.save(agencia);
        }catch (DataIntegrityViolationException ex){
            throw new UsernameUniqueViolationException(String.format("Nome de agencia {%s} já cadastrado.", agencia.getNome()));
        }
    }
    @Transactional(readOnly = true)
    public Agencia buscarPorId(Long id){
        return agenciaRepository.findById(id).orElseThrow(()
                        -> new EntityNotFoundException(String.format("Id {%s} não encontrado !", id)));
    }

    @Transactional(readOnly = true)
    public List<Agencia> buscarTodos(){
        return agenciaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public void deletarPorId(Long id){
        Agencia agencia = buscarPorId(id);
        agenciaRepository.delete(agencia);
    }
    public Agencia toAgencia(AgenciaCreateDTO createDTO) {
        Agencia agencia = new Agencia();
        BeanUtils.copyProperties(createDTO,agencia);
        return agencia;
    }

    public AgenciaResponseDTO toDto(Agencia agencia) {
        AgenciaResponseDTO responseDTO = new AgenciaResponseDTO();
        BeanUtils.copyProperties(agencia,responseDTO);
        return responseDTO;
    }

    public List<AgenciaResponseDTO> toListDto(List<Agencia> agencias){
        return agencias.stream().map(agenciaAux -> toDto(agenciaAux)).collect(Collectors.toList());
    }
}
