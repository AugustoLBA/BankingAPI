package com.BankingAPI.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContaDigitalResponseDTO {

    private Long id;
    private int numero;

    private BigDecimal saldo;

    private String senha;

    private Long agenciaId;

    private Long clienteId;
}
