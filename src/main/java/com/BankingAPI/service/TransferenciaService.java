package com.BankingAPI.service;

import com.BankingAPI.repositories.TransferenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor // Injeção de dependencia via lombok
@Service
public class TransferenciaService {

    private final TransferenciaRepository transferenciaRepository;
}
