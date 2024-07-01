package org.example;

import java.util.Date;

public class ClienteLocacao {
    private String nome;
    private Date dataLocacao;

    public ClienteLocacao(String nome, Date dataLocacao) {
        this.nome = nome;
        this.dataLocacao = dataLocacao;
    }

    @Override
    public String toString() {
        return "Cliente: " + nome + ", Data de locação: " + dataLocacao;
    }
}
