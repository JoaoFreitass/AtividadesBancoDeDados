package org.example;
import java.util.Date;

public class ClienteFilme {
    private String clienteNome;
    private String filmeTitulo;
    private Date dataLocacao;

    public ClienteFilme(String clienteNome, String filmeTitulo, Date dataLocacao) {
        this.clienteNome = clienteNome;
        this.filmeTitulo = filmeTitulo;
        this.dataLocacao = dataLocacao;
    }

    public String getClienteNome() {
        return clienteNome;
    }

    public String getFilmeTitulo() {
        return filmeTitulo;
    }

    public Date getDataLocacao() {
        return dataLocacao;
    }

    @Override
    public String toString() {
        return "ClienteFilme[ " +
                " Nome= " + clienteNome +
                "  Titulo= " + filmeTitulo +
                "  DataLocacao= " + dataLocacao +
                ']';
    }
}
