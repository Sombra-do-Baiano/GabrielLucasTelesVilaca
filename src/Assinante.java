
import java.util.ArrayList;
import java.util.List;

/**
 * Assinante que agrega episódios.
 * Complete os métodos marcados com //TODO (Tarefas 2 e 3).
 */
public class Assinante {
    public static final double TARIFA_BASE = 29.90;
    public static final int MINUTOS_ISENCAO = 600;

    private String nome;
    private List<Episodio> episodios;

    public Assinante(String nome) {
        this.nome = nome == null ? "" : nome;
        this.episodios = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public boolean adicionar(Episodio e) {
        if (e == null) {
            return false;
        }
        episodios.add(e);
        return true;
    }

    public int quantidadeEpisodios() {
        return episodios.size();
    }

    /**
     * Marca como assistido o primeiro episódio com o título que ainda não foi assistido.
     */
    public boolean registrarAssistido(String titulo) {
        if (titulo == null) {
            return false;
        }
        for (Episodio e : episodios) {
            if (titulo.equals(e.getTitulo()) && !e.estaAssistido()) {
                e.marcarAssistido();
                return true;
            }
        }
        return false;
    }

    public int tempoTotalAssistido() {
        int soma = 0;
        for (Episodio e : episodios) {
            if (e.estaAssistido()) {
                soma += e.getMinutos();
            }
        }
        return soma;
    }

    public int creditoDeTempo() {
        int soma = 0;
        for (Episodio e : episodios) {
            if (!e.estaAssistido()) {
                soma += e.getMinutos();
            }
        }
        return soma;
    }

    /**
     * Pela proporção de episódios assistidos sobre o total:
     * até 10% INICIANTE; até 50% REGULAR; até 75% ENGAJADO; acima BINGE.
     * Lista vazia → INICIANTE.
     */
    public Engajamento classificacaoEngajamento() {
        double proporcao = (double)(tempoTotalAssistido())/(tempoTotalAssistido()+creditoDeTempo());
        if(proporcao<=0.10){
            return Engajamento.INICIANTE;
        }
        else if(proporcao <= 0.50){
            return Engajamento.REGULAR;
        }
        else if(proporcao <= 0.75){
            return Engajamento.ENGAJADO;
        }
        else{
            return Engajamento.BINGE;
        }
    }

    /**
     * 0 se tempo assistido &gt; 600 minutos;
     * caso contrário, TARIFA_BASE multiplicada pelo fator da classificação.
     */
    public double tarifaMensal() {
        double tarifa = TARIFA_BASE;
        double totalWatchTime = tempoTotalAssistido();

        // Como vimos, poderia ter sido mais um enum, mas eu vou assumir que isso vai quebrar o código e fazer o jeito que pediu
        if(totalWatchTime>=600){
            tarifa = 0 * TARIFA_BASE;
        }
        else{
            tarifa = classificacaoEngajamento().getFator() * TARIFA_BASE;
        }
        return tarifa;
    }

    public String resumo() {
        return nome
                + " | eps=" + quantidadeEpisodios()
                + " | assistido=" + tempoTotalAssistido() + "min"
                + " | credito=" + creditoDeTempo() + "min"
                + " | " + classificacaoEngajamento()
                + " | tarifa=" + String.format("%.2f", tarifaMensal());
    }
}
