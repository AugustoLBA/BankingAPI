package com.BankingAPI.models;


import jakarta.persistence.*;

import java.io.Serializable;
@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;
    @Column(name = "cpf", nullable = false, unique = true, length = 11)
    private String cpf;
    @Column(name = "telefone", nullable = false, unique = true, length = 11)
    private String telfone;
}
