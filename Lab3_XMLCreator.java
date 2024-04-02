package lr3;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import lr3.PopularNamesExtractor.CustomHandler;
import java.io.File;
import java.util.List;

public class XMLCreator {
    public static void main(String[] args) {
        // Виконання кроку 4, отримання вибраних імен дитини
        CustomHandler handler = new CustomHandler();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse("Popular_Baby_Names_NY.xml", handler);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        List<BabyName> selectedNames = handler.getBabyNames();

        // Створення нового XML документу
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            // Створення кореневого елементу
            Element rootElement = doc.createElement("selectedNames");
            doc.appendChild(rootElement);

            // Додавання елементів для кожного вибраного імені
            for (BabyName name : selectedNames) {
                Element nameElement = doc.createElement("name");
                rootElement.appendChild(nameElement);

                Element nameValue = doc.createElement("value");
                nameValue.appendChild(doc.createTextNode(name.getName()));
                nameElement.appendChild(nameValue);

                Element genderElement = doc.createElement("gender");
                genderElement.appendChild(doc.createTextNode(name.getGender()));
                nameElement.appendChild(genderElement);

                Element countElement = doc.createElement("count");
                countElement.appendChild(doc.createTextNode(String.valueOf(name.getCount())));
                nameElement.appendChild(countElement);

                Element ratingElement = doc.createElement("rating");
                ratingElement.appendChild(doc.createTextNode(String.valueOf(name.getRating())));
                nameElement.appendChild(ratingElement);
            }

            // Запис документу в файл
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("selected_names.xml"));
            transformer.transform(source, result);

            System.out.println("Файл з вибраними іменами створено успішно!");

        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }
}
