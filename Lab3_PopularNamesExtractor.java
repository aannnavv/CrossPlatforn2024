package lr3;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.ArrayList;
import java.util.List;

public class PopularNamesExtractor {
    static class CustomHandler extends DefaultHandler {
        private List<BabyName> babyNames = new ArrayList<>();
        private String currentName;
        private String currentGender;
        private int currentCount;
        private int currentRating;

        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if ("babyName".equals(qName)) {
                currentName = attributes.getValue("name");
                currentGender = attributes.getValue("gender");
                currentCount = Integer.parseInt(attributes.getValue("count"));
                currentRating = Integer.parseInt(attributes.getValue("rating"));
            }
        }

        public void endElement(String uri, String localName, String qName) throws SAXException {
            if ("babyName".equals(qName)) {
                babyNames.add(new BabyName(currentName, currentGender, currentCount, currentRating));
            }
        }

        public List<BabyName> getBabyNames() {
            return babyNames;
        }
    }
}
