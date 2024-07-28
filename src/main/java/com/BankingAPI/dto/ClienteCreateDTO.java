package com.BankingAPI.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteCreateDTO {

    @NotBlank(message = "O NOME do cliente não pode ser nulo !")
    private String nome;

    @NotBlank(message = "O CPF do cliente não pode ser nulo !")
    private String cpf;

    @NotBlank(message = "O TELEFONE do cliente não pode ser nulo !")
    private String telefone;

    @NotNull(message = "O ID DO USUARIO do cliente não pode ser nulo !")
    private Long usuarioId;
}
