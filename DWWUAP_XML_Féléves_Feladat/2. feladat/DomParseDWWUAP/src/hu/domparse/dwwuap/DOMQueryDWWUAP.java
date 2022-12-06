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

public class DOMQueryDWWUAP {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		// Forrás file
		File file = new File("XMLDWWUAP.xml");

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

		Document doc = dBuilder.parse(file);
		doc.getDocumentElement().normalize();
		// Gyökér elem
		System.out.print("Root element: ");
		System.out.println(doc.getDocumentElement().getNodeName());
		// Vadaszvizsga betöltése
		NodeList nList = doc.getElementsByTagName("vadaszvizsga");

		System.out.println("-----------------");

		// Végigfut a vadasznak a gyerek elemein kihagyva a kort
		for (int i = 0; i < nList.getLength(); i++) {
			Node node = nList.item(i);
			System.out.println("\nCurrent Element : " + node.getNodeName());
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element elem = (Element) node;
				System.out.println("ID:" + elem.getAttribute("V_ID"));
				NodeList nList2 = elem.getChildNodes();
				for (int j = 0; j < nList2.getLength(); j++) {
					Node node2 = nList2.item(j);
					if (node2.getNodeType() == Node.ELEMENT_NODE) {
						Element elem2 = (Element) node2;
						if (!node2.getNodeName().equals("kor")) {
							System.out.println(node2.getNodeName() + " : " + node2.getTextContent());
						}
						NodeList nList3 = elem2.getChildNodes();
						for (int k = 0; k < nList3.getLength(); k++) {
							Node node3 = nList3.item(k);
							if (node3.getNodeType() == Node.ELEMENT_NODE) {
								System.out.println(
										"vadaszvizsga :	" + node3.getNodeName() + " : " + node3.getTextContent());
							}
						}
					}
				}
			}
		}

		// Vizsgázók több ponttal mint 6
		NodeList nList2 = doc.getDocumentElement().getElementsByTagName("vadasz");

		for (int i = 0; i < nList2.getLength(); i++) {
			NodeList query = nList2.item(i).getChildNodes();
			for (int j = 0; j < query.getLength(); j++) {
				if (query.item(j).getNodeName().equals("elert_pont")
						&& Integer.parseInt(query.item(j).getTextContent()) > 6) {
					NodeList nList0 = nList2.item(i).getChildNodes();
					for (int k = 0; k < nList0.getLength(); k++) {
						Node node3 = nList0.item(k);
						if (node3.getNodeType() == Node.ELEMENT_NODE) {
							System.out.println(node3.getNodeName() + " : " + node3.getTextContent());
						}
					}
					System.out.println();
				}
			}
		}

		// Vizsgázók idősebbek mint 25
		NodeList nList3 = doc.getDocumentElement().getElementsByTagName("vadasz");

		for (int i = 0; i < nList3.getLength(); i++) {
			NodeList query = nList3.item(i).getChildNodes();
			for (int j = 0; j < query.getLength(); j++) {
				if (query.item(j).getNodeName().equals("kor")
						&& Integer.parseInt(query.item(j).getTextContent()) > 25) {
					NodeList nList0 = nList2.item(i).getChildNodes();
					for (int k = 0; k < nList0.getLength(); k++) {
						Node node3 = nList0.item(k);
						if (node3.getNodeType() == Node.ELEMENT_NODE) {
							System.out.println(node3.getNodeName() + " : " + node3.getTextContent());
						}
					}
					System.out.println();
				}
			}
		}

		// azt a vadászt adja vissza akinek a vezeték neve: Nagy
		NodeList nList5 = doc.getDocumentElement().getElementsByTagName("vadasz");
		for (int i = 0; i < nList5.getLength(); i++) {
			NodeList query = nList5.item(i).getChildNodes();
			for (int j = 0; j < query.getLength(); j++) {
				Node node2 = query.item(j);
				if (node2.getNodeName().equals("V_nev") && node2.getTextContent().equals("Nagy")) {
					NodeList nList0 = nList5.item(i).getChildNodes();
					for (int k = 0; k < nList0.getLength(); k++) {
						Node node3 = nList0.item(k);
						if (node3.getNodeType() == Node.ELEMENT_NODE) {
							System.out.println(node3.getNodeName() + " : " + node3.getTextContent());
						}
					}
					System.out.println();
				}
			}
		}
	}

}