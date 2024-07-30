package com.BankingAPI.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "transferencias")
public class Transferencia extends Operacao{

    @JoinColumn(name = "id_conta_destino")
    private ContaDigital contaDestino;

    public Transferencia(){
        this.setTipo(TipoOperacao.TRANSFERENCIA);
    }
}
