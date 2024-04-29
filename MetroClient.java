import java.io.*;
import java.net.*;

public class MetroClient {
    private static final String SERVER_IP = "127.0.0.1"; // Server IP address
    private static final int SERVER_PORT = 12345; // Server port

    public static void main(String[] args) {
        try (
                Socket socket = new Socket(SERVER_IP, SERVER_PORT);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))
        ) {
            System.out.println("Connected to server.");

            String userInput;
            while ((userInput = consoleReader.readLine()) != null) {
                out.println(userInput);

                String response = in.readLine();
                System.out.println("Server response: " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
