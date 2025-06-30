import java.time.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Cria um lojista simples
        System.out.println("Bem-vindo ao sistema de promoções");
        System.out.print("Digite seu nome: ");
        String nome = input.nextLine();

        System.out.print("Digite seu email: ");
        String email = input.nextLine();

        System.out.print("Digite sua senha: ");
        String senha = input.nextLine();

        Lojista lojista = new Lojista(nome, email, senha);

        Produto produto = null;
        Promocao promocao = null;

        int opcao;
        do {
            System.out.println("\n--MENU--");
            System.out.println("1 - Cadastrar Produto");
            System.out.println("2 - Criar Promoção");
            System.out.println("3 - Exibir Produto com Preço Final");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = input.nextInt();
            input.nextLine(); // limpa o buffer

            switch (opcao) {
                case 1:
                    System.out.print("Nome do produto: ");
                    String nomeProduto = input.nextLine();

                    System.out.print("Preço original: R$");
                    float preco = input.nextFloat();
                    input.nextLine();

                    System.out.print("Nome do arquivo de imagem (simulado): ");
                    String imagem = input.nextLine();

                    produto = new Produto(nomeProduto, preco, imagem);
                    lojista.meusProdutos.add(produto);
                    System.out.println("Produto cadastrado com sucesso!");
                    break;

                case 2:
                    if (produto == null) {
                        System.out.println("Você precisa cadastrar um produto primeiro.");
                        break;
                    }

                    System.out.print("Nome da promoção: ");
                    String nomePromo = input.nextLine();

                    System.out.print("Descrição: ");
                    String descPromo = input.nextLine();

                    System.out.print("Desconto (ex: 0.3 para 30%): ");
                    float desconto = input.nextFloat();
                    input.nextLine();

                    LocalDateTime agora = LocalDateTime.now();
                    LocalDateTime fim = agora.plusDays(5); // duração de 5 dias

                    promocao = new Promocao(nomePromo, descPromo, desconto, agora, fim);
                    promocao.ativar();
                    produto.setPromocao(promocao);
                    lojista.criarPromocao(promocao);
                    System.out.println("Promoção criada e aplicada ao produto!");
                    break;

                case 3:
                    if (produto == null) {
                        System.out.println("Nenhum produto cadastrado ainda.");
                    } else {
                        produto.exibir();
                        if (promocao != null) {
                            System.out.println("Economia: R$" + promocao.getEconomia(produto.getPrecoComDesconto()));
                        }
                    }
                    break;

                case 0:
                    System.out.println("Saindo... Até mais!");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);

        input.close();
    }
}

