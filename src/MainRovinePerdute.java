import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainRovinePerdute {
    public static void main(String[] args) {
        Map<String, Citta> citta = new HashMap<>();
        List<Citta> cities = FileStream.leggiFile("Files/PgAr_Map_200.xml");
        for (Citta c : cities) {
            citta.put(c.getId(), c);
        }
        AbstractMappa mappa1 = new MappaTonatiuh(citta);
        Squadra squadra1 = new Squadra("Tonatiuh", mappa1);
        AbstractMappa mappa2 = new MappaMetztli(citta);
        Squadra squadra2 = new Squadra("Metztli", mappa2);
        AStar algoritmo = new AStar();
        squadra1.trovaPercorso(algoritmo);
        squadra2.trovaPercorso(algoritmo);
        FileStream.scriviOutput(FileStream.FILE_OUTPUT, squadra1, squadra2);
    }
}
