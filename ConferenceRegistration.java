import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List; // Додайте цей оператор імпорту

public interface ConferenceRegistration extends Remote {
    void registerParticipant(Participant participant) throws RemoteException;
    void exportToXML(String filePath) throws RemoteException;
    void importFromXML(String filePath) throws RemoteException;
    List<Participant> getParticipants() throws RemoteException;
}

