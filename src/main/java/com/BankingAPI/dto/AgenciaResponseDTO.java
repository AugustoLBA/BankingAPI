package com.BankingAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AgenciaResponseDTO {

    private Long id;

    private String nome;

    private int numero;

    private String telefone;
}
