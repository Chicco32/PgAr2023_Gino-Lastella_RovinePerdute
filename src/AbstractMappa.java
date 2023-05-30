import java.util.HashMap;
import java.util.Map;

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

    private void creaGrafoStati() {
        this.grafoStati = new HashMap<>();
        for (Citta citta : this.citta.values()) {
            Map<Citta, Float> connessioni = new HashMap<>();
            for (String idCittaAdiacente : citta.getLink()) {
                Citta adiacente = this.citta.get(idCittaAdiacente);
                float distanza = this.calcolaDistanza(citta, adiacente);
                connessioni.put(adiacente, distanza);
            }
            this.grafoStati.put(citta, connessioni);
        }
    }
    
    protected abstract float calcolaDistanza(Citta citta1, Citta citta2);

    private void creaFunzioneEuristica() {
        this.euristica = new HashMap<>();
        for (Citta citta : this.citta.values()) {
            float dla = (float)Math.sqrt(Math.pow(citta.getX() - this.arrivo.getX(), 2) + Math.pow(citta.getY() - this.arrivo.getY(), 2));
            this.euristica.put(citta, dla);
        }
    }

}
