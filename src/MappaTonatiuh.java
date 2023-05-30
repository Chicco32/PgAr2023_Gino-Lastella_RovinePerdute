import java.util.Map;

public class MappaTonatiuh extends AbstractMappa {

    public MappaTonatiuh(Map<String, Citta> citta) {
        super(citta);
    }

    @Override
    protected float calcolaDistanza(Citta citta1, Citta citta2) {
       return (float)Math.sqrt(Math.pow(citta1.getX() - citta2.getX(), 2) + Math.pow(citta1.getY() - citta2.getY(), 2));
    }
}