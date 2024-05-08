package Main;
import java.io.*;
import java.net.Socket;
import java.sql.*;
import java.util.Objects;
import java.util.Random;

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
                }else if (input.equalsIgnoreCase("getDoctor"))
                {
                    GetDoctor();
                }else if (input.equalsIgnoreCase("getStudentDetails"))
                {
                    GetStudent();
                }else if (input.equalsIgnoreCase("updateStudent"))
                {
                    UpdateStudent();
                }else if (input.equalsIgnoreCase("UnAssignStudent"))
                {
                    UnAssignStudent();
                }else if (input.equalsIgnoreCase("AssignStudent"))
                {
                    AssignStudent();
                }else if (input.equalsIgnoreCase("getQuestions"))
                {
                    GetQuestions();
                }else if (input.equalsIgnoreCase("addTFQuestion")) {
                    AddTFQuestion();
                } else if (input.equalsIgnoreCase("addMCQQuestion")) {
                    AddMCQQuestion();
                } else if (input.equalsIgnoreCase("checkQuestionID")) {
                    CheckQuestionID();
                }else if (input.equalsIgnoreCase("editMCQ")) {
                    EditMCQ();
                } else if (input.equalsIgnoreCase("getPassword")) {
                    getPassword();
                } else if (input.equalsIgnoreCase("changePassword")) {
                    changePassword();
                } else{
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

    private void changePassword() {
        try {
            String ID = reader.readLine();
            String SSN = reader.readLine();
            String newPassword = reader.readLine();
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE students SET Spassword = '"+newPassword+"' WHERE Sid = '"+ID+"' AND Sssn = '"+SSN+"'");
            writer.println("true");
        } catch (IOException | SQLException e) {
            System.out.println("Error in changePassword : "+e.getMessage());
            writer.println("false");
        }
    }

    private void getPassword() {
        try {
            String ID = reader.readLine();
            String SSN = reader.readLine();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM students WHERE Sid = '"+ID+"' AND Sssn = '"+SSN+"'");

            if (resultSet.next())
            {
                String currentPassword = resultSet.getString("Spassword");
                if (currentPassword.equals("7#Kd4&FpGqXy!3vPmZ@LwTnU2$r%hY8jN*5sE")) {
                    writer.println("true");
                    String newPassword = generatePassword(6);
                    statement.executeUpdate("UPDATE students SET Spassword = '"+newPassword+"' WHERE Sid = '"+ID+"' AND Sssn = '"+SSN+"'");
                    writer.println(newPassword);
                } else {
                    writer.println("false");
                }
            } else {
                writer.println("false");
            }
        } catch (IOException | SQLException e) {
            System.out.println("Error in getPassword : "+e.getMessage());
        }
    }

    private String generatePassword(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();
        Random rnd = new Random();
        while (password.length() < length) { // length of the random string.
            int index = (int) (rnd.nextFloat() * chars.length());
            password.append(chars.charAt(index));
        }
        return password.toString();
    }

    private void EditMCQ() {
        try {
            String questionID = reader.readLine();
            String courseId = reader.readLine();
            String question = reader.readLine();
            String option2 = reader.readLine();
            String option3 = reader.readLine();
            String option4 = reader.readLine();
            String correctOption = reader.readLine();
            String lecture = reader.readLine();
            String difficultyLevel = reader.readLine();
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE question_bank_"+courseId+" SET question = '"+question+"', lecture = '"+lecture+"', difficulty_level = '"+difficultyLevel+"' WHERE id = '"+questionID+"'");
            statement.executeUpdate("UPDATE mcq_"+courseId+" SET choice = '"+option2+"' WHERE question_id = '"+questionID+"' AND id = 1");
            statement.executeUpdate("UPDATE mcq_"+courseId+" SET choice = '"+option3+"' WHERE question_id = '"+questionID+"' AND id = 2");
            statement.executeUpdate("UPDATE mcq_"+courseId+" SET choice = '"+option4+"' WHERE question_id = '"+questionID+"' AND id = 3");
            statement.executeUpdate("UPDATE question_bank_"+courseId+" SET answer = '"+correctOption+"' WHERE id = '"+questionID+"'");
            writer.println("true");
        } catch (IOException | SQLException e) {
            System.out.println("Error in editMCQ : "+e.getMessage());
            writer.println("false");
        }
    }

    private void CheckQuestionID() {
        try {
            String questionID = reader.readLine();
            String courseID = reader.readLine();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM question_bank_"+courseID+" WHERE id = '"+questionID+"'");
            if (resultSet.next())
            {
                writer.println("true");
                writer.println(resultSet.getString("question"));
                writer.println(resultSet.getString("lecture"));
                writer.println(resultSet.getString("Qtype"));
                writer.println(resultSet.getString("answer"));
                writer.println(resultSet.getString("difficulty_level"));
                if(resultSet.getString("Qtype").equalsIgnoreCase("MCQ")) {
                    ResultSet resultSet1 = statement.executeQuery("SELECT * FROM mcq_" + courseID + " WHERE question_id = '" + questionID + "'");
                    while (resultSet1.next()) {
                        writer.println(resultSet1.getString("choice"));
                    }
                }

            }else{
                writer.println("false");
            }
        } catch (IOException | SQLException e) {
            System.out.println("Error in checkQuestionID : "+e.getMessage());
        }
    }

    private void AddMCQQuestion() {
        try {
            String question = reader.readLine();
            String difficultyLevel = reader.readLine();
            String lecture = reader.readLine();
            String answer = reader.readLine();
            String option2 = reader.readLine();
            String option3 = reader.readLine();
            String option4 = reader.readLine();
            String courseID = reader.readLine();
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO question_bank_"+courseID+" (question, lecture, Qtype, answer, difficulty_level) VALUES ('"+question+"','"+lecture+"','MCQ','"+answer+"','"+difficultyLevel+"')", Statement.RETURN_GENERATED_KEYS);
            ResultSet generatedKeys = statement.getGeneratedKeys();
            int questionID = -1;
            if (generatedKeys.next()) {
                questionID = generatedKeys.getInt(1);
            }
            statement.executeUpdate("INSERT INTO mcq_"+courseID+" (question_id, choice) VALUES ('"+questionID+"','"+option2+"')");
            statement.executeUpdate("INSERT INTO mcq_"+courseID+" (question_id, choice) VALUES ('"+questionID+"','"+option3+"')");
            statement.executeUpdate("INSERT INTO mcq_"+courseID+" (question_id, choice) VALUES ('"+questionID+"','"+option4+"')");
            writer.println("true");
        } catch (IOException | SQLException e) {
            System.out.println("Error in addMCQQuestion : "+e.getMessage());
            writer.println("false");
        }
    }

    private void AddTFQuestion() {
        try {
            String question = reader.readLine();
            String difficultyLevel = reader.readLine();
            String lecture = reader.readLine();
            String answer = reader.readLine();
            String courseID = reader.readLine();
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO question_bank_"+courseID+" (question, lecture, Qtype, answer, difficulty_level) VALUES ('"+question+"','"+lecture+"','TF','"+answer+"','"+difficultyLevel+"')");
            writer.println("true");
        } catch (IOException | SQLException e) {
            System.out.println("Error in addTFQuestion : "+e.getMessage());
            writer.println("false");
        }
    }

    private void GetQuestions() {
        try {
            String courseID = reader.readLine();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM question_bank_"+courseID);
            while (resultSet.next())
            {
                Question question = new Question();
                question.setQuestionId(resultSet.getInt("id"));
                question.setQuestion(resultSet.getString("question"));
                question.setLecture(resultSet.getString("lecture"));
                question.setQuestionType(resultSet.getString("Qtype"));
                question.setAnswer(resultSet.getString("answer"));
                question.setUsedBefore(resultSet.getBoolean("used"));
                question.setDifficultyLevel(resultSet.getString("difficulty_level"));
                objectOutputStream.writeObject(question);
            }
            writer.println("end");
        } catch (IOException | SQLException e) {
            System.out.println("Error in getQuestions : "+e.getMessage());
        }
    }

    private void AssignStudent() {
        try {
            String id = reader.readLine();
            String courseID = reader.readLine();
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO enroll (StudentstID, CoursesID) VALUES ('"+id+"','"+courseID+"')");
            writer.println("true");
        } catch (IOException | SQLException e) {
            System.out.println("Error in assignStudent : "+e.getMessage());
            writer.println("false");
        }
    }

    private void UnAssignStudent() {
        try {
            String id = reader.readLine();
            String courseID = reader.readLine();
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM enroll WHERE StudentstID = '"+id+"' AND CoursesID = '"+courseID+"'");
            writer.println("true");
        } catch (IOException | SQLException e) {
            System.out.println("Error in unAssignStudent : "+e.getMessage());
            writer.println("false");
        }
    }

    private void UpdateStudent() {
        try {
            String name = reader.readLine();
            String SregistrationNumber = reader.readLine();
            String ssn = reader.readLine();
            String email = reader.readLine();
            String id = reader.readLine();
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE students SET Sname = '"+name+"', Sssn = '"+ssn+"', Semail = '"+email+"', SregistrationNumber = '"+SregistrationNumber+"' WHERE Sid = '"+id+"'");
            writer.println("true");
        } catch (IOException | SQLException e) {
            System.out.println("Error in updateStudent : "+e.getMessage());
            writer.println("false");
        }
    }

    private void GetStudent() {
        String id = null;
        try {
            id = reader.readLine();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM students WHERE Sid = '"+id+"'");
            if (resultSet.next())
            {
                writer.println("true");
                student = new Student();
                student.setSid(resultSet.getString("Sid"));
                student.setSname(resultSet.getString("Sname"));
                student.setSpassword(resultSet.getString("Spassword"));
                student.setSssn(resultSet.getString("Sssn"));
                student.setSemail(resultSet.getString("Semail"));
                student.setSregistrationNumber(resultSet.getString("SregistrationNumber"));
                objectOutputStream.writeObject(student);
            }
        } catch (IOException | SQLException e) {
            System.out.println("Error in getStudent : "+e.getMessage());
            writer.println("false");
        }
    }

    private void GetDoctor() {
        String id = null;
        try {
            id = reader.readLine();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM doctors WHERE Did = '"+id+"'");
            if (resultSet.next())
            {
                writer.println("true");
                doctor = new Doctor();
                doctor.setDid(resultSet.getString("Did"));
                doctor.setDname(resultSet.getString("Dname"));
                doctor.setDpassword(resultSet.getString("Dpassword"));
                doctor.setDssn(resultSet.getString("Dssn"));
                objectOutputStream.writeObject(doctor);
            }
        } catch (IOException | SQLException e) {
            System.out.println("Error in getDoctor : "+e.getMessage());
            writer.println("false");
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
            // Check if the doctor with the provided ID exists
            PreparedStatement checkDoctorStatement = connection.prepareStatement("SELECT * FROM doctors WHERE Did = ?");
            checkDoctorStatement.setString(1, DocID);
            ResultSet doctorResult = checkDoctorStatement.executeQuery();

            if (doctorResult.next()) {
                // If the doctor exists, insert the new course
                PreparedStatement insertCourseStatement = connection.prepareStatement("INSERT INTO courses (Cname, CcreditHours, DocID) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                insertCourseStatement.setString(1, Cname);
                insertCourseStatement.setString(2, CcreditHours);
                insertCourseStatement.setString(3, DocID);
                int rowsAffected = insertCourseStatement.executeUpdate();

                if (rowsAffected > 0) {
                    // Signal successful course insertion to the client
                    writer.println("true");

                    // Retrieve the generated course ID
                    ResultSet generatedKeys = insertCourseStatement.getGeneratedKeys();
                    int courseId = -1; // Default value if course ID retrieval fails
                    if (generatedKeys.next()) {
                        courseId = generatedKeys.getInt(1);
                    }

                    // Create a new table for the question bank associated with the course
                    String createQuestionBankTableSQL = "CREATE TABLE question_bank_" + courseId + " ("
                            + "id INT AUTO_INCREMENT PRIMARY KEY,"
                            + "question VARCHAR(255) NOT NULL,"
                            + "lecture VARCHAR(255) NOT NULL,"
                            + "Qtype VARCHAR(255) NOT NULL,"
                            + "answer VARCHAR(255) NOT NULL,"
                            + "used BOOLEAN NOT NULL DEFAULT FALSE,"
                            + "difficulty_level VARCHAR(255) NOT NULL"
                            + ")";
                    Statement createTableStatement = connection.createStatement();
                    createTableStatement.executeUpdate(createQuestionBankTableSQL);

                    // Create a new table for the MCQs associated with the question bank
                    String createMCQTableSQL = "CREATE TABLE mcq_" + courseId + " ("
                            + "id INT AUTO_INCREMENT PRIMARY KEY,"
                            + "question_id INT NOT NULL,"
                            + "choice VARCHAR(255) NOT NULL,"
                            + "FOREIGN KEY (question_id) REFERENCES question_bank_" + courseId + "(id)"
                            + ")";
                    createTableStatement.executeUpdate(createMCQTableSQL);

                } else {
                    writer.println("false"); // Signal failure to insert course to the client
                }
            } else {
                writer.println("false"); // Signal that doctor with provided ID does not exist
            }
        } catch (SQLException e) {
            System.out.println("Error in addCourse : " + e.getMessage());
            writer.println("false"); // Signal failure to the client
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
            writer.println("Doctor not found");
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
            writer.println("true");
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
                writer.println(doctor.getDname());
                writer.println(doctor.getDssn());
            }
        } else if (status.equalsIgnoreCase("Student")) {

            boolean output = StudentLogin(id, password);
            writer.println(output);
            if (output)
            {

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
