package com.BankingAPI.controllers;

import com.BankingAPI.service.ContaDigitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequiredArgsConstructor
@RestController
@RequestMapping("api/vi/contasDigitais")
public class ContaDigitalController {

    private final ContaDigitalService contaDigitalService;
}
