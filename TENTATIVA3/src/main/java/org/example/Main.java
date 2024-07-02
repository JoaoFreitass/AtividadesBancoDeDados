package org.example;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final DataBase database = new DataBase();

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Listar todos os filmes alugados por um cliente específico");
            System.out.println("2 - Obter uma lista de clientes e seus dependentes");
            System.out.println("3 - Listar todos os filmes de um determinado gênero");
            System.out.println("4 - Exibir todos os clientes que têm uma profissão específica");
            System.out.println("5 - Encontrar todos os filmes em uma categoria específica com quantidade disponível maior que 5");
            System.out.println("6 - Listar todos os atores que participaram de filmes com um determinado título");
            System.out.println("7 - Obter o endereço completo de um cliente específico");
            System.out.println("8 - Listar todos os filmes e seus respectivos gêneros e categorias");
            System.out.println("9 - Mostrar todos os clientes que alugaram um filme específico e a data de locação");
            System.out.println("10 - Exibir a lista de clientes com multas superiores a um valor específico.  //não funcional");
            System.out.println("11 - Listar todas as locações feitas em um período específico");
            System.out.println("12 - Listar os clientes e os filmes que eles alugaram, ordenados por data de locação");
            System.out.println("13 - Mostrar todos os clientes que moram em uma cidade específica e que alugaram filmes de uma categoria específica");

            System.out.println("0 - Sair");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    listarFilmesAlugadosPorCliente();
                    break;
                case 2:
                    listarClientesEDependentes();
                    break;
                case 3:
                    listarFilmesPorGenero();
                    break;
                case 4:
                    listarClientesPorProfissao();
                    break;
                case 5:
                    listarFilmesPorCategoriaEQuantidade();
                    break;
                case 6:
                    listarAtoresPorTituloFilme();
                    break;
                case 7:
                    obterEnderecoCompletoCliente();
                    break;
                case 8:
                    listarFilmesGenerosCategorias();
                    break;
                case 9:
                    listarClientesPorFilme();
                    break;
                case 10:
                    listarClientesComMultasSuperiores();
                    break;
                case 11:
                    listarLocacoesPorPeriodo();
                    break;
                case 12:
                    listarClientesEFilmesAlugados();
                    break;
                case 13:
                    listarClientesPorCidadeECategoria();
                    break;
                case 0:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);

        database.fecharConexao();
    }

    private static void listarFilmesAlugadosPorCliente() {
        System.out.println("Digite o código do cliente:");
        int codCliente = scanner.nextInt();
        scanner.nextLine();

        List<FilmeLocacao> filmesLocados = database.listarFilmesAlugadosPorCliente(codCliente);

        System.out.println("Filmes alugados pelo cliente:");
        for (FilmeLocacao filme : filmesLocados) {
            System.out.println(filme);
        }
    }

    private static void listarClientesEDependentes() {
        List<ClienteDependente> clientesDependentes = database.listarClientesEDependentes();

        System.out.println("Clientes e seus dependentes:");
        for (ClienteDependente clienteDependente : clientesDependentes) {
            System.out.println(clienteDependente);
        }
    }

    private static void listarFilmesPorGenero() {
        System.out.println("Digite o código do gênero:");
        int codGenero = scanner.nextInt();
        scanner.nextLine();

        List<Filme> filmes = database.listarFilmesPorGenero(codGenero);

        System.out.println("Filmes do gênero especificado:");
        for (Filme filme : filmes) {
            System.out.println(filme);
        }
    }

    private static void listarClientesPorProfissao() {
        System.out.println("Digite o código da profissão:");
        int codProfissao = scanner.nextInt();
        scanner.nextLine();

        List<Cliente> clientes = database.listarClientesPorProfissao(codProfissao);

        System.out.println("Clientes com a profissão especificada:");
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }

    private static void listarFilmesPorCategoriaEQuantidade() {
        try {
            System.out.println("Digite o código da categoria:");
            int codCategoria = scanner.nextInt();
            scanner.nextLine();

            List<Filme> filmes = database.listarFilmesPorCategoriaEQuantidade(codCategoria);

            if (filmes.isEmpty()) {
                System.out.println("Não há filmes nessa categoria com quantidade disponível maior que 5.");
            } else {
                System.out.println("Filmes na categoria especificada com quantidade maior que 5:");
                for (Filme filme : filmes) {
                    System.out.println(filme);
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
            scanner.nextLine();
        }
    }

    private static void listarAtoresPorTituloFilme() {
        System.out.println("Digite o título do filme:");
        String tituloFilme = scanner.nextLine();

        List<Ator> atores = database.listarAtoresPorTituloFilme(tituloFilme);

        if (atores.isEmpty()) {
            System.out.println("Nenhum ator encontrado para o título especificado.");
        } else {
            System.out.println("Atores que participaram do filme \"" + tituloFilme + "\":");
            for (Ator ator : atores) {
                System.out.println(ator);
            }
        }
    }

    private static void obterEnderecoCompletoCliente() {
        System.out.println("Digite o código do cliente:");
        int codCliente = scanner.nextInt();
        scanner.nextLine();

        Endereco endereco = database.obterEnderecoCliente(codCliente);

        if (endereco != null) {
            System.out.println("Endereço do cliente:");
            System.out.println(endereco);
        } else {
            System.out.println("Nenhum endereço encontrado para o cliente especificado.");
        }
    }

    private static void listarFilmesGenerosCategorias() {
        List<FilmeGeneroCategoria> filmes = database.listarFilmesGenerosCategorias();

        System.out.println("Filmes, seus gêneros e categorias:");
        for (FilmeGeneroCategoria filme : filmes) {
            System.out.println(filme);
        }
    }

    private static void listarClientesPorFilme() {
        System.out.println("Digite o código do filme:");
        int codFilme = scanner.nextInt();
        scanner.nextLine();

        List<ClienteLocacao> clientes = database.listarClientesPorFilme(codFilme);

        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente encontrou para o filme especificado.");
        } else {
            System.out.println("Clientes que alugaram o filme:");
            for (ClienteLocacao cliente : clientes) {
                System.out.println(cliente);
            }
        }

    }

    private static void listarClientesComMultasSuperiores() {
        try {
            System.out.println("Digite o valor mínimo de multa:");
            float valorMulta = scanner.nextFloat();
            scanner.nextLine();

            List<Cliente> clientes = database.listarClientesComMultasSuperiores(valorMulta);

            if (clientes.isEmpty()) {
                System.out.println("Nenhum cliente encontrado com multas superiores a $" + valorMulta);
            } else {
                System.out.println("Clientes com multas superiores a $" + valorMulta + ":");
                for (Cliente cliente : clientes) {
                    System.out.println(cliente);
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, insira um valor numérico.");
            scanner.nextLine();
        }
    }

    private static void listarLocacoesPorPeriodo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            System.out.println("Digite a data de início (yyyy-MM-dd):");
            Date dataInicio = sdf.parse(scanner.nextLine());
            System.out.println("Digite a data de fim (yyyy-MM-dd):");
            Date dataFim = sdf.parse(scanner.nextLine());

            List<Locacao> locacoes = database.listarLocacoesPorPeriodo(dataInicio, dataFim);

            System.out.println("Locações no período especificado:");
            for (Locacao locacao : locacoes) {
                System.out.println(locacao);
            }
        } catch (ParseException e) {
            System.out.println("Formato de data inválido. Por favor, use o formato yyyy-MM-dd.");
        }
    }

    private static void listarClientesEFilmesAlugados() {
        List<ClienteFilme> clienteFilmes = database.listarClientesEFilmesAlugados();

        System.out.println("Clientes e filmes alugados:");
        for (ClienteFilme clienteFilme : clienteFilmes) {
            System.out.println(clienteFilme);
        }
    }

    private static void listarClientesPorCidadeECategoria() {
        System.out.println("Digite a cidade:");
        String cidade = scanner.nextLine();
        System.out.println("Digite o código da categoria:");
        int codCategoria = scanner.nextInt();
        scanner.nextLine();

        List<ClienteCidadeCategoria> clienteCidadeCategorias = database.listarClientesPorCidadeECategoria(cidade, codCategoria);

        System.out.println("Clientes que moram na cidade e alugaram filmes da categoria especificada:");
        for (ClienteCidadeCategoria clienteCidadeCategoria : clienteCidadeCategorias) {
            System.out.println(clienteCidadeCategoria);
        }
    }
}
