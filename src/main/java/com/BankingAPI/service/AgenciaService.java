package com.BankingAPI.service;

import com.BankingAPI.dto.AgenciaCreateDTO;
import com.BankingAPI.exceptions.UsernameUniqueViolationException;
import com.BankingAPI.models.Agencia;
import com.BankingAPI.repositories.AgenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

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

    public Agencia toAgencia(AgenciaCreateDTO createDTO) {
        Agencia agencia = new Agencia();
        BeanUtils.copyProperties(createDTO,agencia);
        return agencia;
    }
}
