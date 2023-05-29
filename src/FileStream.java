import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

public class FileStream {
    
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
}
