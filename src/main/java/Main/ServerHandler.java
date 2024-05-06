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
    private String username1;

    public ServerHandler(Socket clientSocket, Connection connection) {
        this.clientSocket = clientSocket;
        this.connection = connection;
    }

    @Override
    public void run() {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//                BufferedReader reader2 = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            String input;
//            System.out.println("Client connected");
            // Read client input
            while (true) {
                input = reader.readLine();
                if (input == null || "exit".equalsIgnoreCase(input)) {
                    System.out.println("Client disconnected");
                    break;
                }
                if (input.equalsIgnoreCase("LogIn")) {
                    String Status = reader.readLine();
                    String id = reader.readLine();
                    String password = reader.readLine();
                    if (Status.equalsIgnoreCase("Admin")) {
                        boolean output = AdminLogIn(id, password);
                        writer.println(output);
                        if (output)
                            writer.println(username1);
                    } else if (Status.equalsIgnoreCase("Doctor")) {
                        boolean output = DoctorLogin(id, password);
                        writer.println(output);
                        if (output)
                            writer.println(username1);
                    } else if (Status.equalsIgnoreCase("Student")) {
                        boolean output = StudentLogin(id, password);
                        writer.println(output);
                        if (output)
                            writer.println(username1);
                    }
                } else if (input.equalsIgnoreCase("register")) {
                    String username = reader.readLine();
                    String password = reader.readLine();
                    String Status = reader.readLine();
                } else if (input.equalsIgnoreCase("getAdminDashBoardNumbers")) {
                    getAdminDashBoardNumbers();
                    writer.println(AdminsNumber);
                    writer.println(DoctorsNumber);
                    writer.println(StudentsNumber);
                } else if (input.equalsIgnoreCase("getAdmins")) {
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM admins");
                    while (resultSet.next()) {
                        writer.println(resultSet.getString("Aid"));
                        writer.println(resultSet.getString("Aname"));
                    }
                    writer.println("end");
                } else if (input.equalsIgnoreCase("getDoctors")) {
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM doctors");
                    while (resultSet.next()) {
                        writer.println(resultSet.getString("Did"));
                        writer.println(resultSet.getString("Dname"));
                        writer.println(resultSet.getString("Dpassword"));
                        writer.println(resultSet.getString("Dssn"));
                    }
                    writer.println("end");
                } else if (input.equalsIgnoreCase("getStudents")) {
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM students");
                    while (resultSet.next()) {
                        writer.println(resultSet.getString("Sid"));
                        writer.println(resultSet.getString("Sname"));
                        writer.println(resultSet.getString("Spassword"));
                    }
                    writer.println("end");
                } else if (input.equalsIgnoreCase("addAdmin")) {
                    System.out.println("addAdmin");
                    try {
                        String name = reader.readLine();
                        String password = reader.readLine();
                        Statement statement = connection.createStatement();
                        statement.executeUpdate("INSERT INTO admins (Aname,Apassword) VALUES ('" + name + "','" + password + "')");
                        writer.println("Admin added successfully");
                    } catch (IOException | SQLException e) {
                        System.out.println("Error in addAdmin : " + e.getMessage());
                    }
                } else if(input.equalsIgnoreCase("checkAdminId")){
                    String id = reader.readLine();
                    try {
                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery("SELECT * FROM admins WHERE Aid = '"+id+"'");
                        if (resultSet.next())
                        {
                            writer.println("true");
                            writer.println(resultSet.getString("Aname"));
                            writer.println(resultSet.getString("Apassword"));
                        }else{
                            writer.println("false");
                        }
                    } catch (SQLException e) {
                        System.out.println("Error in checkAdminId : "+e.getMessage());
                    }

                }else if (input.equalsIgnoreCase("checkAdminId2")){
                    String id = reader.readLine();
                    try {
                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery("SELECT * FROM admins WHERE Aid = '"+id+"'");
                        if (resultSet.next())
                        {
                            writer.println("true");
                        }else{
                            writer.println("false");
                        }
                    } catch (SQLException e) {
                        System.out.println("Error in checkAdminId2 : "+e.getMessage());
                    }

                }else if (input.equalsIgnoreCase("deleteAdmin")) {
                    String id = reader.readLine();
                    System.out.println("deleteAdmin : "+id);
                    try {
                        Statement statement = connection.createStatement();
                        statement.executeUpdate("DELETE FROM admins WHERE Aid = '" + id + "'");
                        writer.println("Admin deleted successfully");
                    } catch (SQLException e) {
                        System.out.println("Error in deleteAdmin : " + e.getMessage());
                    }
                }else if (input.equalsIgnoreCase("editAdmin")){
                    String id = reader.readLine();
                    String name = reader.readLine();
                    String password = reader.readLine();
                    try {
                        Statement statement = connection.createStatement();
                        statement.executeUpdate("UPDATE admins SET Aname = '"+name+"', Apassword = '"+password+"' WHERE Aid = '"+id+"'");
                        writer.println("Admin edited successfully");
                    } catch (SQLException e) {
                        System.out.println("Error in editAdmin : "+e.getMessage());
                    }
                }else if (input.equalsIgnoreCase("addDoctor")) {
                    System.out.println("addDoctor");
                    try {
                        String name = reader.readLine();
                        String password = reader.readLine();
                        String ssn = reader.readLine();
                        Statement statement = connection.createStatement();
                        statement.executeUpdate("INSERT INTO doctors (Dname,Dpassword,Dssn) VALUES ('" + name + "','" + password + "','" + ssn + "')");
                        writer.println("Doctor added successfully");
                    } catch (IOException | SQLException e) {
                        System.out.println("Error in addDoctor : " + e.getMessage());
                    }
                } else if(input.equalsIgnoreCase("checkDoctorId")) {
                    String id = reader.readLine();
                    try {
                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery("SELECT * FROM doctors WHERE Did = '" + id + "'");
                        if (resultSet.next()) {
                            writer.println("true");
                            writer.println(resultSet.getString("Dname"));
                            writer.println(resultSet.getString("Dpassword"));
                            writer.println(resultSet.getString("Dssn"));
                        } else {
                            writer.println("false");
                        }
                    } catch (SQLException e) {
                        System.out.println("Error in checkDoctorId : " + e.getMessage());
                    }
                }else if (input.equalsIgnoreCase("deleteDoctor")) {
                    String id = reader.readLine();
                    try {
                        Statement statement = connection.createStatement();
                        statement.executeUpdate("DELETE FROM doctors WHERE Did = '" + id + "'");
                        writer.println("Doctor deleted successfully");
                    } catch (SQLException e) {
                        System.out.println("Error in deleteDoctor : " + e.getMessage());
                    }
                }else if (input.equalsIgnoreCase("editDoctor")){
                    String id = reader.readLine();
                    String name = reader.readLine();
                    String password = reader.readLine();
                    String ssn = reader.readLine();
                    try {
                        Statement statement = connection.createStatement();
                        statement.executeUpdate("UPDATE doctors SET Dname = '"+name+"', Dpassword = '"+password+"', Dssn = '"+ssn+"' WHERE Did = '"+id+"'");
                        writer.println("Doctor edited successfully");
                    } catch (SQLException e) {
                        System.out.println("Error in editDoctor : "+e.getMessage());
                    }
                }else if (input.equalsIgnoreCase("viewCourses"))
                {
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM courses");
                    while (resultSet.next())
                    {
                        writer.println(resultSet.getString("Cid"));
                        writer.println(resultSet.getString("Cname"));
                        writer.println(resultSet.getString("CcreditHours"));
                        writer.println(resultSet.getString("DocID"));
                    }
                    writer.println("end");
                }else if (input.equalsIgnoreCase("viewCoursesOfDoctor"))
                {
                    String id = reader.readLine();
                    try {
                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery("SELECT * FROM courses WHERE DocID = '"+id+"'");
                        while (resultSet.next())
                        {
                            writer.println(resultSet.getString("Cid"));
                            writer.println(resultSet.getString("Cname"));
                            writer.println(resultSet.getString("CcreditHours"));
                            writer.println(resultSet.getString("DocID"));
                        }
                        writer.println("end");
                    } catch (SQLException e) {
                        System.out.println("Error in viewCoursesOfDoctor : "+e.getMessage());
                    }
                }else if (input.equalsIgnoreCase("addCourse"))
                {
                    String Cname = reader.readLine();
                    String CcreditHours = reader.readLine();
                    String DocID = reader.readLine();
                    try {
                        Statement statement = connection.createStatement();
                        statement.executeUpdate("INSERT INTO courses (Cname,CcreditHours,DocID) VALUES ('"+Cname+"','"+CcreditHours+"','"+DocID+"')");
                        writer.println("Course added successfully");
                    } catch (SQLException e) {
                        System.out.println("Error in addCourse : "+e.getMessage());
                    }
                }else if (input.equalsIgnoreCase("checkCourseId"))
                {
                    String id = reader.readLine();
                    try {
                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery("SELECT * FROM courses WHERE Cid = '"+id+"'");
                        if (resultSet.next())
                        {
                            writer.println("true");
//                            writer.println(resultSet.getString("Cname"));
//                            writer.println(resultSet.getString("CcreditHours"));
//                            writer.println(resultSet.getString("DocID"));
                        }else{
                            writer.println("false");
                        }
                    } catch (SQLException e) {
                        System.out.println("Error in checkCourseId : "+e.getMessage());
                    }
                }else if (input.equalsIgnoreCase("deleteCourse"))
                {
                    String id = reader.readLine();
                    try {
                        Statement statement = connection.createStatement();
                        statement.executeUpdate("DELETE FROM courses WHERE Cid = '"+id+"'");
                        writer.println("Course deleted successfully");
                    } catch (SQLException e) {
                        System.out.println("Error in deleteCourse : "+e.getMessage());
                    }
                } else{
                    String output = processInput(input);
                    System.out.println("message received : "+input);
                    writer.println(output);
                }
            }
        } catch (IOException | SQLException e) {
            System.out.println("Error in server Handler: "+e.getMessage());
        } finally {
            try {
                if (clientSocket != null) clientSocket.close();
            } catch (IOException e) {
                System.out.println("Error in server Handler: "+e.getMessage());
            }
        }
    }

    private boolean StudentLogin(String id, String password) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM students WHERE Sid = '"+id+"' AND Spassword = '"+password+"'");
            if (resultSet.next())
            {
                username1 = resultSet.getString("Sname");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error in StudentLogin : "+e.getMessage());
        }
        return false;
    }

    private boolean DoctorLogin(String id, String password) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM doctors WHERE Did = '"+id+"' AND Dpassword = '"+password+"'");
            if (resultSet.next())
            {
                username1 = resultSet.getString("Dname");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error in DoctorLogin : "+e.getMessage());
        }
        return false;
    }

    private boolean AdminLogIn(String id, String password) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM admins WHERE Aid = '"+id+"' AND Apassword = '"+password+"'");
            if (resultSet.next())
            {
                username1 = resultSet.getString("Aname");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error in AdminLogIn : "+e.getMessage());
        }
        return false;
    }

    private String processInput(String input) throws SQLException {
        return input;
    }

    int AdminsNumber;
    int DoctorsNumber;
    int StudentsNumber;
    private void getAdminDashBoardNumbers() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM admins");
        resultSet.next();
        AdminsNumber = resultSet.getInt(1);
        resultSet = statement.executeQuery("SELECT COUNT(*) FROM doctors");
        resultSet.next();
        DoctorsNumber = resultSet.getInt(1);
        resultSet = statement.executeQuery("SELECT COUNT(*) FROM students");
        resultSet.next();
        StudentsNumber = resultSet.getInt(1);
    }
}
