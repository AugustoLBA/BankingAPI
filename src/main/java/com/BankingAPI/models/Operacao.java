package com.BankingAPI.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "operacoes")
public class Operacao implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "dataRealizada")
    private LocalDateTime dataRealizada;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 13)
    private TipoOperacao tipo;
    @Column(name = "valor", nullable = false)
    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "id_conta_digital")
    private ContaDigital contaDigital;


    public enum TipoOperacao{
        DEPOSITO,SAQUE,TRANSFERENCIA,PAGAMENTO;
    }
}
