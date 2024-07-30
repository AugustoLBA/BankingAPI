package com.BankingAPI.service;

import com.BankingAPI.dto.OperacaoResponseDTO;
import com.BankingAPI.dto.OperacaoTransferenciaDTO;
import com.BankingAPI.exceptions.EntityNotFoundException;
import com.BankingAPI.models.Operacao;
import com.BankingAPI.models.Transferencia;
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

    @Transactional(readOnly = true)
    public Operacao buscarPorId(Long id){
        return operacaoRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException(String.format("Id {%s} não encontrado !", id)));
    }

    @Transactional(readOnly = true)
    public List<Operacao> buscarTodos(){
        return operacaoRepository.findAll();
    }
    public OperacaoResponseDTO toDto(Operacao operacao){
        if(operacao.getTipo().equals(Operacao.TipoOperacao.TRANSFERENCIA)){

            Transferencia transferencia = (Transferencia) operacao;

            OperacaoTransferenciaDTO responseDTO = new OperacaoTransferenciaDTO();

            BeanUtils.copyProperties(transferencia,responseDTO);
            responseDTO.setContaDigitalId(transferencia.getContaDigital().getId());
            responseDTO.setContaOrigemId(transferencia.getContaDestino().getId());
            responseDTO.setContaDestinoId(transferencia.getContaDestino().getId());
            return responseDTO;
        }
        Operacao operacao1 = (Operacao) operacao;
            OperacaoResponseDTO responseDTO = new OperacaoResponseDTO();
            BeanUtils.copyProperties(operacao,responseDTO);
            responseDTO.setContaDigitalId(operacao1.getContaDigital().getId());
            return responseDTO;
    }

    public List<OperacaoResponseDTO> toListDto(List<Operacao> operacaoList){
        return operacaoList.stream().map(responseDto -> toDto(responseDto)).collect(Collectors.toList());
    }
}
