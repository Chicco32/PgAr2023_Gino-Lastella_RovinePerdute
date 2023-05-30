import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class AStar {

    private float costo;

    public List<Citta> eseguiAlgoritmo(AbstractMappa mappa) {
        Citta partenza = mappa.getPartenza();
        Citta arrivo = mappa.getArrivo();
        Map<Citta, Map<Citta, Float>> grafoStati = mappa.getGrafoStati();
        Map<Citta, Float> euristica = mappa.getEuristica();
        // creo la coda di priorità che serve all'algoritmo e creo la citta iniziale
        PriorityQueue<Nodo> frontiera = new PriorityQueue<>();
        frontiera.add(new Nodo(partenza, 0, euristica.get(partenza), null));

        Set<Citta> close = new HashSet<Citta>();
        Nodo nodoCorrente = null;
        // finche ho città da visitare, continua l'algoritmo
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
        return Nodo.creaCammino(nodoCorrente);
    }

    public float getCosto() {
        return this.costo;
    }
}
