package com.BankingAPI.controllers;

import com.BankingAPI.service.AgenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequiredArgsConstructor // Injeção de depêndencia via lombok
@RestController
@RequestMapping("api/v1/agencias")
public class AgenciaController {

    private final AgenciaService agenciaService;
}
