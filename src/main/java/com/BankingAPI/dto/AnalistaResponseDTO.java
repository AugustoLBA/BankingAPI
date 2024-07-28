package com.BankingAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnalistaResponseDTO {

    private Long id;

    private String nome;

    private String cpf;

    private String telefone;

    private Long usuarioId;

    private Long agenciaId;
}
