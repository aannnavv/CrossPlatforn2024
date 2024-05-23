import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

public class ComputeServerClient {

    public static void main(String[] args) {
        try {
            // Реєстрація віддаленого об'єкта
            Compute engine = new ComputeEngine();
            LocateRegistry.createRegistry(1099);
            Naming.rebind("Compute", engine);
            System.out.println("ComputeEngine bound in registry");

            // Клієнтська частина
            Compute comp = (Compute) Naming.lookup("rmi://localhost/Compute");
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the precision for Pi calculation: ");
            int precision = scanner.nextInt();

            double pi = comp.computePi(precision);
            System.out.println("Computed value of Pi: " + pi);

        } catch (Exception e) {
            System.err.println("ComputeServerClient exception:");
            e.printStackTrace();
        }
    }
}
