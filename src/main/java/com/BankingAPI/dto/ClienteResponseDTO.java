package com.BankingAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponseDTO {

    private Long id;

    private String nome;

    private String cpf;

    private String telefone;

    private Long usuarioId;
}
