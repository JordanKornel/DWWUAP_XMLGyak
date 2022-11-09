package hu.domparse.dwwuap;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMReadDWWUAP {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		//Forrás file 
		File file = new File("XMLDWWUAP.xml");		
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		
		Document doc = dBuilder.parse(file);
		
		doc.getDocumentElement().normalize();
		//Gyökérelem
		System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
		//Gyerekelemek lementése
		NodeList nList = (NodeList) doc.getDocumentElement();
		
		for (int i = 0; i < nList.getLength(); i++) {
			Node node = nList.item(i);
			
			
			//Ha egyesület
			if(node.getNodeName() == "egyesulet"){
				if(!node.getNodeName().equals("#text")) {
					System.out.println("\n");
					System.out.println("Current element: " + node.getNodeName());
				}
				//Egyesület adatainak kiírása
				if(node.getNodeType()==Node.ELEMENT_NODE) {
					Element elem = (Element) node;
	
					String e_id = elem.getAttribute("E_ID");
	
					Node egyesulet_node = elem.getElementsByTagName("telekhely").item(0);
					String telekhely_name = egyesulet_node.getTextContent();
	
					Node egyesulet_node2 = elem.getElementsByTagName("alapitas_eve").item(0);
					String alapitas_eve_name = egyesulet_node2.getTextContent();
	
					Node egyesulet_node3 = elem.getElementsByTagName("e_nev").item(0);
					String e_nev_name = egyesulet_node3.getTextContent();
	
					System.out.printf("Egyesulet id: %s%n", e_id);
					System.out.printf("Telekhely neve: %s%n", telekhely_name);
					System.out.printf("Alapitas eve: %s%n", alapitas_eve_name);
					System.out.printf("Egyesulet neve: %s%n", e_nev_name);								
				}
			}
			
			
		}
	}

}