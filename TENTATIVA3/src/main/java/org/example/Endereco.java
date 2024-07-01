package org.example;

public class Endereco {
    private String logradouro;
    private String tipoLog;
    private String complemento;
    private String cidade;
    private String uf;
    private String cep;
    private String numero;
    private String bairro;

    public Endereco(String logradouro, String tipoLog, String complemento, String cidade, String uf, String cep, String numero, String bairro) {
        this.logradouro = logradouro;
        this.tipoLog = tipoLog;
        this.complemento = complemento;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
        this.numero = numero;
        this.bairro = bairro;
    }

    @Override
    public String toString() {
        return tipoLog + " " + logradouro + ", " + numero + (complemento != null && !complemento.isEmpty() ? " (" + complemento + ")" : "") +
                ", " + bairro + ", " + cidade + " - " + uf + ", CEP: " + cep;
    }
}
