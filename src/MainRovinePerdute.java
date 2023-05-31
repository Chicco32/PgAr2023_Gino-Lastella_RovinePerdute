import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainRovinePerdute {
    public static void main(String[] args) {
        FileStream.leggiNomiFile();
        int scelta;
        while ((scelta = IOStream.input()) != 0) {
            String file = FileStream.DIRECTORY_INPUT + "/" + FileStream.fileInput.get(scelta-1);
            Map<String, Citta> citta = new HashMap<>();
            List<Citta> cities = FileStream.leggiFile(file);
            for (Citta c : cities) {
                citta.put(c.getId(), c);
            }
            Squadra squadra1 = new Squadra("Tonatiuh", new MappaTonatiuh(citta));
            Squadra squadra2 = new Squadra("Metztli", new MappaMetztli(citta));
            System.out.println("Inizio generazione del primo percorso");
            squadra1.trovaPercorso();
            System.out.println("Inizio generazione del secondo percorso");
            squadra2.trovaPercorso();
            FileStream.scriviOutput(FileStream.FILE_OUTPUT, squadra1, squadra2);
            System.out.println("Generazione del documento completata\n");
        }
    }
}
