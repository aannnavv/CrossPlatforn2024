import java.io.*;
import java.net.*;

public class MetroServer {
    private static final int PORT = 12345;
    private static CardDatabase cardDatabase = new CardDatabase();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server started on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);

                ClientHandler clientHandler = new ClientHandler(clientSocket);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;
        private final BufferedReader in;
        private final PrintWriter out;

        public ClientHandler(Socket socket) throws IOException {
            this.clientSocket = socket;
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new PrintWriter(socket.getOutputStream(), true);
        }

        @Override
        public void run() {
            try {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println("Received from client: " + inputLine);

                    String[] tokens = inputLine.split(" ");
                    String command = tokens[0];
                    String response = "";

                    switch (command) {
                        case "REGISTER":
                            String cardId = tokens[1];
                            boolean success = cardDatabase.registerCard(cardId);
                            response = success ? "Card registered successfully." : "Card already exists.";
                            break;
                        case "DEPOSIT":
                            // Implement deposit operation
                            double amount = Double.parseDouble(tokens[1]);
                            cardDatabase.deposit(tokens[2], amount);
                            response = "Deposit successful.";
                            break;
                        case "PAYMENT":
                            // Implement payment operation
                            double fare = Double.parseDouble(tokens[1]);
                            boolean paymentSuccess = cardDatabase.makePayment(tokens[2], fare);
                            response = paymentSuccess ? "Payment successful." : "Insufficient funds.";
                            break;
                        case "BALANCE":
                            // Implement balance inquiry operation
                            double balance = cardDatabase.getBalance(tokens[1]);
                            response = "Balance: " + balance;
                            break;
                        default:
                            response = "Unknown command.";
                    }

                    out.println(response);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class CardDatabase {
        // Implementation of card database
        public boolean registerCard(String cardId) {
            // Logic to register a new card
            return true; // For simplicity, always return true
        }

        public synchronized void deposit(String cardId, double amount) {
            // Logic to deposit amount to card
        }

        public synchronized boolean makePayment(String cardId, double fare) {
            // Logic to make payment for journey
            return true; // For simplicity, always return true
        }

        public synchronized double getBalance(String cardId) {
            // Logic to get balance of card
            return 0.0; // For simplicity, always return 0.0
        }
    }
}
