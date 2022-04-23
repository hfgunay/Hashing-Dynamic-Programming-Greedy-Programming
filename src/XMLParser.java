import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.HashMap;
import java.util.Map;

public class XMLParser {
    /**
     * TODO: Parse the input XML file and return a dictionary as described in the assignment insturctions
     *
     * @param filename the input XML file
     * @return a dictionary as described in the assignment insturctions
     */
    public static Map<String, Malware> parse(String filename)  {
        Map<String, Malware> map = new HashMap<String, Malware>();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = dbFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(filename);
            Element documentElement = document.getDocumentElement();
            NodeList rows = documentElement.getElementsByTagName("row");
            if (rows != null && rows.getLength() > 0) {
                for (int i = 0; i < rows.getLength(); i++) {
                    Node node = rows.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        NodeList nodes;
                        nodes = element.getElementsByTagName("title");
                        String title = nodes.item(0).getChildNodes().item(0).getNodeValue();
                        nodes = element.getElementsByTagName("level");
                        int level = Integer.parseInt(nodes.item(0).getChildNodes().item(0).getNodeValue());
                        nodes = element.getElementsByTagName("hash");
                        String hash = nodes.item(0).getChildNodes().item(0).getNodeValue();
                        Malware malware = new Malware(title, level, hash);
                        map.put(hash, malware);
                    } } } }
        catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}


