public class Main {
    public static void main(String[] args) {
        // Запуск сервера
        new Thread(() -> MetroServer.main(null)).start();

        // Запуск клієнта
        MetroClient.main(null);
    }
}