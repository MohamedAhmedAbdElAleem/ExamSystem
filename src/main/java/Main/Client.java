package Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;


    public Client() {
        try {
            socket = new Socket("localhost", 8080);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
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

    public ObservableList<Admin> getAdmins() throws IOException {
        ObservableList<Admin> adminList = FXCollections.observableArrayList();
        String line = in.readLine();
        while (line != null && !line.equalsIgnoreCase("end")) {
            Admin admin = new Admin();
            admin.setAid(line); // Assume the first line is the Aid
            line = in.readLine(); // Read the next line
            if (line != null && !line.equalsIgnoreCase("end")) {
                admin.setAname(line); // Assume the second line is the Aname
                adminList.add(admin); // Add the admin to the list
                line = in.readLine(); // Read the next line
            }
        }
        return adminList;
    }

    public void addAdmin(String name, String password) {
        sendMessage("addAdmin");
        sendMessage(name);
        sendMessage(password);
        String result = this.receiveMessage();
        sendMessage("exit");

    }
    private String username1;
    private String password1;

    public String getUsername1() {
        return username1;
    }

    public String getPassword1() {
        return password1;
    }
    public boolean checkAdminId(String id) {
        sendMessage("checkAdminId");
        sendMessage(id);
        String result = this.receiveMessage();
        if (result.equalsIgnoreCase("true"))
        {
            username1 = this.receiveMessage();
            password1 = this.receiveMessage();

        }
        sendMessage("exit");
        return Boolean.parseBoolean(result);
    }

    public boolean checkAdminId2(String id) {
        sendMessage("checkAdminId2");
        sendMessage(id);
        String result = this.receiveMessage();
        return Boolean.parseBoolean(result);
    }

    public boolean deleteAdmin(String id) {
        sendMessage("deleteAdmin");
        sendMessage(id);
        String result = this.receiveMessage();
        System.out.println(result);
        sendMessage("exit");
        return Boolean.parseBoolean(result);
    }

    public void editAdmin(String id, String name, String password) {
        sendMessage("editAdmin");
        sendMessage(id);
        sendMessage(name);
        sendMessage(password);
        String result = this.receiveMessage();
        System.out.println(result);
    }

    public ObservableList<Doctor> getDoctors() {
        ObservableList<Doctor> doctorList = FXCollections.observableArrayList();
        String line = null;
        try {
            line = in.readLine();
        while (line != null && !line.equalsIgnoreCase("end")) {
            Doctor doctor = new Doctor();
            doctor.setDid(line); // Assume the first line is the Did
            line = in.readLine(); // Read the next line
            if (line != null && !line.equalsIgnoreCase("end")) {
                doctor.setDname(line); // Assume the second line is the Dname
                line = in.readLine(); // Read the next line
                if (line != null && !line.equalsIgnoreCase("end")) {
                    doctor.setDpassword(line); // Assume the third line is the Dpassword
                    line = in.readLine(); // Read the next line
                    if (line != null && !line.equalsIgnoreCase("end")) {
                        doctor.setDssn(line); // Assume the fourth line is the Dssn
                        doctorList.add(doctor); // Add the doctor to the list
                        line = in.readLine(); // Read the next line
                    }
                }
            }
        }
        } catch (IOException e) {
            System.out.println("Error in getting Doctors");
        }
        return doctorList;
    }
    public ObservableList<Course> getCourses() {
        ObservableList<Course> courseList = FXCollections.observableArrayList();
        try {
            while (true) {
                Course line = null;
                try {
                    line = (Course) objectInputStream.readObject();
                } catch (IOException e) {
                    break;
                }
                courseList.add(line);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error in getting Courses: " + e.getMessage());
        }
        return courseList;
    }

    public ObservableList<Course> getCoursesOfDoctor() {
        ObservableList<Course> courseList = FXCollections.observableArrayList();
        try {
            while (true) {

                Course line = null;
                try {
                    line = (Course) objectInputStream.readObject();
                } catch (IOException e) {
                    break;
                }
                courseList.add(line);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error in getting Courses: " + e.getMessage());
        }
        return courseList;
    }

    public ObservableList<Student> getStudents() {
        ObservableList<Student> studentList = FXCollections.observableArrayList();
        try {
            while (true) {
                Student line = null;
                try {
                    line = (Student) objectInputStream.readObject();
                } catch (IOException e) {
                    break;
                }
                studentList.add(line);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error in getting Students: " + e.getMessage());
        }
        return studentList;
    }

    public Student getStudent() {
        Student student = new Student();
        try {
            student = (Student) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error in getting Student: " + e.getMessage());
        }
        return student;
    }

    public String sendStudent(Student student, String courseid) {
        sendMessage(student.getSname());
        sendMessage(student.getSregistrationNumber());
        sendMessage(student.getSssn());
        sendMessage(student.getSemail());
        String message = receiveMessage();
        if (message.equalsIgnoreCase("true")) {
            sendMessage(courseid);
            if (receiveMessage().equalsIgnoreCase("true"))
                return "true";
            else
                return "false";
        }else{
            return "false";
        }

    }
}
