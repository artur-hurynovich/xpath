package XML.xpath;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;
import java.util.ArrayList;
public class XPathClass {
    public static void main(String[] args) {
        ArrayList<String> cars = new ArrayList<>();
        String mark;
        String model;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse("cars.xml");
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();
            XPathExpression expression = xPath.compile("//car/characteristics[engineCapacity > 4.5]/..");
            NodeList carElements = (NodeList) expression.evaluate(document, XPathConstants.NODESET);
            for (int i = 0; i < carElements.getLength(); i++) {
                Element carElement = (Element) carElements.item(i);
                mark = carElement.getElementsByTagName("mark").item(0).getTextContent();
                model = carElement.getElementsByTagName("model").item(0).getTextContent();
                cars.add(mark + " " + model);
            }
            System.out.println(cars);
        }
        catch (ParserConfigurationException|SAXException|IOException|XPathExpressionException e) {
            e.printStackTrace();
        }
    }
}
