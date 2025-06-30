package cadastrapromo;

import java.time.LocalDate;

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
        if (emPromocao()) {
            System.out.println("Promoção: " + promocao.getNome());
            System.out.println("Descrição: " + promocao.getDescricao());
            System.out.println("Desconto: " + (promocao.getDesconto() * 100) + "%");
            System.out.println("Válida até: " + promocao.getDataFim());
        } else {
            System.out.println("Produto sem promoção ativa.");
        }
    }

    public void setPromocao(Promocao p) {
        this.promocao = p;
    }

    public String getNome() {
        return nome;
    }

    public float getPrecoOriginal() {
        return precoOriginal;
    }

    public String getImagem() {
        return imagem;
    }

    public Promocao getPromocao() {
        return promocao;
    }
}
