
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Os testes prontos servem de exemplo.
 * Complete os testes marcados com //TODO (Tarefas 4 e 5).
 */
public class AssinanteTest {

    private Assinante assinante;

    @BeforeEach
    void setUp() {
        assinante = new Assinante("Ana");
    }

    @Test
    void deveRegistrarAssistidoPorTitulo() {
        assinante.adicionar(new Episodio("Piloto", 1, 42));
        assertTrue(assinante.registrarAssistido("Piloto"));
        assertEquals(42, assinante.tempoTotalAssistido());
    }

    @Test
    void naoDeveRegistrarTituloInexistente() {
        assinante.adicionar(new Episodio("Piloto", 1, 42));
        assertFalse(assinante.registrarAssistido("Final"));
    }

    @Test
    void deveCalcularCreditoDeTempo() {
        assinante.adicionar(new Episodio("A", 1, 40));
        assinante.adicionar(new Episodio("B", 1, 50));
        assinante.registrarAssistido("A");
        assertEquals(50, assinante.creditoDeTempo());
    }

    @Test
    void resumoDeveConterNomeEClassificacao() {
        assinante.adicionar(new Episodio("Piloto", 1, 42));
        String r = assinante.resumo();
        assertTrue(r.contains("Ana"), r);
        assertTrue(r.contains("INICIANTE"), r);
    }

    @Test
    void deveClassificarEngajamento() {
        assinante.adicionar(new Episodio("A", 1, 50));
        assinante.adicionar(new Episodio("B", 1, 40));
        assinante.adicionar(new Episodio("C", 1, 10));

        //Assistiu 50%(REGULAR); FATOR = 1
        assinante.registrarAssistido("A");
        assertEquals(1.0, assinante.classificacaoEngajamento().getFator(), 0.01);
        
        //Assistiu 90%(BINGE); FATOR = 1,1
        assinante.registrarAssistido("B");
        assertEquals(1.1, assinante.classificacaoEngajamento().getFator(), 0.01);
    }

    @Test
    void deveCalcularTarifaMensal() {
        assinante.adicionar(new Episodio("A", 1, 500));

        //Assinante BINGE(100% do tempo) mas MENOS de 600 min
        assinante.registrarAssistido("A");
        assertEquals(29.9*1.1, assinante.tarifaMensal(), 0.01);

        assinante.adicionar(new Episodio("B", 1, 500));
        //Assinante Binge(100% de tempo assistido) mas MAIS de 600 min
        assinante.registrarAssistido("B");
        assertEquals(0, assinante.tarifaMensal(), 0.01);
    }
}
