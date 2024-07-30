package com.BankingAPI.service;

import com.BankingAPI.models.Transferencia;
import com.BankingAPI.repositories.TransferenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor // Injeção de dependencia via lombok
@Service
public class TransferenciaService {

    private final TransferenciaRepository transferenciaRepository;

    public Transferencia salvar(Transferencia transferencia){
        return transferenciaRepository.save(transferencia);
    }
}
