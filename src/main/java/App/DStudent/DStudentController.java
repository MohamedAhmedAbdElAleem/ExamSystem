package App.DStudent;

import App.AddStudent.AddStudentController;
import App.AssignStudent.AssignStudentController;
import App.DBefore.DBeforeController;
import App.DExam.DExamController;
import App.DHome.DHomeController;
import App.DID.DIDController;
import App.DQABank.DQABankController;
import App.DoctorLogin.DoctorLoginController;
import App.DoctorProfile.DoctorProfileController;
import App.Notification.NotificationController;
import App.SID.SIDController;
import Main.Client;
import Main.Student;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class DStudentController {
    private String username;
    @FXML
    private Button DoctorProfile;
    @FXML
    private Button Notification;
    @FXML
    private Button EditStudent;
    @FXML
    private Button DeleteStudent;
    @FXML
    private Button AddStudent;
    @FXML
    private Button DHomeButton;
    @FXML
    private Button QBankButton;
    @FXML
    private Button ExamsButton;
    @FXML
    private Button BackButton;
    @FXML
    private Button LogOutButton;
    @FXML
    private TableView<Student> StudentsView;
    @FXML
    private TableColumn<Student,String> ID;
    @FXML
    private TableColumn<Student,String> Name;
    @FXML
    private TableColumn<Student,String> Email;
    @FXML
    private TableColumn<Student,String> RegistrationNumber;
    @FXML
    private Button AssignButton;

    private EventHandler<ActionEvent> DHomeButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/DHome/DHome.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            DHomeController dHomeController = fxmlLoader.getController();
            dHomeController.setUsername(username);
            dHomeController.setSsn(ssn);
            dHomeController.setId(id);
            dHomeController.setCourseId(courseId);
            dHomeController.setSelectedCourse(selectedCourse);
            dHomeController.setDStudentController(this);
            Stage stage = (Stage) DHomeButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        };
    }
    private EventHandler<ActionEvent> QBankButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/DQABank/DQABank.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            DQABankController dqaBankController = fxmlLoader.getController();
            dqaBankController.setUsername(username);
            dqaBankController.setSsn(ssn);
            dqaBankController.setId(id);
            dqaBankController.setCourseId(courseId);
            dqaBankController.setSelectedCourse(selectedCourse);
            dqaBankController.setDStudentController(this);
            Stage stage = (Stage) DHomeButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        };
    }
    private EventHandler<ActionEvent> ExamsButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/DExam/DExam.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            DExamController dExamController = fxmlLoader.getController();
            dExamController.setUsername(username);
            dExamController.setSsn(ssn);
            dExamController.setId(id);
            dExamController.setCourseId(courseId);
            dExamController.setSelectedCourse(selectedCourse);
            dExamController.setDStudentController(this);
            Stage stage = (Stage) DHomeButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        };
    }
    private EventHandler<ActionEvent> LogOutButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/DoctorLogin/DoctorLogin.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            DoctorLoginController loginController = fxmlLoader.getController();

            loginController.setDBeforeController(this);
            Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        };
    }
    private EventHandler<ActionEvent> BackButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/DBefore/DBefore.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            DBeforeController dBeforeController = fxmlLoader.getController();
            dBeforeController.setUsername(username);
            dBeforeController.setSsn(ssn);
            dBeforeController.setId(id);
            dBeforeController.setDBeforeController(this);
            Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        };
    }

    private EventHandler<ActionEvent> AddStudentButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/AddStudent/AddStudent.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            AddStudentController addStudentController = fxmlLoader.getController();
            addStudentController.setCourseId(courseId);
            addStudentController.setDStudentController(this);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
            ViewStudents();
        };
    }

    public void initialize(){
        DHomeButton.setOnAction(DHomeButtonClicked());
        QBankButton.setOnAction(QBankButtonClicked());
        ExamsButton.setOnAction(ExamsButtonClicked());
        BackButton.setOnAction(BackButtonClicked());
        LogOutButton.setOnAction(LogOutButtonClicked());
        AddStudent.setOnAction(AddStudentButtonClicked());
        EditStudent.setOnAction(EditDeleteStudentButtonClicked("Edit"));
        DeleteStudent.setOnAction(EditDeleteStudentButtonClicked("Delete"));
        DoctorProfile.setOnAction(DoctorProfileButtonClicked());
        Notification.setOnAction(NotificationButtonClicked());
//        ViewStudents();
        ID.setCellValueFactory(new PropertyValueFactory<>("Sid"));
        Name.setCellValueFactory(new PropertyValueFactory<>("Sname"));
        Email.setCellValueFactory(new PropertyValueFactory<>("Semail"));
        RegistrationNumber.setCellValueFactory(new PropertyValueFactory<>("SregistrationNumber"));
        AssignButton.setOnAction(AssignButtonClicked());
    }

    private EventHandler<ActionEvent> AssignButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/AssignStudent/AssignStudent.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            AssignStudentController assignStudentController = fxmlLoader.getController();
            assignStudentController.setCourseId(courseId);
            assignStudentController.setDStudentController(this);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL); // This line makes the new window modal
            stage.setScene(scene);
            stage.showAndWait();
        };
    }

    private void ViewStudents() {
        Client client = new Client();
        client.sendMessage("GetStudentsOfCourse");
        client.sendMessage(courseId);
        ObservableList<Student> students = client.getStudents();
        StudentsView.setItems(students);
    }

    private EventHandler<ActionEvent> NotificationButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/Notification/Notification.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            NotificationController loginController = fxmlLoader.getController();
//           //loginController.setUsername(Username);
            loginController.setDStudentController(this);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL); // This line makes the new window modal
            stage.setScene(scene);
            stage.showAndWait();
        };
    }

    private EventHandler<ActionEvent> DoctorProfileButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/DoctorProfile/DoctorProfile.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            DoctorProfileController doctorProfileController = fxmlLoader.getController();
            doctorProfileController.setUsername(username);
            doctorProfileController.setSsn(ssn);
            doctorProfileController.setId(id);
            doctorProfileController.setDStudentController(this);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL); // This line makes the new window modal
            stage.setScene(scene);
            stage.showAndWait();
        };
    }
    private EventHandler<ActionEvent> EditDeleteStudentButtonClicked(String Process) {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/SID/SID.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
                SIDController sidController = fxmlLoader.getController();
                sidController.setUsername(username);
                sidController.setId(id);
                sidController.setProcess(Process);
                sidController.setDStudentController(this);
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        };
    }

    private DHomeController dHomeController;
    public void setDHomeController(DHomeController dHomeController) {
        this.dHomeController = dHomeController;
    }

    private DQABankController dqaBankController;
    public void setDHomeController(DQABankController dqaBankController) {
        this.dqaBankController = dqaBankController;
    }

    private DExamController dExamController;
    public void setDHomeController(DExamController dExamController) {
        this.dExamController = dExamController;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    private String id;
    public void setId(String id) {
        this.id = id;
    }
    private String selectedCourse;
    public void setSelectedCourse(String selectedCourse) {
        this.selectedCourse = selectedCourse;
//        System.out.println(selectedCourse);
    }
    private String courseId;
    public void setCourseId(String courseId) {
        this.courseId = courseId;
        ViewStudents();
//        System.out.println("CourseId in DStudentController : "+courseId);
    }

    private String ssn;
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
}

