import java.util.*;

public class Lojista extends Usuario {
    public List<Produto> meusProdutos;
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
        minhasPromocoes.set(index, nova);
    }

    public void excluirPromocao(int index) {
        minhasPromocoes.remove(index);
    }

    public void visualizarPromocoes() {
        for (Promocao p : minhasPromocoes) {
            System.out.println(p.getNome());
        }
    }
}
