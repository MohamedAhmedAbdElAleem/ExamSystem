package Main;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private ServerSocket serverSocket;
    private ExecutorService threadPool;
    private Connection connection;

    public Server() {
        try {
            serverSocket = new ServerSocket(8080);
            threadPool = Executors.newFixedThreadPool(10); // Use a fixed-size thread pool with 10 threads
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ali", "root", "ialy24405");
        } catch (IOException | SQLException e) {
            System.out.println("Error in server : "+e.getMessage());
        }
    }

    public void start() {
        System.out.println("Server started");
        try {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                // Handle client connection using a thread from the thread pool
                try {
                    threadPool.execute(new ServerHandler(clientSocket, connection));

                }catch (Exception e) {
                }
            }
        } catch (IOException e) {
            System.out.println("Error in server : "+e.getMessage());
        }
    }

    public void stop() {
        try {
            if (serverSocket != null) serverSocket.close();
            if (connection != null) connection.close();
            if (threadPool != null) threadPool.shutdown();
        } catch (IOException | SQLException e) {
            System.out.println("Error in server : "+e.getMessage());
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
}
