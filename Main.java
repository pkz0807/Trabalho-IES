package cadastrapromo;

import java.time.*;
import java.util.*;

public class Main {
    static Scanner input = new Scanner(System.in);
    static Administrador admin;
    static Lojista lojista;

    public static void main(String[] args) {
        System.out.println("=== Sistema de Promoções ===");
        System.out.println("1 - Entrar como Lojista");
        System.out.println("2 - Entrar como Administrador");
        System.out.print("Escolha: ");
        int perfil = Integer.parseInt(input.nextLine());

        System.out.print("Digite seu nome: ");
        String nome = input.nextLine();

        System.out.print("Digite seu email: ");
        String email = input.nextLine();

        System.out.print("Digite sua senha: ");
        String senha = input.nextLine();

        if (perfil == 1) {
            lojista = new Lojista(nome, email, senha);
            menuLojista();
        } else {
            admin = new Administrador(nome, email, senha);
            menuAdministrador();
        }
    }

    public static void menuLojista() {
        int opcao;
        do {
            System.out.println("\n-- MENU LOJISTA --");
            System.out.println("1 - Cadastrar Produto");
            System.out.println("2 - Criar Promoção");
            System.out.println("3 - Exibir Produtos");
            System.out.println("4 - Visualizar Promoções");
            System.out.println("5 - Editar Promoção");
            System.out.println("6 - Excluir Promoção");
            System.out.println("7 - Ativar Promoção");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            try {
                opcao = Integer.parseInt(input.nextLine());
                switch (opcao) {
                    case 1 -> cadastrarProduto();
                    case 2 -> criarPromocao();
                    case 3 -> exibirProdutos();
                    case 4 -> visualizarPromocoes();
                    case 5 -> editarPromocao();
                    case 6 -> excluirPromocao();
                    case 7 -> ativarPromocao();
                    case 0 -> System.out.println("Saindo...");
                    default -> System.out.println("Opção inválida");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite um número válido.");
                opcao = -1;
            }
        } while (opcao != 0);
    }

    public static void cadastrarProduto() {
        try {
            System.out.print("Nome do Produto: ");
            String nome = input.nextLine();
            System.out.print("Preço: R$");
            float preco = Float.parseFloat(input.nextLine().replace(",", "."));
            System.out.print("Imagem (nome do arquivo): ");
            String img = input.nextLine();

            Produto p = new Produto(nome, preco, img);
            lojista.getMeusProdutos().add(p);
            System.out.println("Produto cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar produto: " + e.getMessage());
        }
    }

    public static void criarPromocao() {
        if (lojista.getMeusProdutos().isEmpty()) {
            System.out.println("Cadastre um produto primeiro.");
            return;
        }

        System.out.println("Escolha o produto:");
        for (int i = 0; i < lojista.getMeusProdutos().size(); i++) {
            System.out.println(i + " - " + lojista.getMeusProdutos().get(i).getNome());
        }
        try {
            int idx = Integer.parseInt(input.nextLine());
            Produto prod = lojista.getMeusProdutos().get(idx);

            if (prod.emPromocao()) {
                System.out.println("Produto já está em promoção.");
                return;
            }

            System.out.print("Nome da promoção: ");
            String nome = input.nextLine();
            System.out.print("Descrição: ");
            String desc = input.nextLine();
            System.out.print("Desconto (0.05 a 0.9): ");
            float descValor = Float.parseFloat(input.nextLine().replace(",", "."));

            if (descValor < 0.05f || descValor > 0.9f) {
                System.out.println("Desconto fora do intervalo permitido.");
                return;
            }

            System.out.print("Data e hora de início (yyyy-MM-ddTHH:mm): ");
            LocalDateTime inicio = LocalDateTime.parse(input.nextLine());
            System.out.print("Data e hora de fim (yyyy-MM-ddTHH:mm): ");
            LocalDateTime fim = LocalDateTime.parse(input.nextLine());

            if (inicio.isBefore(LocalDateTime.now())) {
                System.out.println("A data de início não pode ser no passado.");
                return;
            }
            if (!fim.isAfter(inicio)) {
                System.out.println("A data de fim deve ser posterior à de início.");
                return;
            }

            Promocao promo = new Promocao(nome, desc, descValor, inicio, fim);
            if (LocalDateTime.now().isAfter(inicio)) promo.ativar();
            prod.setPromocao(promo);
            lojista.criarPromocao(promo);
            System.out.println("Promoção criada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao criar promoção: " + e.getMessage());
        }
    }

    public static void exibirProdutos() {
        for (Produto p : lojista.getMeusProdutos()) {
            p.exibir();
            if (p.getPromocao() != null && p.emPromocao()) {
                System.out.println("Tempo restante: " + p.getPromocao().getTempoRestante().toHours() + "h");
            }
            System.out.println();
        }
    }

    public static void visualizarPromocoes() {
        lojista.visualizarPromocoes();
    }

    public static void editarPromocao() {
        visualizarPromocoes();
        try {
            System.out.print("Índice da promoção: ");
            int idx = Integer.parseInt(input.nextLine());
            Promocao p = lojista.getMinhasPromocoes().get(idx);

            if (p.getDataInicio().isBefore(LocalDateTime.now())) {
                System.out.println("Só é possível editar promoções futuras.");
                return;
            }

            System.out.print("Novo nome: ");
            String novoNome = input.nextLine();
            System.out.print("Nova descrição: ");
            String novaDesc = input.nextLine();
            System.out.print("Novo desconto: ");
            float novoDesc = Float.parseFloat(input.nextLine().replace(",", "."));
            System.out.print("Nova data início (yyyy-MM-ddTHH:mm): ");
            LocalDateTime novaIni = LocalDateTime.parse(input.nextLine());
            System.out.print("Nova data fim (yyyy-MM-ddTHH:mm): ");
            LocalDateTime novaFim = LocalDateTime.parse(input.nextLine());

            Promocao nova = new Promocao(novoNome, novaDesc, novoDesc, novaIni, novaFim);
            lojista.editarPromocao(idx, nova);
            System.out.println("Promoção editada.");
        } catch (Exception e) {
            System.out.println("Erro ao editar: " + e.getMessage());
        }
    }

    public static void excluirPromocao() {
        visualizarPromocoes();
        try {
            System.out.print("Índice da promoção: ");
            int idx = Integer.parseInt(input.nextLine());
            Promocao p = lojista.getMinhasPromocoes().get(idx);
            if (p.getDataInicio().isBefore(LocalDateTime.now())) {
                System.out.println("Só é possível excluir promoções futuras.");
                return;
            }
            lojista.excluirPromocao(idx);
            System.out.println("Promoção excluída.");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public static void ativarPromocao() {
        visualizarPromocoes();
        try {
            System.out.print("Índice da promoção: ");
            int idx = Integer.parseInt(input.nextLine());
            Promocao p = lojista.getMinhasPromocoes().get(idx);
            p.ativar();
            System.out.println("Promoção ativada.");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public static void menuAdministrador() {
        System.out.print("Defina o limite de destaques: ");
        try {
            int limite = Integer.parseInt(input.nextLine());
            admin.limitarDestaques(limite);
        } catch (NumberFormatException e) {
            System.out.println("Limite inválido.");
        }
    }
}
