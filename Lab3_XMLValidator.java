package lr3;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;

public class XMLValidator {

    public static void main(String[] args) {
        // Шлях до XML документу та XSD схеми
        String xmlFilePath = "Popular_Baby_Names_NY.xml";
        String xsdFilePath = "Popular_Baby_Names_NY.xsd";

        // Валідація XML документу за XSD схемою
        if (validateXML(xmlFilePath, xsdFilePath)) {
            System.out.println("XML документ є відповідним XSD схемі.");
        } else {
            System.out.println("XML документ не відповідає XSD схемі.");
        }
    }

    public static boolean validateXML(String xmlFilePath, String xsdFilePath) {
        try {
            // Створюємо фабрику для схеми
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            // Завантажуємо XSD схему
            Schema schema = factory.newSchema(new File(xsdFilePath));

            // Створюємо валідатор на основі схеми
            Validator validator = schema.newValidator();

            // Виконуємо валідацію XML документу
            validator.validate(new StreamSource(new File(xmlFilePath)));

            return true;
        } catch (Exception e) {
            // Обробляємо будь-які винятки під час валідації
            e.printStackTrace();
            return false;
        }
    }
}
