import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class ConferenceServer {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            ConferenceRegistrationImpl obj = new ConferenceRegistrationImpl();
            Naming.rebind("ConferenceRegistration", obj);
            System.out.println("Conference Registration Server is ready.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
