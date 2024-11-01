package Main;
import App.ErrorPopUp.ErrorPopUpController;
import App.SucessfulPopUp.SucessfulPopUpController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private static final int CONNECTION_TIMEOUT = 5000;

    Validation validation = new Validation();
    public Client() {
    try {
        socket = new Socket();
        socket.connect(socketAddress(), CONNECTION_TIMEOUT);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectInputStream = new ObjectInputStream(socket.getInputStream());
    } catch (ConnectException | SocketTimeoutException e) {
        validation.showErrorPopUp("Server is not Responding");
    } catch (IOException e) {
        validation.showErrorPopUp("Server is not Responding");
    }
}
private SocketAddress socketAddress() {
    return new InetSocketAddress("192.168.138.98", 8080);
}
    public void sendMessage(String message) {
        if (out != null) {
            out.println(message);
        } else {
            System.err.println("Error: PrintWriter 'out' is not initialized.");
        }
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
        while (true) {
            System.out.print("Enter message: ");
            String message = scanner.nextLine();
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
    private void showSuccessPopUp(String message) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/SucessfulPopUp/SucessfulPopUp.fxml"));
        Parent parent = null;
        try {
            parent = fxmlLoader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        SucessfulPopUpController sucessfulPopUpController = fxmlLoader.getController();
        sucessfulPopUpController.setSuccessfulMessage(message);

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    private void showErrorPopUp(String message) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/ErrorPopUp/ErrorPopUp.fxml"));
        Parent parent = null;
        try {
            parent = fxmlLoader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        ErrorPopUpController errorPopUpController = fxmlLoader.getController();
        errorPopUpController.setErrorMessage(message);

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();


    }

    public void editAdmin(String id, String name, String password) {
        sendMessage("editAdmin");
        sendMessage(id);
        sendMessage(name);
        sendMessage(password);
        String result = this.receiveMessage();
        if(result.equalsIgnoreCase("true"))
            showSuccessPopUp("Admin edited successfully");
        else
            showErrorPopUp("Error in editing Admin");
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
        Student student = null;
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

    public Object receiveObject() {
        try {
            return objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error in receiving object : " + e.getMessage());
            return null;
        }
    }

    public Student process(String getStudentDetails, String sid) {
        sendMessage(getStudentDetails);
        sendMessage(sid);
        String result = receiveMessage();
        if (result.equalsIgnoreCase("true")) {
            return getStudent();
        }
        return null;
    }

    public String process(String updateStudent, Student student) {
        sendMessage(updateStudent);
        sendMessage(student.getSname());
        sendMessage(student.getSregistrationNumber());
        sendMessage(student.getSssn());
        sendMessage(student.getSemail());
        sendMessage(student.getSid());
        String result = receiveMessage();
        return result;
    }

    public String UnAssignStudent(String id, String courseid) {
        sendMessage("UnAssignStudent");
        sendMessage(id);
        sendMessage(courseid);
        String responce = receiveMessage();
        return responce;
    }

    public String AssignStudent(String sid, String courseId) {
        sendMessage("AssignStudent");
        sendMessage(sid);
        sendMessage(courseId);
        String responce = receiveMessage();
        return responce;
    }

    public ObservableList<Question> getQuestions() {
        ObservableList<Question> questionList = FXCollections.observableArrayList();
        try {
            while (true) {
                Question line = null;
                try {
                    line = (Question) objectInputStream.readObject();
                } catch (IOException e) {
                    break;
                }
                questionList.add(line);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error in getting Questions: " + e.getMessage());
        }
        return questionList;
    }

    public String addTFQuestion(String question, String difficultyLevel, String lecture, String answer, String courseId) {
        sendMessage("addTFQuestion");
        sendMessage(question);
        sendMessage(difficultyLevel);
        sendMessage(lecture);
        sendMessage(answer);
        sendMessage(courseId);
        String result = receiveMessage();
        return result;
    }

    public String addMCQQuestion(String question, String difficultyLevel, String lecture, String answer, String option2, String option3, String option4, String courseId) {
        sendMessage("addMCQQuestion");
        sendMessage(question);
        sendMessage(difficultyLevel);
        sendMessage(lecture);
        sendMessage(answer);
        sendMessage(option2);
        sendMessage(option3);
        sendMessage(option4);
        sendMessage(courseId);
        String result = receiveMessage();
        return result;
    }

    public ObservableList<Course> ViewCoursesOfStudent(String sid) throws IOException {
        sendMessage("ViewCoursesOfStudent");
        sendMessage(sid);
        ObservableList<Course> courses = FXCollections.observableArrayList();
        Course line = null;

        try {
            while (true) {
                line = (Course) objectInputStream.readObject();
                courses.add(line);
            }
        } catch (IOException | ClassNotFoundException e) {
//            System.out.println("Error in getting Courses : " + e.getMessage());
        }

        return courses;
    }

    public List<Exam> getExams() {
        List<Exam> exams = new java.util.ArrayList<>();
        String message = receiveMessage();
        while (message.equalsIgnoreCase("true")) {
            String ExamId = receiveMessage();
            String name = receiveMessage();
            String startDate = receiveMessage();
            String duration = receiveMessage();
            String TotalMarks = receiveMessage();
            String lectureStart = receiveMessage();
            String lectureEnd = receiveMessage();
            String doctorId = receiveMessage();
            String MCQE = receiveMessage();
            String MCQM = receiveMessage();
            String MCQH = receiveMessage();
            String TFE = receiveMessage();
            String TFM = receiveMessage();
            String TFH = receiveMessage();
            String QbId = receiveMessage();
            String EasyMarks = receiveMessage();
            String MediumMarks = receiveMessage();
            String HardMarks = receiveMessage();
            String QuestionsIds = receiveMessage();
            Exam exam = new Exam();
            exam.setExamId(Integer.parseInt(ExamId));
            exam.setName(name);
            exam.setStartDate(LocalDateTime.parse(startDate));
            exam.setDuration(Double.parseDouble(duration));
            exam.setTotalMarks(Integer.parseInt(TotalMarks));
            exam.setLectureStart(Integer.parseInt(lectureStart));
            exam.setLectureEnd(Integer.parseInt(lectureEnd));
            exam.setDoctorId(Integer.parseInt(doctorId));
            exam.setMCQE(Integer.parseInt(MCQE));
            exam.setMCQM(Integer.parseInt(MCQM));
            exam.setMCQH(Integer.parseInt(MCQH));
            exam.setTFE(Integer.parseInt(TFE));
            exam.setTFM(Integer.parseInt(TFM));
            exam.setTFH(Integer.parseInt(TFH));
            exam.setQbId(Integer.parseInt(QbId));
            exam.setEasyMarks(Integer.parseInt(EasyMarks));
            exam.setMediumMarks(Integer.parseInt(MediumMarks));
            exam.setHardMarks(Integer.parseInt(HardMarks));
            exam.setQuestionsIds(QuestionsIds);
            exams.add(exam);
            message = receiveMessage();
        }
        return exams;
    }

    public void sendExam(Exam exam) {
        sendMessage(exam.getName());
        sendMessage(exam.getStartDate().toString());
        sendMessage(String.valueOf(exam.getDuration()));
        sendMessage(String.valueOf(exam.getTotalMarks()));
        sendMessage(String.valueOf(exam.getLectureStart()));
        sendMessage(String.valueOf(exam.getLectureEnd()));
        sendMessage(String.valueOf(exam.getDoctorId()));
        sendMessage(String.valueOf(exam.getMCQE()));
        sendMessage(String.valueOf(exam.getMCQM()));
        sendMessage(String.valueOf(exam.getMCQH()));
        sendMessage(String.valueOf(exam.getTFE()));
        sendMessage(String.valueOf(exam.getTFM()));
        sendMessage(String.valueOf(exam.getTFH()));
        sendMessage(String.valueOf(exam.getQbId()));
        sendMessage(String.valueOf(exam.getEasyMarks()));
        sendMessage(String.valueOf(exam.getMediumMarks()));
        sendMessage(String.valueOf(exam.getHardMarks()));
    }

    public ObservableList<Student> ViewStudentsOfCourse(int qbId) {
        sendMessage("ViewStudentsOfCourse");
        sendMessage(String.valueOf(qbId));
        ObservableList<Student> students = FXCollections.observableArrayList();
        Student line = null;
        try {
            while (true) {
                line = (Student) objectInputStream.readObject();
                students.add(line);
            }
        } catch (IOException | ClassNotFoundException e) {
//            System.out.println("Error in getting Students : " + e.getMessage());
        }
        return students;
    }

    public ObservableList<Question> getQuestionsOfExam() {
        ObservableList<Question> questions = FXCollections.observableArrayList();
        Question line = null;
        try {
            while (true) {
                line = (Question) objectInputStream.readObject();
                questions.add(line);
            }
        } catch (IOException | ClassNotFoundException e) {
//            System.out.println("Error in getting Questions : " + e.getMessage());
        }
        return questions;
    }

    public ObservableList<Results> getResultsOfStudent() {
        ObservableList<Results> results = FXCollections.observableArrayList();
        Results line = null;
        try {
            while (true) {
                line = (Results) objectInputStream.readObject();
                results.add(line);
            }
        } catch (IOException | ClassNotFoundException e) {
//            System.out.println("Error in getting Results : " + e.getMessage());
        }
        return results;
    }

    public ObservableList<Exam> getExamsOfStudent(String sid, String cid) {
        sendMessage("getExamsOfStudent");
        sendMessage(sid);
        sendMessage(cid);
        ObservableList<Exam> exams = FXCollections.observableArrayList();
        Exam line = null;
        try {
            while (true) {
                line = (Exam) objectInputStream.readObject();
                exams.add(line);
            }
        } catch (IOException | ClassNotFoundException e) {
//            System.out.println("Error in getting Exams : " + e.getMessage());
        }
        return exams;
    }

}
