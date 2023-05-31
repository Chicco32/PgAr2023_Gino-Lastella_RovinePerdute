import java.util.Map;

public class MappaMetztli extends AbstractMappa {

    public MappaMetztli(Map<String, Citta> citta) {
        super(citta);
    }

    @Override
    protected float calcolaConsumo(Citta citta1, Citta citta2) {
        //Il consumo è pari alla differenza di altitudine tra le due città
        return Math.abs(citta1.getH() - citta2.getH());
    }
}