package org.example;

public class Genero {
    private int codGen;
    private String nome;

    public Genero(int codGen, String nome) {
        this.codGen = codGen;
        this.nome = nome;
    }

    public int getCodGen() {
        return codGen;
    }

    public void setCodGen(int codGen) {
        this.codGen = codGen;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Genero{" +
                "codGen=" + codGen +
                ", nome='" + nome + '\'' +
                '}';
    }
}
