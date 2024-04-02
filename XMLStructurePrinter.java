package lr3;

import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

public class XMLStructurePrinter {

    public static void main(String[] args) {
        try {
            // Створюємо фабрику SAX парсера
            SAXParserFactory factory = SAXParserFactory.newInstance();
            // Створюємо SAX парсер
            SAXParser saxParser = factory.newSAXParser();

            // Задаємо обробник подій SAX парсера
            DefaultHandler handler = new DefaultHandler() {
                boolean inElement = false;

                // Метод, який викликається при початку елементу
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    // Виводимо ім'я тегу
                    System.out.println("Element: " + qName);
                    inElement = true;
                }

                // Метод, який викликається при завершенні елементу
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    inElement = false;
                }

                // Метод, який викликається при зчитуванні текстового вмісту елементу
                public void characters(char ch[], int start, int length) throws SAXException {
                    if (inElement) {
                        // Виводимо текстовий вміст елементу
                        System.out.println("  Text: " + new String(ch, start, length));
                    }
                }
            };

            // Запускаємо парсер для обробки XML документу
            saxParser.parse("Popular_Baby_Names_NY.xml", handler);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
