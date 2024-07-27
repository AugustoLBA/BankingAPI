package com.BankingAPI.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contasDigitais")
public class ContaDigital implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "numero", nullable = false)
    private int numero;

    @Column(name = "saldo")
    private BigDecimal saldo;

    @Column(name = "senha", nullable = false, length = 200)
    private String senha;

    @ManyToOne
    @JoinColumn(name = "agencia_id")
    private Agencia agencia;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "contaDigital")
    private Set<Operacao> operacoes = new HashSet<>();
}
