package com.BankingAPI.dto;

import com.BankingAPI.models.Operacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OperacaoResponseDTO {

    private Long id;

    private LocalDateTime dataRealizada;
    
    private Operacao.TipoOperacao tipo;

    private BigDecimal valor;

    private Long contaDigitalId;
}
