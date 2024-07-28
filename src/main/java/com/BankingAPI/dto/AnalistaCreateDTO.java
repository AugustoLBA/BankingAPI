package com.BankingAPI.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnalistaCreateDTO {

    @NotBlank(message = "O nome do analista não pode ser nulo !")
    private String nome;

    @NotBlank(message = "O nome do CPF não pode ser nulo !")
    private String cpf;

    @NotBlank(message = "O nome do telefone não pode ser nulo !")
    private String telefone;

    @NotNull(message = "O id do USUARIO não pode ser nulo!")
    private Long usuarioId;

    @NotNull(message = "O id da AGENCIA não pode ser nulo!")
    private Long agenciaId;
}
