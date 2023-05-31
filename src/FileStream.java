import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

public class FileStream {

    public static final String DIRECTORY_INPUT = "files";
    public static List<String> fileInput;
    public static final String FILE_OUTPUT = "output/Routes.xml";
    
    /**
     * Legge i nomi dei file di input dalla directory di input e li salva in fileInput
     */
    public static void leggiNomiFile() {
        fileInput = new ArrayList<>();
        File cartella = new File(DIRECTORY_INPUT);
        for (File file : cartella.listFiles()) {
            fileInput.add(file.getName());
        }
        //Ordina i nomi dei file in base ai numeri che contengono
        Collections.sort(fileInput, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.valueOf(o1.replaceAll("[^0-9]", "")).compareTo
                (Integer.valueOf(o2.replaceAll("[^0-9]", "")));
            }
        });
    }

    private static XMLStreamReader inizializzaReader(String nomeFile) {
		try {
			FileInputStream fis = new FileInputStream(nomeFile);
			XMLInputFactory xmlf = XMLInputFactory.newInstance();
			XMLStreamReader xmlr = xmlf.createXMLStreamReader(fis);
			return xmlr;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

    private static XMLStreamWriter inizializzaWriter(String nomeOutput) {
		try {
			XMLOutputFactory xmlof = XMLOutputFactory.newInstance();
			XMLStreamWriter xmlw = xmlof.createXMLStreamWriter(new FileOutputStream(nomeOutput), "utf-8");
			xmlw.writeStartDocument("utf-8", "1.0");
            return xmlw;
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

    /**
     * Legge una lista di città da un file .xml
     * @param file il nome del file da cui leggere i dati
     * @return la lista di città lette
     */
    public static List<Citta> leggiFile(String file) {
        XMLStreamReader xmlr = inizializzaReader(file);
        List<Citta> citta = new ArrayList<>();
        List<String> link = new ArrayList<>();
        String nome = "", id = "";
        int x = 0, y = 0, h = 0;
        try {
            while (xmlr.hasNext()) {
                switch (xmlr.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT:
                        switch (xmlr.getLocalName()) {
                            //Se il tag è di una città, mi salvo i suoi attributi
                            case "city":
                                id = xmlr.getAttributeValue(null, "id");
                                nome = xmlr.getAttributeValue(null, "name");
                                x = Integer.parseInt(xmlr.getAttributeValue(null, "x"));
                                y = Integer.parseInt(xmlr.getAttributeValue(null, "y"));
                                h = Integer.parseInt(xmlr.getAttributeValue(null, "h"));
                                break;
                            //Se il tag è un collegamento, lo aggiungo alla lista dei collegamenti
                            case "link":
                                link.add(xmlr.getAttributeValue(0));
                                break;
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        //Alla fine del tag città, creo l'oggetto città e lo aggiungo alla lista
                        if(xmlr.getLocalName().equals("city")) {
                            Citta nuovaCitta = new Citta(id, nome, x, y, h, link);
                            citta.add(nuovaCitta);
                            link = new ArrayList<>();
                        }
                    break;
                }
                xmlr.next();
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return citta;
    }


    /**
     * Scrive il documento xml
     * @param fileOutput nome del file di output
     * @param squadra1
     * @param squadra2
     */
    public static void scriviOutput(String fileOutput, Squadra squadra1, Squadra squadra2) {
        XMLStreamWriter xmlw = inizializzaWriter(fileOutput);
        try {
            scriviSquadra(xmlw, squadra1);
            scriviSquadra(xmlw, squadra2);
            xmlw.writeEndDocument();
            xmlw.flush();
            xmlw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Scrive nel documento le informazioni relative a una squadra
     * @param xmlw
     * @param squadra
     * @throws XMLStreamException
     */
    public static void scriviSquadra(XMLStreamWriter xmlw, Squadra squadra) throws XMLStreamException{
        //Scrivo l'elemento routes coi suoi attributi
        xmlw.writeStartElement("routes");
        xmlw.writeAttribute("team", squadra.getNome());
        xmlw.writeAttribute("cost", String.valueOf(squadra.getCosto()));
        xmlw.writeAttribute("cities", String.valueOf(squadra.getPercorso().size()));
        //Scrivo le città attraversate da questa squadra
        for (Citta citta : squadra.getPercorso()) {
            xmlw.writeEmptyElement("city");
            xmlw.writeAttribute("id", citta.getId());
            xmlw.writeAttribute("name", citta.getNome());
        }
        xmlw.writeEndElement();
    }
}
