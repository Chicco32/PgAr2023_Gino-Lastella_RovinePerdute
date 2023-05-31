import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Rappresenta una squadra esplorativa, calcola il percorso fino al traguardo e ne contiene i risultati
 */
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

    public String getNome() {
        return nome;
    }
    
    public float getCosto() {
        return costo;
    }
    
    public List<Citta> getPercorso() {
        return percorso;
    }

    public void trovaPercorso() {
        Citta partenza = this.mappa.getPartenza();
        Citta arrivo = this.mappa.getArrivo();
        Map<Citta, Map<Citta, Float>> grafoStati = this.mappa.getGrafoStati();
        Map<Citta, Float> euristica = this.mappa.getEuristica();
        // creo la coda di priorità che serve all'algoritmo e creo la citta iniziale
        PriorityQueue<Nodo> frontiera = new PriorityQueue<>();
        frontiera.add(new Nodo(partenza, 0, euristica.get(partenza), null));

        Set<Citta> close = new HashSet<Citta>();
        Nodo nodoCorrente = null;
        // finché ho città da visitare, continua l'algoritmo
        while(!frontiera.isEmpty()) {

            //estraggo la citta alla quale costa di meno arrivare
            nodoCorrente = frontiera.poll();
            Citta cittaCorrente = nodoCorrente.getCitta();
            //se la citta in cui mi trovo è la città di arrivo
            if(cittaCorrente.equals(arrivo)) {
                //termino l'algoritmo e restituisco il cammino dalla partenza all'arrivo
                this.costo = nodoCorrente.getCamminoParziale();
                break;
            } else {
                //altrimenti questa citta non la visito più
                close.add(nodoCorrente.getCitta());
            }

            //vedo le citta in cui posso andare
            Map<Citta, Float> connessioni = grafoStati.get(cittaCorrente);

            // studio tutte le citta in cui potenzialmente posso andare
            for(Citta citta : connessioni.keySet()) {
                // se la citta in cui posso andare non l'ho mai visitata
                if(!close.contains(citta)) {
                    //aggiungo la citta all'elenco di citta da visitare
                    float camminoParziale = nodoCorrente.getCamminoParziale() + connessioni.get(citta);
                    float funzioneDiValutazione = camminoParziale + euristica.get(citta);
                    frontiera.add(new Nodo(citta, camminoParziale, funzioneDiValutazione, nodoCorrente));
                }
            }
        }
        this.percorso = Nodo.creaCammino(nodoCorrente);
    }

}
