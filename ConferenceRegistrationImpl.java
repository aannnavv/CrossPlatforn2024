import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class ConferenceRegistrationImpl extends UnicastRemoteObject implements ConferenceRegistration {
    private List<Participant> participants;

    protected ConferenceRegistrationImpl() throws RemoteException {
        participants = new ArrayList<>();
    }

    @Override
    public void registerParticipant(Participant participant) throws RemoteException {
        participants.add(participant);
    }

    @Override
    public void exportToXML(String filePath) throws RemoteException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            Element rootElement = doc.createElement("Participants");
            doc.appendChild(rootElement);

            for (Participant participant : participants) {
                Element participantElement = doc.createElement("Participant");
                rootElement.appendChild(participantElement);

                Element name = doc.createElement("Name");
                name.appendChild(doc.createTextNode(participant.getName()));
                participantElement.appendChild(name);

                Element email = doc.createElement("Email");
                email.appendChild(doc.createTextNode(participant.getEmail()));
                participantElement.appendChild(email);

                Element organization = doc.createElement("Organization");
                organization.appendChild(doc.createTextNode(participant.getOrganization()));
                participantElement.appendChild(organization);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(filePath);
            transformer.transform(domSource, streamResult);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoteException("Error exporting to XML", e);
        }
    }

    @Override
    public void importFromXML(String filePath) throws RemoteException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(filePath);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("Participant");

            participants.clear();

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String name = element.getElementsByTagName("Name").item(0).getTextContent();
                    String email = element.getElementsByTagName("Email").item(0).getTextContent();
                    String organization = element.getElementsByTagName("Organization").item(0).getTextContent();
                    participants.add(new Participant(name, email, organization));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoteException("Error importing from XML", e);
        }
    }

    @Override
    public List<Participant> getParticipants() throws RemoteException {
        return participants;
    }
}
