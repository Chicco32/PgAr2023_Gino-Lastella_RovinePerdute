import java.util.List;

public class Squadra {
    
    private String nome;
    private AbstractMappa mappa;
    private float costo;
    private List<Citta> percorso;

    public Squadra(String nome, AbstractMappa mappa) {
        this.nome = nome;
        this.mappa = mappa;
        this.costo = 0;
        this.percorso = null;
    }

    public void trovaPercorso(AStar algoritmo) {
        this.percorso = algoritmo.eseguiAlgoritmo(this.mappa);
        this.costo = algoritmo.getCosto();
    }

    public String getNome() {
        return nome;
    }

    public float getCosto() {
        return costo;
    }

    public List<Citta> getPercorso() {
        return percorso;
    }

}
