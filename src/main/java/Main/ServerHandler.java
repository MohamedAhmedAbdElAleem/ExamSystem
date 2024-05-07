package Main;
import java.io.*;
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
    private Admin admin;
    private Doctor doctor;
    private Student student;
    private Course course;
    BufferedReader reader;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;
    PrintWriter writer;
    public  ServerHandler(Socket clientSocket, Connection connection) {
        this.clientSocket = clientSocket;
        this.connection = connection;
        try {
            // Initialize streams
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            writer = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void run() {
        try
        {
            String input;
            while (true) {
                input = reader.readLine();
                if (input == null || "exit".equalsIgnoreCase(input)) {
                    break;
                }
                if (input.equalsIgnoreCase("LogIn")) {
                    String Status = reader.readLine();
                    String id = reader.readLine();
                    String password = reader.readLine();
                    logIn(Status, id, password);
                } else if (input.equalsIgnoreCase("register")) {
                    String username = reader.readLine();
                    String password = reader.readLine();
                    String Status = reader.readLine();
                } else if (input.equalsIgnoreCase("getAdminDashBoardNumbers")) {
                    getAdminDashBoardNumbers();
                } else if (input.equalsIgnoreCase("getAdmins")) {
                    getAdmins();
                } else if (input.equalsIgnoreCase("getDoctors")) {
                    getDoctors();
                } else if (input.equalsIgnoreCase("getStudents")) {
                    getStudents();
                } else if (input.equalsIgnoreCase("addAdmin")) {
                    addAdmin();
                } else if(input.equalsIgnoreCase("checkAdminId")){
                    CheckAdminId();
                }else if (input.equalsIgnoreCase("checkAdminId2")){
                    CheckAdminId2();
                }else if (input.equalsIgnoreCase("deleteAdmin")) {
                    DeleteAdmin();
                }else if (input.equalsIgnoreCase("editAdmin")){
                    EditAdmin();
                }else if (input.equalsIgnoreCase("addDoctor")) {
                    AddDoctor();
                } else if(input.equalsIgnoreCase("checkDoctorId")) {
                    CheckDoctorId();
                }else if (input.equalsIgnoreCase("deleteDoctor")) {
                    DeleteDoctor();
                }else if (input.equalsIgnoreCase("editDoctor")){
                    EditDoctor();
                }else if (input.equalsIgnoreCase("viewCourses"))
                {
                    ViewCourses();
                }else if (input.equalsIgnoreCase("viewCoursesOfDoctor"))
                {
                    ViewCoursesOfDoctor();
                }else if (input.equalsIgnoreCase("addCourse"))
                {
                    AddCourse();
                }else if (input.equalsIgnoreCase("checkCourseId"))
                {
                    CheckCourseId();
                }else if (input.equalsIgnoreCase("deleteCourse"))
                {
                    DeleteCourse();
                }else if (input.equalsIgnoreCase("GetStudentsOfCourse")) {
                    GetStudentsOfCourse();
                }else if (input.equalsIgnoreCase("addStudent")) {
                    AddStudent();
                }
                else{
                    String output = processInput(input);
                    System.out.println("message received : "+input);
                    writer.println(output);
                }
            }
        } catch (IOException | SQLException e) {
//            System.out.println("Error in server Handler: "+e.getMessage());
        } finally {
            try {
                if (clientSocket != null) clientSocket.close();
            } catch (IOException e) {
//                System.out.println("Error in server Handler: "+e.getMessage());
            }
        }
    }

    private void AddStudent() {
        try {
            String name = reader.readLine();
            String SregistrationNumber = reader.readLine();
            String ssn = reader.readLine();
            String email = reader.readLine();
            // Insert student into students table
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO students (Sname, Sssn, Semail,SregistrationNumber) VALUES ('" + name + "','" + ssn + "','" + email + "','"+SregistrationNumber+"')", Statement.RETURN_GENERATED_KEYS);
            // Retrieve the generated student ID
            writer.println("true"); // Signal successful student insertion to the client
            ResultSet generatedKeys = statement.getGeneratedKeys();
            int newSid = -1; // Default value if the student ID retrieval fails
            if (generatedKeys.next()) {
                newSid = generatedKeys.getInt(1);
            }

            // Read the CourseID from the client
            String courseID = reader.readLine();

            // Insert student-course enrollment into enroll table
            statement.executeUpdate("INSERT INTO enroll (StudentstID, CoursesID) VALUES ('" + newSid + "','" + courseID + "')");

            writer.println("true"); // Signal successful enrollment to the client
        } catch (IOException | SQLException e) {
            System.out.println("Error in addStudent : " + e.getMessage());
            writer.println("false"); // Signal failure to the client
        }

    }

    private void GetStudentsOfCourse() {
        String id = null;
        try {
            id = reader.readLine();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM students WHERE Sid IN (SELECT StudentstID FROM enroll WHERE CoursesID = '"+id+"')");
            while (resultSet.next())
            {
                student = new Student();
                student.setSid(resultSet.getString("Sid"));
                student.setSname(resultSet.getString("Sname"));
                student.setSpassword(resultSet.getString("Spassword"));
                student.setSssn(resultSet.getString("Sssn"));
                student.setSemail(resultSet.getString("Semail"));
                student.setSregistrationNumber(resultSet.getString("SregistrationNumber"));
                objectOutputStream.writeObject(student);
//
//                writer.println(resultSet.getString("Sid"));
//                writer.println(resultSet.getString("Sname"));
//                writer.println(resultSet.getString("Spassword"));
            }
            writer.println("end");
        } catch (IOException | SQLException e) {
            System.out.println("Error in getStudentsOfCourse : "+e.getMessage());
        }
    }

    private synchronized void DeleteCourse() throws IOException {
        String id = reader.readLine();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM courses WHERE Cid = '"+id+"'");
            writer.println("true");
        } catch (SQLException e) {
            System.out.println("Error in deleteCourse : "+e.getMessage());
            writer.println("false");
        }
    }

    private synchronized void CheckCourseId() throws IOException {
        String id = reader.readLine();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM courses WHERE Cid = '"+id+"'");
            if (resultSet.next())
            {
                writer.println("true");
            }else{
                writer.println("false");
            }
        } catch (SQLException e) {
            System.out.println("Error in checkCourseId : "+e.getMessage());
        }
    }

    private synchronized void AddCourse() throws IOException {
        String Cname = reader.readLine();
        String CcreditHours = reader.readLine();
        String DocID = reader.readLine();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM doctors WHERE Did = '" + DocID + "'");
            if (resultSet.next()) {
                try {
                    statement = connection.createStatement();
                    statement.executeUpdate("INSERT INTO courses (Cname,CcreditHours,DocID) VALUES ('"+Cname+"','"+CcreditHours+"','"+DocID+"')");
                    writer.println("true");
                } catch (SQLException e) {
                    System.out.println("Error in addCourse : "+e.getMessage());
                    writer.println("false");
                }
            } else {
                writer.println("false");
            }
        } catch (SQLException e) {
            System.out.println("Error in checkDoctorId : " + e.getMessage());
        }

    }

    private synchronized void ViewCoursesOfDoctor() throws IOException {
        String id = reader.readLine();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM courses WHERE DocID = '"+id+"'");
            while (resultSet.next())
            {
                course = new Course();
                course.setCid(resultSet.getString("Cid"));
                course.setCname(resultSet.getString("Cname"));
                course.setCcreditHours(resultSet.getString("CcreditHours"));
                course.setDocID(resultSet.getString("DocID"));
                objectOutputStream.writeObject(course);
            }
            writer.println("end");
        } catch (SQLException e) {
            System.out.println("Error in viewCoursesOfDoctor : "+e.getMessage());
        }
    }

    private synchronized void ViewCourses() throws SQLException, IOException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM courses");
        while (resultSet.next())
        {
            course = new Course();
            course.setCid(resultSet.getString("Cid"));
            course.setCname(resultSet.getString("Cname"));
            course.setCcreditHours(resultSet.getString("CcreditHours"));
            course.setDocID(resultSet.getString("DocID"));
            objectOutputStream.writeObject(course);
        }
        writer.println("end");
    }

    private synchronized void EditDoctor() throws IOException {
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
    }

    private synchronized void DeleteDoctor() throws IOException {
        String id = reader.readLine();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM doctors WHERE Did = '" + id + "'");
            writer.println("Doctor deleted successfully");
        } catch (SQLException e) {
            System.out.println("Error in deleteDoctor : " + e.getMessage());
        }
    }

    private synchronized void CheckDoctorId() throws IOException {
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
    }

    private synchronized void AddDoctor() {
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
    }

    private synchronized void EditAdmin() throws IOException {
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
    }

    private synchronized void DeleteAdmin() throws IOException {
        String id = reader.readLine();
        System.out.println("deleteAdmin : "+id);
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM admins WHERE Aid = '" + id + "'");
            writer.println("Admin deleted successfully");
        } catch (SQLException e) {
            System.out.println("Error in deleteAdmin : " + e.getMessage());
        }
    }

    private synchronized void CheckAdminId2() throws IOException {
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
    }

    private synchronized void CheckAdminId() throws IOException {
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

    }

    private synchronized void addAdmin() {
        try {
            String name = reader.readLine();
            String password = reader.readLine();
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO admins (Aname,Apassword) VALUES ('" + name + "','" + password + "')");
            writer.println("Admin added successfully");
        } catch (IOException | SQLException e) {
            System.out.println("Error in addAdmin : " + e.getMessage());
        }
    }

    private synchronized void getStudents() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM students");
        while (resultSet.next()) {
            writer.println(resultSet.getString("Sid"));
            writer.println(resultSet.getString("Sname"));
            writer.println(resultSet.getString("Spassword"));
        }
        writer.println("end");
    }

    private synchronized void getDoctors() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM doctors");
        while (resultSet.next()) {
            writer.println(resultSet.getString("Did"));
            writer.println(resultSet.getString("Dname"));
            writer.println(resultSet.getString("Dpassword"));
            writer.println(resultSet.getString("Dssn"));
        }
        writer.println("end");
    }

    private synchronized void getAdmins() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM admins");
        while (resultSet.next()) {
            writer.println(resultSet.getString("Aid"));
            writer.println(resultSet.getString("Aname"));
        }
        writer.println("end");
    }

    private synchronized void logIn(String status, String id, String password) throws IOException {
        if (status.equalsIgnoreCase("Admin")) {
            boolean output = AdminLogIn(id, password);
            writer.println(output);
            if (output)
                writer.println(username1);
        } else if (status.equalsIgnoreCase("Doctor")) {
            boolean output = DoctorLogin(id, password);
            writer.println(output);
            if (output) {
//                System.out.println("Doctor : "+doctor.getDname());
//                System.out.println("Doctor : "+doctor.getDssn());

                writer.println(doctor.getDname());
                writer.println(doctor.getDssn());
            }
        } else if (status.equalsIgnoreCase("Student")) {

            boolean output = StudentLogin(id, password);
            writer.println(output);
            if (output)
            {
//                System.out.println("Student : "+student.getSname());
//                System.out.println("Student : "+student.getSssn());
//                System.out.println("Student : "+student.getSemail());
//                System.out.println("Student : "+student.getSregistrationNumber());
                objectOutputStream.writeObject(student);
            }
        }
    }

    private synchronized boolean StudentLogin(String id, String password) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM students WHERE Sid = '"+id+"' AND Spassword = '"+password+"'");
            if (resultSet.next())
            {
                student = new Student();
                student.setSid(resultSet.getString("Sid"));
                student.setSname(resultSet.getString("Sname"));
                student.setSpassword(resultSet.getString("Spassword"));
                student.setSssn(resultSet.getString("Sssn"));
                student.setSemail(resultSet.getString("Semail"));
                student.setSregistrationNumber(resultSet.getString("SregistrationNumber"));
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error in StudentLogin : "+e.getMessage());
        }
        return false;
    }

    private synchronized boolean DoctorLogin(String id, String password) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM doctors WHERE Did = '"+id+"' AND Dpassword = '"+password+"'");
            if (resultSet.next())
            {
                doctor = new Doctor();
                doctor.setDid(resultSet.getString("Did"));
                doctor.setDname(resultSet.getString("Dname"));
                doctor.setDpassword(resultSet.getString("Dpassword"));
                doctor.setDssn(resultSet.getString("Dssn"));
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error in DoctorLogin : "+e.getMessage());
        }
        return false;
    }

    private synchronized boolean AdminLogIn(String id, String password) {
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

    private synchronized String processInput(String input) throws SQLException {
        return input;
    }

    int AdminsNumber;
    int DoctorsNumber;
    int StudentsNumber;
    private synchronized void getAdminDashBoardNumbers() throws SQLException {
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
        writer.println(AdminsNumber);
        writer.println(DoctorsNumber);
        writer.println(StudentsNumber);
    }
}
