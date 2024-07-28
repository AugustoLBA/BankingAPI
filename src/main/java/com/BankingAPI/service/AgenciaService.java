package com.BankingAPI.service;

import com.BankingAPI.repositories.AgenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor // Injeção de depêndencia via lombok
@Service
public class AgenciaService {

    private final AgenciaRepository agenciaRepository;
}
