package org.example;

public class ClienteDependente {
    private int codCliente;
    private String nomeCliente;
    private int codDependente;
    private String parentesco;

    public ClienteDependente(int codCliente, String nomeCliente, int codDependente, String parentesco) {
        this.codCliente = codCliente;
        this.nomeCliente = nomeCliente;
        this.codDependente = codDependente;
        this.parentesco = parentesco;
    }

    @Override
    public String toString() {
        return "ClienteDependente [" +
                "Código do cliente= " + codCliente +
                "  Nome do Cliente= " + nomeCliente +
                "  Cod. do Dependente= " + codDependente +
                "  Parentesco= " + parentesco +
                ']';
    }
}
