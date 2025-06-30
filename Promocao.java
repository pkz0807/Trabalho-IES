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
        if (LocalDateTime.now().isAfter(dataFim)) ativa = false;
    }

    public boolean estaAtiva() {
        return ativa;
    }

    public Duration getTempoRestante() {
        return Duration.between(LocalDateTime.now(), dataFim);
    }

    public float getEconomia(float preco) {
        return preco * desconto;
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

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }
    
}
