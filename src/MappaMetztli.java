import java.util.Map;

public class MappaMetztli extends AbstractMappa {

    public MappaMetztli(Map<String, Citta> citta) {
        super(citta);
    }

    @Override
    protected float calcolaDistanza(Citta citta1, Citta citta2) {
        return Math.abs(citta1.getH() - citta2.getH());
    }
}