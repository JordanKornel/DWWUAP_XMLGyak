package hu.domparse.dwwuap;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMModifyDWWUAP {
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		
		try {
			//Forrás file 
			File inputFile = new File("XML2DWWUAP.xml");
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(inputFile);
	        
			//ELső és harmadik egyesület mentése
			Node vadasz1 = doc.getElementsByTagName("vadasz").item(0);
			Node vadasz3 = doc.getElementsByTagName("vadasz").item(2);
			//Gyökérelem
			Node vadaszvizsga = doc.getFirstChild();
	        
			//Harmadik egyesület ID váltás
			NamedNodeMap attr = vadasz3.getAttributes();
			Node nodeAttr = attr.getNamedItem("V_ID");
			nodeAttr.setTextContent("20");
	         
			//Második egyesület irányítószámának megváltoztatása
			NodeList list = vadasz1.getChildNodes();
			for (int i = 0; i < list.getLength(); i++) {
				Node node = list.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element elem = (Element) node;
					if ("telef_szam".equals(elem.getNodeName())) {					
						elem.setTextContent("06206964200");  						
					}
				}
			}
	        
			//Harmadik egyesületnek az Email megváltoztatása
			NodeList list1 = vadasz3.getChildNodes();
			for (int i = 0; i < list1.getLength(); i++) {
				Node node1 = list1.item(i);
				if (node1.getNodeType() == Node.ELEMENT_NODE) {
					Element elem1 = (Element) node1;
					if ("Email".equals(elem1.getNodeName())) {						
						elem1.setTextContent("valamiModify@Email.com");  						
					}
				}
			}
	        
			//Egyesületek törlése
			NodeList childNodes = vadaszvizsga.getChildNodes();
			for(int i = 0; i < childNodes.getLength(); i++) {
				Node node = childNodes.item(i);
	            
				if("egyesulet".equals(node.getNodeName()))
					vadaszvizsga.removeChild(node);
			}
	        
	        //Konzolra kírás
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			System.out.println("New File");
			StreamResult consoleResult = new StreamResult(System.out);
			transformer.transform(source, consoleResult);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
}