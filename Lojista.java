package cadastrapromo;

import java.util.*;

public class Lojista extends Usuario {
    private List<Produto> meusProdutos;
    private List<Promocao> minhasPromocoes;

    public Lojista(String nome, String email, String senha) {
        super(nome, email, senha);
        meusProdutos = new ArrayList<>();
        minhasPromocoes = new ArrayList<>();
    }

    public void criarPromocao(Promocao p) {
        minhasPromocoes.add(p);
    }

    public void editarPromocao(int index, Promocao nova) {
        if (index >= 0 && index < minhasPromocoes.size()) {
            minhasPromocoes.set(index, nova);
        } else {
            System.out.println("Índice inválido para edição.");
        }
    }

    public void excluirPromocao(int index) {
        if (index >= 0 && index < minhasPromocoes.size()) {
            minhasPromocoes.remove(index);
        } else {
            System.out.println("Índice inválido para exclusão.");
        }
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

    public List<Produto> getMeusProdutos() {
        return meusProdutos;
    }

    public List<Promocao> getMinhasPromocoes() {
        return minhasPromocoes;
    }
}
