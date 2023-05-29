import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainRovinePerdute {
    public static void main(String[] args) {
        Map<String, Citta> citta = new HashMap<>();
        List<Citta> cities = FileStream.leggiFile("Files/PgAr_Map_12.xml");
        for (Citta c : cities) {
            citta.put(c.getId(), c);
        }
        for (Citta c : cities) {
            System.out.println(c.getId() + " " + c.getNome());
        }
    }
}
