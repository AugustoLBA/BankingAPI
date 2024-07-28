package com.BankingAPI.dto;

import com.BankingAPI.models.Operacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OperacaoTransferenciaDTO extends OperacaoResponseDTO{

    private Long contaOrigemId;
    private Long contaDestinoId;
}
