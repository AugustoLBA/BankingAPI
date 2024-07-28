package com.BankingAPI.service;

import com.BankingAPI.repositories.ContaDigitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor // Injeção de depêndencia via lombok
@Service
public class ContaDigitalService {

    private final ContaDigitalRepository contaDigitalRepository;
}
