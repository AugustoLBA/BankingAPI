package com.BankingAPI.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "agencias")
public class Agencia implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nome", nullable = false,length = 100)
    private String nome;
    @Column(name = "dataCriacao", nullable = false)
    private LocalDateTime dataCriacao;
    @Column(name = "telefone", nullable = false,length = 11)
    private String telefone;
}
