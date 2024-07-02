package org.example;
import java.util.Date;

public class Locacao {
    private int codLocacao;
    private int codCliente;
    private Date dataLocacao;

    public Locacao(int codLocacao, int codCliente, Date dataLocacao) {
        this.codLocacao = codLocacao;
        this.codCliente = codCliente;
        this.dataLocacao = dataLocacao;
    }

    public int getCodLocacao() {
        return codLocacao;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public Date getDataLocacao() {
        return dataLocacao;
    }

    @Override
    public String toString() {
        return "Locacao[ " +
                " Cod. Locacao= " + codLocacao +
                "  Cod. Cliente= " + codCliente +
                "  Data Locacao= " + dataLocacao +
                ']';
    }
}
