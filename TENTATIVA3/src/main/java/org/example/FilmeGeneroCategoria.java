package org.example;

public class FilmeGeneroCategoria {
    private String titulo;
    private String genero;
    private String categoria;

    public FilmeGeneroCategoria(String titulo, String genero, String categoria) {
        this.titulo = titulo;
        this.genero = genero;
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Filme: " + titulo + ", Gênero: " + genero + ", Categoria: " + categoria;
    }
}
