package com.BankingAPI.service;

import com.BankingAPI.repositories.OperacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor // Injeção de depêndencia via lombok
@Service
public class OperacaoService {

    private final OperacaoRepository operacaoRepository;
}
