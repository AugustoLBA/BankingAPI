package com.BankingAPI.service;

import com.BankingAPI.dto.ContaDigitalCreateDTO;
import com.BankingAPI.dto.ContaDigitalResponseDTO;
import com.BankingAPI.models.ContaDigital;
import com.BankingAPI.repositories.ContaDigitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor // Injeção de depêndencia via lombok
@Service
public class ContaDigitalService {

    private final ContaDigitalRepository contaDigitalRepository;

    private final AgenciaService agenciaService;

    private final ClienteService clienteService;


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
