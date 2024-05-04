package Main;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public Client() {
        try {
            socket = new Socket("localhost", 8080);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.out.println("Error in client : "+e.getMessage());
        }
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    public String receiveMessage() {
        try {
            return in.readLine();
        } catch (IOException e) {
            System.out.println("Error in client : "+e.getMessage());
            return null;
        }
    }

    public void close() {
        try {
            if (socket != null) socket.close();
            if (out != null) out.close();
            if (in != null) in.close();
        } catch (IOException e) {
            System.out.println("Error in Close the client : "+e.getMessage());
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        Scanner scanner = new Scanner(System.in);

        // Loop to send and receive messages
        while (true) {
            System.out.print("Enter message: ");
            String message = scanner.nextLine();

            // Exit condition
            if (message.equalsIgnoreCase("exit")) {
                client.close();
                break;
            }
            if (message.equalsIgnoreCase("login")) {
                client.sendMessage(message);
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                if (!validate(username, password)) {
                    System.out.println("Invalid input");
                    continue;
                }
                client.sendMessage(username);
                client.sendMessage(password);
                String response = client.receiveMessage();
                System.out.println("Server response: " + response);
                continue;
            }else if (message.equalsIgnoreCase("register")) {
                client.sendMessage(message);
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                System.out.print("Enter status: ");
                String status = scanner.nextLine();
                if (!validate(username, password)) {
                    System.out.println("Invalid input");
                    continue;
                }
                client.sendMessage(username);
                client.sendMessage(password);
                client.sendMessage(status);
                String response = client.receiveMessage();
                System.out.println("Server response: " + response);
                continue;
            }
            // Send message to the server
            client.sendMessage(message);
            // Receive response from the server
            String response = client.receiveMessage();
            System.out.println("Server response: " + response);
        }
    }
    private static boolean validate(String username, String password) {
        return username != null && !username.isEmpty() && password != null && !password.isEmpty()
                && !username.contains("'") && !password.contains("'");
    }

}
