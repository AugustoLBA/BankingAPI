package com.BankingAPI.service;

import com.BankingAPI.dto.OperacaoResponseDTO;
import com.BankingAPI.models.Operacao;
import com.BankingAPI.repositories.OperacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor // Injeção de depêndencia via lombok
@Service
public class OperacaoService {

    private final OperacaoRepository operacaoRepository;
    @Transactional
    public Operacao salvar(Operacao operacao){
       return operacaoRepository.save(operacao);
    }
    public OperacaoResponseDTO toDto(Operacao operacao){
        OperacaoResponseDTO responseDTO = new OperacaoResponseDTO();
        BeanUtils.copyProperties(operacao,responseDTO);
        responseDTO.setContaDigitalId(operacao.getContaDigital().getId());
        return responseDTO;
    }

    public List<OperacaoResponseDTO> toListDto(List<Operacao> operacaoList){
        return operacaoList.stream().map(responseDto -> toDto(responseDto)).collect(Collectors.toList());
    }
}
