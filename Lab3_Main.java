package lr3;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class Main {
    public static void main(String[] args) {
        try {
            // Створюємо фабрику для створення об'єкту DocumentBuilder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            // Створюємо об'єкт DocumentBuilder
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Створюємо новий документ
            Document document = builder.newDocument();

            // Створюємо кореневий елемент
            Element rootElement = document.createElement("root");
            document.appendChild(rootElement);

            // Додаємо дочірні елементи до кореневого елемента
            Element dataElement = document.createElement("data");
            dataElement.appendChild(document.createTextNode("Example Data"));
            rootElement.appendChild(dataElement);

            // Виводимо створений XML-документ на екран
            printXML(document.getDocumentElement(), "");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Метод для виведення XML-документу на екран
    private static void printXML(Node node, String indent) {
        System.out.print(indent);
        System.out.print("<" + node.getNodeName() + ">");
        if (node.hasChildNodes()) {
            NodeList children = node.getChildNodes();
            for (int i = 0; i < children.getLength(); i++) {
                Node child = children.item(i);
                if (child.getNodeType() == Node.TEXT_NODE) {
                    System.out.print(child.getNodeValue());
                } else {
                    printXML(child, indent + "  ");
                }
            }
        }
        System.out.print("</" + node.getNodeName() + ">");
    }
}
