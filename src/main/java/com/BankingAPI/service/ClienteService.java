package com.BankingAPI.service;

import com.BankingAPI.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor // Injeção de depêndencia via lombok
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
}
