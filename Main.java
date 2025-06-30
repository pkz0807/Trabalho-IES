package cadastrapromo;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);
    static Administrador admin;
    static Lojista lojista;

    public static void main(String[] args) {
        int perfil = -1;

        System.out.println("=== Sistema de Promoções ===");

        while (true) {
            System.out.println("1 - Entrar como Lojista");
            System.out.println("2 - Entrar como Administrador");
            System.out.println("3 - Sair");
            System.out.print("Escolha: ");
            try {
                perfil = Integer.parseInt(input.nextLine());
                if (perfil >= 1 && perfil <= 3) break;
                System.out.println("Número inválido. Digite 1, 2 ou 3.");
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número.");
            }
        }

        if (perfil == 3) {
            System.out.println("Saindo...");
            return;
        }

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
            System.out.println("1 - Criar Promoção");
            System.out.println("2 - Visualizar Promoções");
            System.out.println("3 - Editar Promoção");
            System.out.println("4 - Excluir Promoção");
            System.out.println("5 - Ativar Promoção");
            System.out.println("6 - Desativar Promoção");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            try {
                opcao = Integer.parseInt(input.nextLine());
                switch (opcao) {
                    case 1 -> {
                        System.out.print("Nome do produto: ");
                        String nomeProduto = input.nextLine();
                        System.out.print("Nome da promoção: ");
                        String nomePromo = input.nextLine();
                        System.out.print("Descrição: ");
                        String desc = input.nextLine();
                        System.out.print("Desconto (0.05 a 0.9): ");
                        float desconto = Float.parseFloat(input.nextLine().replace(",", "."));
                        System.out.print("Data e hora de início (yyyy-MM-ddTHH:mm): ");
                        LocalDateTime inicio = LocalDateTime.parse(input.nextLine());
                        System.out.print("Data e hora de fim (yyyy-MM-ddTHH:mm): ");
                        LocalDateTime fim = LocalDateTime.parse(input.nextLine());

                        if (!fim.isAfter(inicio)) {
                            System.out.println("Erro: A data de fim deve ser depois da data de início.");
                            break;
                        }

                        Promocao p = new Promocao(nomePromo, desc, desconto, inicio, fim);
                        lojista.adicionarPromocao(p);
                    }
                    case 2 -> lojista.visualizarPromocoes();
                    case 3 -> lojista.editarPromocao(input);
                    case 4 -> lojista.excluirPromocao(input);
                    case 5 -> {
                        lojista.visualizarPromocoes();
                        System.out.print("Índice da promoção para ativar: ");
                        int idx = Integer.parseInt(input.nextLine());
                        lojista.getMinhasPromocoes().get(idx).ativar();
                        System.out.println("Promoção ativada.");
                    }
                    case 6 -> {
                        lojista.visualizarPromocoes();
                        System.out.print("Índice da promoção para desativar: ");
                        int idx = Integer.parseInt(input.nextLine());
                        lojista.getMinhasPromocoes().get(idx).desativar();
                        System.out.println("Promoção desativada.");
                    }
                    case 0 -> System.out.println("Saindo...");
                    default -> System.out.println("Opção inválida");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
                opcao = -1;
            }
        } while (opcao != 0);
    }

    public static void menuAdministrador() {
        int limite = -1;
        while (limite < 0) {
            System.out.print("Defina o limite de destaques: ");
            try {
                limite = Integer.parseInt(input.nextLine());
                if (limite < 0) {
                    System.out.println("O limite deve ser um número positivo.");
                } else {
                    admin.limitarDestaques(limite);
                }
            } catch (NumberFormatException e) {
                System.out.println("Limite inválido. Digite um número inteiro.");
            }
        }
    }
}
