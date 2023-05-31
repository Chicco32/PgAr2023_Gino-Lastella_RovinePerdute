import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/**
 * Classe che rappresenta il nodo del grafo, contiene una città, il consumo che richiederebbe arrivarci,
 * un valore di comparazione e il nodo precedente nel cammino
 */
public class Nodo implements Comparable<Nodo> {

    private Citta citta;
    private float camminoParziale;
    private float funzioneDiValutazione;
    private Nodo padre;

    public Nodo(Citta citta, float camminoParziale, float funzioneDiValutazione, Nodo padre) {
        this.citta = citta;
        this.camminoParziale = camminoParziale;
        this.funzioneDiValutazione = funzioneDiValutazione;
        this.padre = padre;
    }

    /**
     * Metodo ricorsivo che compone la lista di città da percorrere per arrivare a questo nodo dalla partenza
     * @param nodo il nodo che rappresenta l'arrivo del percorso
     * @return Lista di città percorse
     */
    public static List<Citta> creaCammino(Nodo nodo) {
        List<Citta> risultato = new ArrayList<>();
        while(nodo != null) {
            risultato.add(nodo.getCitta());
            nodo = nodo.getPadre();
        }
        Collections.reverse(risultato);
        return risultato;
    }
    
    public Citta getCitta() {
        return this.citta;
    }
    
    public float getCamminoParziale() {
        return this.camminoParziale;
    }

    public float getFunzioneDiValutazione() {
        return this.funzioneDiValutazione;
    }

    public Nodo getPadre() {
        return this.padre;
    }
    
    @Override
    public int compareTo(Nodo nodo) {
        return (int)(this.funzioneDiValutazione - nodo.getFunzioneDiValutazione());
    }
}