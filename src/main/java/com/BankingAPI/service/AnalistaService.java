package com.BankingAPI.service;

import com.BankingAPI.repositories.AnalistaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor // Injeção de depêndencia via lombok
@Service
public class AnalistaService {

    private final AnalistaRepository analistaRepository;
}
