package xpathDWWUAP;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class xPathDWWUAP {
    public static void main(String[] args) {
        try {
            // File xmlFile = new File("student.xml");
            // DocumentBuildert letrehozasa
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse("studentDWWUAP.xml");
            document.getDocumentElement().normalize();
            // az XPath keszitese
            XPath xPath = XPathFactory.newInstance().newXPath();
            // Meg kell adni az eleresi Ut kifejezest es a csom6pont listat:

            String expression = "/class/student";
            // 1-12
            // String expression = "//student[@id=01]";
            // String expression = "//student";
            // String expression = "/class/student[2]";
            // String expression = "/class/student[last()]";
            // String expression = "/class/student[last()-1]";
            // String expression = "/class/*";
            // String expression = "/class/*[@*>=1]";
            // String expression = "/descendant-or-self::*";
            // String expression = "/class/student[20<age]";
            // String expression = "/class/student/firstname | /class/student/lastname";
            // KeszitsUnk egy listat, majd a xPath kifejezest le kell forditani es ki kell
            // ertekelni.
            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
            // A for ciklus segitsegevel a NodeList csom6pontjain vegig kell
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                System.out.println("\nAktualis elem: " + node.getNodeName());
                // Meg kell vizsgalni a csom6pontot, tesztelni kell a subelement
                if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("student")) {
                    Element element = (Element) node;
                    // az id attribOtumot ad vissza
                    System.out.println(
                            "Hallgat6 ID: " + element.getAttribute("id"));
                    // keresztnevet ad vissza
                    System.out.println(
                            "Keresztnev: " + element.getElementsByTagName("firstname").item(0).getTextContent());
                    // vezeteknevet ad vissza
                    System.out.println(
                            "Vezeteknev: " + element.getElementsByTagName("lastname").item(0).getTextContent());

                    // becenevet ad vissza
                    System.out.println(
                            "Becenev: " + element.getElementsByTagName("nickname").item(0).getTextContent());
                    // kort ad vissza
                    System.out.println(
                            "Km " + element.getElementsByTagName("age").item(0).getTextContent());
                }
            }
        } catch (

        ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }
}
