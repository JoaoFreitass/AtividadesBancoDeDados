package org.example;

public class Cliente {
    private int codCli;
    private String cpf;
    private String nome;
    private String telefone;
    private int fkCodProf;

    public Cliente(int codCli, String cpf, String nome, String telefone, int fkCodProf) {
        this.codCli = codCli;
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.fkCodProf = fkCodProf;
    }

    @Override
    public String toString() {
        return "Cliente [" +
                "Código cliente= " + codCli +
                "  CPF= " + cpf +
                "  Nome= " + nome +
                "  Telefone= " + telefone +
                "  Código profissão=" + fkCodProf +
                ']';
    }
}
