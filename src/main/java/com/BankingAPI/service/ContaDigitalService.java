package com.BankingAPI.service;

import com.BankingAPI.dto.ContaDigitalCreateDTO;
import com.BankingAPI.dto.ContaDigitalResponseDTO;
import com.BankingAPI.exceptions.EntityNotFoundException;
import com.BankingAPI.exceptions.UsernameUniqueViolationException;
import com.BankingAPI.models.ContaDigital;
import com.BankingAPI.repositories.ContaDigitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor // Injeção de depêndencia via lombok
@Service
public class ContaDigitalService {

    private final ContaDigitalRepository contaDigitalRepository;

    private final AgenciaService agenciaService;

    private final ClienteService clienteService;


    @Transactional
    public ContaDigital salvar(ContaDigital contaDigital){
        try {
            return contaDigitalRepository.save(contaDigital);
        }catch (DataIntegrityViolationException e){
            throw new UsernameUniqueViolationException(String.format("Conta com NUMERO: {%s} já cadastrado.", contaDigital.getNumero()));
        }
    }

    @Transactional(readOnly = true)
    public ContaDigital buscarPorId(Long id){
        return contaDigitalRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException(String.format("Id {%s} não encontrado !", id)));
    }
    public ContaDigital toContaDigital(ContaDigitalCreateDTO createDTO){
        ContaDigital contaDigital = new ContaDigital();
        BeanUtils.copyProperties(createDTO,contaDigital);
        contaDigital.setAgencia(agenciaService.buscarPorId(createDTO.getAgenciaId()));
        contaDigital.setCliente(clienteService.buscarPorId(createDTO.getClienteId()));
        return contaDigital;
    }

    public ContaDigitalResponseDTO toDto(ContaDigital contaDigital){
        ContaDigitalResponseDTO responseDTO = new ContaDigitalResponseDTO();
        BeanUtils.copyProperties(contaDigital,responseDTO);
        responseDTO.setAgenciaId(contaDigital.getAgencia().getId());
        responseDTO.setClienteId(contaDigital.getCliente().getId());
        return responseDTO;
    }

    public List<ContaDigitalResponseDTO> toListDto(List<ContaDigital> contaDigitals){
        return contaDigitals.stream().map(responseDto -> toDto(responseDto)).collect(Collectors.toList());
    }
}
