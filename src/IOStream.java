import it.unibs.fp.mylib.InputDati;
import it.unibs.fp.mylib.MyMenu;

public class IOStream {

    private static final String INTRODUZIONE = "Scegli un file di città per cui generare i percorsi";
    private static final String MESSAGGIO_SCELTA = "Scegli una voce: ";

    public static int input() {
        MyMenu menu = new MyMenu(INTRODUZIONE, FileStream.fileInput.toArray(new String[0]));
        menu.stampaMenu();
        return InputDati.leggiIntero(MESSAGGIO_SCELTA, 0, FileStream.fileInput.size());
    }

}
