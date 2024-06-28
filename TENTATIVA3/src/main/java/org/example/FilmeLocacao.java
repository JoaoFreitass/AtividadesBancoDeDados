package org.example;

import java.util.Date;

public class FilmeLocacao {
    private String tituloOriginal;
    private String titulo;
    private Date dataLocacao;
    private Date dataDevolucao;

    public FilmeLocacao(String tituloOriginal, String titulo, Date dataLocacao, Date dataDevolucao) {
        this.tituloOriginal = tituloOriginal;
        this.titulo = titulo;
        this.dataLocacao = dataLocacao;
        this.dataDevolucao = dataDevolucao;
    }

    public String getTituloOriginal() {
        return tituloOriginal;
    }

    public void setTituloOriginal(String tituloOriginal) {
        this.tituloOriginal = tituloOriginal;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getDataLocacao() {
        return dataLocacao;
    }

    public void setDataLocacao(Date dataLocacao) {
        this.dataLocacao = dataLocacao;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    @Override
    public String toString() {
        return "Filmes alugados: " +
                "Titulo Original= " + tituloOriginal +
                "  Titulo= " + titulo +
                "  Data Da Locação= " + dataLocacao +
                "  Data da Devolução= " + dataDevolucao +
                ']'+ "\n";
    }
}
