package lr3;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.HashSet;
import java.util.Set;

public class NationalityExtractor {

    public static void main(String[] args) {
        // Шлях до XML документу
        String xmlFilePath = "Popular_Baby_Names_NY.xml";

        try {
            // Створюємо фабрику SAX парсера
            SAXParserFactory factory = SAXParserFactory.newInstance();

            // Створюємо SAX парсер
            SAXParser saxParser = factory.newSAXParser();

            // Створюємо обробник подій SAX парсера
            NationalityHandler handler = new NationalityHandler();

            // Запускаємо парсер для обробки XML документу
            saxParser.parse(xmlFilePath, handler);

            // Отримуємо та виводимо національні групи
            Set<String> nationalities = handler.getNationalities();
            System.out.println("Nationalities:");
            for (String nationality : nationalities) {
                System.out.println(nationality);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Клас для обробки подій SAX парсера
    static class NationalityHandler extends DefaultHandler {
        private Set<String> nationalities = new HashSet<>();

        // Метод, який викликається при початку елементу
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if ("ethcty".equals(qName)) {
                String nationality = attributes.getValue("nm");
                nationalities.add(nationality);
            }
        }

        // Метод для отримання національних груп
        public Set<String> getNationalities() {
            return nationalities;
        }
    }
}
