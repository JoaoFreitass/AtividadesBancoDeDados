package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataBase {
    private final String url = "jdbc:postgresql://localhost:5432/LocacaoFilmes";
    private final String user = "postgres";
    private final String password = "admin";
    private Connection connection;

    public DataBase() {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    public List<FilmeLocacao> listarFilmesAlugadosPorCliente(int codCliente) {
        List<FilmeLocacao> filmesLocados = new ArrayList<>();
        String query = "SELECT f.titulo_original, f.titulo, l.data_loc, lf.data_devol " +
                "FROM locacao l " +
                "JOIN locacao_filme lf ON l.cod_loc = lf.fk_cod_loc " +
                "JOIN filmes f ON lf.fk_cod_filme = f.cod_filme " +
                "WHERE l.fk_cod_cli = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, codCliente);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Date dataLocacao = rs.getDate("data_loc");
                Date dataDevolucao = rs.getDate("data_devol");
                String tituloOriginal = rs.getString("titulo_original");
                String titulo = rs.getString("titulo");

                FilmeLocacao filme = new FilmeLocacao(tituloOriginal, titulo, dataLocacao, dataDevolucao);
                filmesLocados.add(filme);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar filmes alugados por cliente: " + e.getMessage());
        }

        return filmesLocados;
    }

    public List<ClienteDependente> listarClientesEDependentes() {
        List<ClienteDependente> clientesDependentes = new ArrayList<>();

        String sql = "SELECT c.cod_cli, c.nome AS cliente_nome, d.cod_dep, d.parentesco " +
                "FROM CLIENTE c " +
                "LEFT JOIN DEPENDENTE d ON c.cod_cli = d.fk_cod_cli";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int codCliente = rs.getInt("cod_cli");
                String nomeCliente = rs.getString("cliente_nome");
                int codDependente = rs.getInt("cod_dep");
                String parentesco = rs.getString("parentesco");

                if (codDependente != 0) { // Filtra dependentes inválidos
                    clientesDependentes.add(new ClienteDependente(codCliente, nomeCliente, codDependente, parentesco));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientesDependentes;
    }

    public List<Filme> listarFilmesPorGenero(int codGenero) {
        List<Filme> filmes = new ArrayList<>();

        String sql = "SELECT cod_filme, titulo_original, titulo, quantidade, fk_cod_cat, fk_cod_gen " +
                "FROM FILMES " +
                "WHERE fk_cod_gen = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, codGenero);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int codFilme = rs.getInt("cod_filme");
                    String tituloOriginal = rs.getString("titulo_original");
                    String titulo = rs.getString("titulo");
                    int quantidade = rs.getInt("quantidade");
                    int fkCodCat = rs.getInt("fk_cod_cat");
                    int fkCodGen = rs.getInt("fk_cod_gen");

                    filmes.add(new Filme(codFilme, tituloOriginal, titulo, quantidade, fkCodCat, fkCodGen));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return filmes;
    }

    public List<Cliente> listarClientesPorProfissao(int codProfissao) {
        List<Cliente> clientes = new ArrayList<>();

        String sql = "SELECT cod_cli, cpf, nome, telefone, fk_cod_prof " +
                "FROM CLIENTE " +
                "WHERE fk_cod_prof = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, codProfissao);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int codCli = rs.getInt("cod_cli");
                    String cpf = rs.getString("cpf");
                    String nome = rs.getString("nome");
                    String telefone = rs.getString("telefone");
                    int fkCodProf = rs.getInt("fk_cod_prof");

                    clientes.add(new Cliente(codCli, cpf, nome, telefone, fkCodProf));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientes;
    }


    public List<Filme> listarFilmesPorCategoriaEQuantidade(int codCategoria) {
        List<Filme> filmes = new ArrayList<>();

        String sql = "SELECT cod_filme, titulo_original, titulo, quantidade, fk_cod_cat, fk_cod_gen " +
                "FROM FILMES " +
                "WHERE fk_cod_cat = ? AND quantidade > 5";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, codCategoria);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int codFilme = rs.getInt("cod_filme");
                    String tituloOriginal = rs.getString("titulo_original");
                    String titulo = rs.getString("titulo");
                    int quantidade = rs.getInt("quantidade");
                    int fkCodCat = rs.getInt("fk_cod_cat");
                    int fkCodGen = rs.getInt("fk_cod_gen");

                    filmes.add(new Filme(codFilme, tituloOriginal, titulo, quantidade, fkCodCat, fkCodGen));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return filmes;
    }

    public List<Ator> listarAtoresPorTituloFilme(String titulo) {
        List<Ator> atores = new ArrayList<>();

        String sql = "SELECT a.cod_ator, a.nome " +
                "FROM ATOR a " +
                "INNER JOIN filme_autor fa ON a.cod_ator = fa.fk_cod_ator " +
                "INNER JOIN FILMES f ON fa.fk_cod_filme = f.cod_filme " +
                "WHERE f.titulo = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, titulo);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int codAtor = rs.getInt("cod_ator");
                    String nome = rs.getString("nome");
                    Ator ator = new Ator(codAtor, nome);
                    atores.add(ator);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return atores;
    }

    public Endereco obterEnderecoCliente(int codCliente) {
        String query = "SELECT e.logradouro, e.tipo_log, e.complemento, e.cidade, e.uf, e.cep, e.numero, e.bairro " +
                "FROM endereco e " +
                "JOIN cli c ON e.cod_end = c.fk_cod_end " +
                "JOIN cliente cli ON cli.cod_cli = c.fk_cod_cli " +
                "WHERE cli.cod_cli = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, codCliente);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Endereco(
                            rs.getString("logradouro"),
                            rs.getString("tipo_log"),
                            rs.getString("complemento"),
                            rs.getString("cidade"),
                            rs.getString("uf"),
                            rs.getString("cep"),
                            rs.getString("numero"),
                            rs.getString("bairro")
                    );
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<FilmeGeneroCategoria> listarFilmesGenerosCategorias() {
        String query = "SELECT f.titulo, g.nome AS genero, c.nome AS categoria " +
                "FROM filmes f " +
                "JOIN genero g ON f.fk_cod_gen = g.cod_gen " +
                "JOIN categoria c ON f.fk_cod_cat = c.cod_cat";
        List<FilmeGeneroCategoria> filmes = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                FilmeGeneroCategoria filme = new FilmeGeneroCategoria(
                        rs.getString("titulo"),
                        rs.getString("genero"),
                        rs.getString("categoria")
                );
                filmes.add(filme);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filmes;
    }

    public List<ClienteLocacao> listarClientesPorFilme(int codFilme) {
        String query = "SELECT c.nome, l.data_loc " +
                "FROM cliente c " +
                "JOIN locacao l ON c.cod_cli = l.fk_cod_cli " +
                "JOIN locacao_filme lf ON l.cod_loc = lf.fk_cod_loc " +
                "WHERE lf.fk_cod_filme = ?";
        List<ClienteLocacao> clientes = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, codFilme);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ClienteLocacao cliente = new ClienteLocacao(
                            rs.getString("nome"),
                            rs.getDate("data_loc")
                    );
                    clientes.add(cliente);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    public List<Cliente> listarClientesComMultasSuperiores(float valorMulta) {
        List<Cliente> clientes = new ArrayList<>();

        String sql = "SELECT c.cod_cli, c.cpf, c.nome, c.telefone, c.fk_cod_prof " +
                "FROM cliente c " +
                "JOIN locacao l ON c.cod_cli = l.fk_cod_cli " +
                "WHERE l.multa > CAST(? AS money)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setFloat(1, valorMulta);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int codCli = rs.getInt("cod_cli");
                    String cpf = rs.getString("cpf");
                    String nome = rs.getString("nome");
                    String telefone = rs.getString("telefone");
                    int codProfissao = rs.getInt("fk_cod_prof");

                    Cliente cliente = new Cliente(codCli, cpf, nome, telefone, codProfissao);
                    clientes.add(cliente);
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar clientes com multas superiores: " + e.getMessage());
        }

        return clientes;
    }




    public void fecharConexao () {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão com o banco de dados: " + e.getMessage());
            }
        }
    }


