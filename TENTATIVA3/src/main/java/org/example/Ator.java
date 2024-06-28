package org.example;

public class Ator {
    private int codAtor;
    private String nome;

    public Ator(int codAtor, String nome) {
        this.codAtor = codAtor;
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Ator: [" +
                "Código do Ator= " + codAtor +
                "  Nome= " + nome +
                ']';
    }
}
