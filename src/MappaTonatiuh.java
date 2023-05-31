import java.util.Map;

public class MappaTonatiuh extends AbstractMappa {

    public MappaTonatiuh(Map<String, Citta> citta) {
        super(citta);
    }

    @Override
    protected float calcolaConsumo(Citta citta1, Citta citta2) {
        //Il consumo è pari alla distanza in linea d'aria tra le due città
        return (float)Math.sqrt(Math.pow(citta1.getX() - citta2.getX(), 2) + Math.pow(citta1.getY() - citta2.getY(), 2));
    }
}