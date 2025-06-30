package cadastrapromo;

import java.time.*;

public class Promocao {
    private String nome;
    private String descricao;
    private float desconto;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private boolean ativa;

    public Promocao(String nome, String descricao, float desconto,
                    LocalDateTime dataInicio, LocalDateTime dataFim) {
        this.nome = nome;
        this.descricao = descricao;
        this.desconto = desconto;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.ativa = false;
    }

    public void ativar() {
        if (LocalDateTime.now().isAfter(dataInicio)) ativa = true;
    }

    public void desativar() {
        if (ativa) ativa = false;
    }

    public boolean estaAtiva() {
        return ativa;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public float getDesconto() {
        return desconto;
    }

    public LocalDateTime getDataFim() {
        return dataFim;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }
}
