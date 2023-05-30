import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

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

    public static List<Citta> creaCammino(Nodo nodo) {
        List<Citta> risultato = new ArrayList<>();
        while(nodo != null) {
            risultato.add(nodo.getCitta());
            nodo = nodo.getPadre();
        }
        Collections.reverse(risultato);
        return risultato;
    }

    @Override
    public int compareTo(Nodo nodo) {
        return (int)(this.funzioneDiValutazione - nodo.getFunzioneDiValutazione());
    }

}