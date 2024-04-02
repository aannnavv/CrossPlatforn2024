package lr3;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashSet;
import java.util.Set;

public class XMLHandler extends DefaultHandler {
    private Set<String> tags = new HashSet<>();
    private StringBuilder currentElementContent;

    // Метод, який буде викликаний при початку обробки елементу
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        tags.add(qName); // Додаємо ім'я тегу до множини тегів
        currentElementContent = new StringBuilder(); // Очищаємо буфер для зберігання тексту між тегами
    }

    // Метод, який буде викликаний при завершенні обробки елементу
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        // Логіка для закриття тегів
        String content = currentElementContent.toString().trim(); // Отримуємо текст між тегами (без пробілів на початку і в кінці)
        System.out.println("Закрито тег: " + qName + ", зміст: " + content);

        // Очищаємо буфер для наступного тегу
        currentElementContent.setLength(0);
    }

    // Метод, який буде викликаний при зчитуванні тексту між тегами
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        // Зберігаємо текст між тегами у буфер
        currentElementContent.append(ch, start, length);
    }

    // Метод для отримання списку тегів
    public Set<String> getTags() {
        return tags;
    }
}
