
/**
 * Classificação de engajamento do assinante.
 * Cada classificação tem um fator aplicado sobre a tarifa base:
 * INICIANTE 1,00; REGULAR 1,00; ENGAJADO 0,95; BINGE 1,10.
 */
public enum Engajamento {
    INICIANTE(1), 
    REGULAR(1), 
    ENGAJADO(0.95), 
    BINGE(1.1);

    public double fator;

    private Engajamento(double fator) {
        this.fator = fator;
    }

    public double getFator(){
        return this.fator;
    }
}
