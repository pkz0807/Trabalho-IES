package cadastrapromo;

public class Administrador extends Usuario {
    public Administrador(String nome, String email, String senha) {
        super(nome, email, senha);
    }

    public void limitarDestaques(int limite) {
        System.out.println("Limite de destaques definido: " + limite);
    }

    public void substituirPromocao(Produto produto, Promocao nova) {
        produto.setPromocao(nova);
    }
}
