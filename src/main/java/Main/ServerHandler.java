package Main;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class ServerHandler implements Runnable {
    private Socket clientSocket;
    private Connection connection;

    public ServerHandler(Socket clientSocket, Connection connection) {
        this.clientSocket = clientSocket;
        this.connection = connection;
    }

    @Override
    public void run() {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                BufferedReader reader2 = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            String input;
            System.out.println("Client connected");
            // Read client input
            while (true)
            {
                input = reader.readLine();
                if (input == null || "exit".equalsIgnoreCase(input)) {
                    System.out.println("Client disconnected");
                    break;
                }
                if(input.equalsIgnoreCase("LogIn")) {
                    String username = reader.readLine();
                    String password = reader.readLine();
                    String output = LogIn(username, password);
                    writer.println(output);
                    System.out.println(output + " for user : "+username);
                }else if (input.equalsIgnoreCase("register"))
                {
                    String username = reader.readLine();
                    String password = reader.readLine();
                    String Status = reader.readLine();
                    if(IsAlreadyRegistered(username)){
                        writer.println("User already registered");
                        continue;
                    }
                    String output = register(username, password, Status);
                    System.out.println("User registered : "+username);
                    writer.println("User registered");

                } else{
                    String output = processInput(input);
                    System.out.println("message received : "+input);
                    writer.println(output);
                }
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (clientSocket != null) clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String processInput(String input) throws SQLException {
        return input;
    }
    private String LogIn(String username,String password) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE UName = '" + username + "' AND UPassword = '" + password + "'");
        if (resultSet.next()) {
            return "Login Successful state : "+resultSet.getString("status");
        } else {
            return "Login Failed";
        }

    }
    private String register(String username,String password,String Status) throws SQLException {
        System.out.println("Register request for user : "+username);
        Statement statement = connection.createStatement();
        statement.executeUpdate("INSERT INTO users (UName, UPassword, status) VALUES ('" + username + "', '" + password + "', '" + Status + "')");
        return "User registered";
    }
    private boolean IsAlreadyRegistered(String username) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE UName = '" + username + "'");
        return resultSet.next();
    }
}
