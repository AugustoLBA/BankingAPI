package com.BankingAPI.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "transferencias")
public class Transferencia extends Operacao{

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_conta_destino")
    private ContaDigital contaDestino;

    public Transferencia() {
       this.setTipo(Operacao.TipoOperacao.TRANSFERENCIA);
    }
}
