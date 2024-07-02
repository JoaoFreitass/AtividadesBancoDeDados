package org.example;

public class Categoria {
    private int codCat;
    private String nome;

    public Categoria(int codCat, String nome) {
        this.codCat = codCat;
        this.nome = nome;
    }

    public int getCodCat() {
        return codCat;
    }

    public void setCodCat(int codCat) {
        this.codCat = codCat;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Categoria[ " +
                "codCat= " + codCat +
                " Nome= " + nome +
                ']';
    }
}
