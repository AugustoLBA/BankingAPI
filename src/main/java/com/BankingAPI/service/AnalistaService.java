package com.BankingAPI.service;

import com.BankingAPI.dto.AnalistaCreateDTO;
import com.BankingAPI.dto.AnalistaResponseDTO;
import com.BankingAPI.models.Analista;
import com.BankingAPI.repositories.AnalistaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor // Injeção de depêndencia via lombok
@Service
public class AnalistaService {

    private final AnalistaRepository analistaRepository;


    public Analista toAnalista(AnalistaCreateDTO createDTO){
        Analista analista = new Analista();
        BeanUtils.copyProperties(createDTO, analista);
        return analista;
    }

    public AnalistaResponseDTO toDto(Analista analista){
        AnalistaResponseDTO responseDTO = new AnalistaResponseDTO();
        BeanUtils.copyProperties(analista, responseDTO);
        return responseDTO;
    }

    public List<AnalistaResponseDTO> toListDto(List<Analista> analistas){
        return analistas.stream().map(responseDto -> toDto(responseDto)).collect(Collectors.toList());
    }
}
