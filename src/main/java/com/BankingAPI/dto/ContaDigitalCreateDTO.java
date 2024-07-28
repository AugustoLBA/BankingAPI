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
public class ContaDigitalCreateDTO {

    @NotNull(message = "O numero da conta não pode se nulo !")
    private int numero;

    @NotNull(message = "O saldo da conta não pode ser nulo !")
    private BigDecimal saldo;

    @NotBlank(message = "A senha da conta não pode ser nula !")
    private String senha;

    @NotNull(message = "O ID da AGENCIA da conta não pode ser nulo !")
    private Long agenciaId;

    @NotNull(message = "O ID do CLIENTE da conta não pode ser nulo !")
    private Long clienteId;
}
