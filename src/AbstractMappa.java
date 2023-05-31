import java.util.HashMap;
import java.util.Map;

/**
 * Calcola e contiene i parametri per eseguire l'algoritmo di pathfinding
 */
public abstract class AbstractMappa {
    
    private Map<String, Citta> citta;
    private Map<Citta, Map<Citta, Float>> grafoStati;
    private Map<Citta, Float> euristica;
    private Citta partenza;
    private Citta arrivo;

    public AbstractMappa(Map<String, Citta> citta) {
        this.citta = citta;
        this.partenza = this.citta.get("0");
        this.arrivo = this.citta.get(String.valueOf(this.citta.size() - 1));
        this.creaGrafoStati();
        this.creaFunzioneEuristica();
    }

    public Map<Citta, Map<Citta, Float>> getGrafoStati() {
        return grafoStati;
    }

    public Map<Citta, Float> getEuristica() {
        return euristica;
    }

    public Citta getPartenza() {
        return partenza;
    }

    public Citta getArrivo() {
        return arrivo;
    }

    /**
     * Genera una mappa contenente tutte le città, ad ogni città corrisponde una mappa che contiene
     * tutte le città ad essa collegate e il carburante consumato per arrivarci
     */
    private void creaGrafoStati() {
        this.grafoStati = new HashMap<>();
        for (Citta citta : this.citta.values()) {
            Map<Citta, Float> connessioni = new HashMap<>();
            for (String idCittaAdiacente : citta.getLink()) {
                Citta adiacente = this.citta.get(idCittaAdiacente);
                float distanza = this.calcolaConsumo(citta, adiacente);
                connessioni.put(adiacente, distanza);
            }
            this.grafoStati.put(citta, connessioni);
        }
    }
    /**
     * Calcola il consumo di carburante per lo spostamento tra 2 città secondo diversi criteri
     * @param citta1
     * @param citta2
     * @return Il carburante consumato
     */
    protected abstract float calcolaConsumo(Citta citta1, Citta citta2);

    /**
     * Genera una mappa contenente tutte le città, ad ogni città corrisponde la distanza 
     * in linea d'aria con l'arrivo
     */
    private void creaFunzioneEuristica() {
        this.euristica = new HashMap<>();
        for (Citta citta : this.citta.values()) {
            float dla = (float)Math.sqrt(Math.pow(citta.getX() - this.arrivo.getX(), 2) + Math.pow(citta.getY() - this.arrivo.getY(), 2));
            this.euristica.put(citta, dla);
        }
    }

}
