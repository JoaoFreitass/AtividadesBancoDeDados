package org.example;

public class Filme {
    private int codFilme;
    private String tituloOriginal;
    private String titulo;
    private int quantidade;
    private int fkCodCat;
    private int fkCodGen;

    public Filme(int codFilme, String tituloOriginal, String titulo, int quantidade, int fkCodCat, int fkCodGen) {
        this.codFilme = codFilme;
        this.tituloOriginal = tituloOriginal;
        this.titulo = titulo;
        this.quantidade = quantidade;
        this.fkCodCat = fkCodCat;
        this.fkCodGen = fkCodGen;
    }

    @Override
    public String toString() {
        return "Filme: [" +
                "Código do filme= " + codFilme +
                "  Titulo Original= " + tituloOriginal +
                "  Titulo= " + titulo +
                "  Quantidade= " + quantidade +
                "  Codigo da Categoria= " + fkCodCat +
                "  Codigo do Genero= " + fkCodGen +
                ']';
    }
}
