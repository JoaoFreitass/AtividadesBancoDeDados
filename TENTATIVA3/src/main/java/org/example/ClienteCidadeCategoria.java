package org.example;

class ClienteCidadeCategoria {
    private String clienteNome;
    private String cidade;
    private String categoriaNome;

    public ClienteCidadeCategoria(String clienteNome, String cidade, String categoriaNome) {
        this.clienteNome = clienteNome;
        this.cidade = cidade;
        this.categoriaNome = categoriaNome;
    }

    public String getClienteNome() {
        return clienteNome;
    }

    public String getCidade() {
        return cidade;
    }

    public String getCategoriaNome() {
        return categoriaNome;
    }

    @Override
    public String toString() {
        return "ClienteCidadeCategoria[ " +
                "Nome= " + clienteNome +
                " Cidade= " + cidade +
                " Categoria= " + categoriaNome +
                ']';
    }
}
