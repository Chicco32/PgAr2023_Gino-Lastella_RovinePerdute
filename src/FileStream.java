import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

public class FileStream {

    public static final String FILE_OUTPUT = "output/Routes.xml";
    
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
                            case "city":
                                id = xmlr.getAttributeValue(null, "id");
                                nome = xmlr.getAttributeValue(null, "name");
                                x = Integer.parseInt(xmlr.getAttributeValue(null, "x"));
                                y = Integer.parseInt(xmlr.getAttributeValue(null, "y"));
                                h = Integer.parseInt(xmlr.getAttributeValue(null, "h"));
                                break;
                            case "link":
                                link.add(xmlr.getAttributeValue(0));
                                break;
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
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

    public static void scriviSquadra(XMLStreamWriter xmlw, Squadra squadra) throws XMLStreamException{
        xmlw.writeStartElement("routes");
        xmlw.writeAttribute("team", squadra.getNome());
        xmlw.writeAttribute("cost", String.valueOf(squadra.getCosto()));
        xmlw.writeAttribute("cities", String.valueOf(squadra.getPercorso().size()));
        for (Citta citta : squadra.getPercorso()) {
            xmlw.writeEmptyElement("city");
            xmlw.writeAttribute("id", citta.getId());
            xmlw.writeAttribute("name", citta.getNome());
        }
        xmlw.writeEndElement();
    }
}
