public class Produto {
    private String nome;
    private float precoOriginal;
    private String imagem;
    private Promocao promocao;

    public Produto(String nome, float precoOriginal, String imagem) {
        this.nome = nome;
        this.precoOriginal = precoOriginal;
        this.imagem = imagem;
        this.promocao = null;
    }

    public boolean emPromocao() {
        return promocao != null && promocao.estaAtiva();
    }

    public float getPrecoComDesconto() {
        if (emPromocao()) {
            return precoOriginal * (1 - promocao.getDesconto());
        }
        return precoOriginal;
    }

    public void exibir() {
        System.out.println(nome + " - R$" + getPrecoComDesconto());
    }

    public void setPromocao(Promocao p) {
        this.promocao = p;
    }
}
