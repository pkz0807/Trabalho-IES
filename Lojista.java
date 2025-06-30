package cadastrapromo;

import java.time.LocalDateTime;
import java.util.*;

public class Lojista extends Usuario {
    private List<Promocao> minhasPromocoes;

    public Lojista(String nome, String email, String senha) {
        super(nome, email, senha);
        minhasPromocoes = new ArrayList<>();
    }

    public void adicionarPromocao(Promocao p) {
        minhasPromocoes.add(p);
        System.out.println("Promoção criada com sucesso!");
    }

    public void visualizarPromocoes() {
        if (minhasPromocoes.isEmpty()) {
            System.out.println("Nenhuma promoção cadastrada.");
            return;
        }

        for (int i = 0; i < minhasPromocoes.size(); i++) {
            Promocao p = minhasPromocoes.get(i);
            System.out.println(i + " - " + p.getNome() + " (" + (p.estaAtiva() ? "Ativa" : "Inativa") + ")");
        }
    }

    public void editarPromocao(Scanner input) {
        visualizarPromocoes();
        if (minhasPromocoes.isEmpty()) return;
        try {
            System.out.print("Índice da promoção: ");
            int idx = Integer.parseInt(input.nextLine());
            Promocao p = minhasPromocoes.get(idx);

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

            minhasPromocoes.set(idx, new Promocao(novoNome, novaDesc, novoDesc, novaIni, novaFim));
            System.out.println("Promoção editada.");
        } catch (Exception e) {
            System.out.println("Erro ao editar: " + e.getMessage());
        }
    }

    public void excluirPromocao(Scanner input) {
        visualizarPromocoes();
        if (minhasPromocoes.isEmpty()) return;
        try {
            System.out.print("Índice da promoção: ");
            int idx = Integer.parseInt(input.nextLine());
            Promocao p = minhasPromocoes.get(idx);
            if (p.getDataInicio().isBefore(LocalDateTime.now())) {
                System.out.println("Só é possível excluir promoções futuras.");
                return;
            }
            minhasPromocoes.remove(idx);
            System.out.println("Promoção excluída.");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public List<Promocao> getMinhasPromocoes() {
        return minhasPromocoes;
    }
}
