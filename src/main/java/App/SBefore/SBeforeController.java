package App.SBefore;

import App.DHome.DHomeController;
import App.Notification.NotificationController;
import App.SExams.SExamsController;
import App.SHome.SHomeController;
import App.SResults.SResultsController;
import App.StudentLogin.StudentLoginController;
import App.StudentProfile.StudentProfileController;
import Main.Client;
import Main.Course;
import Main.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class SBeforeController {
    @FXML
    private Button StudentProfile;
    @FXML
    private Button Notification;
    private String username;
    @FXML
    private Button LogOutButton;
    @FXML
    private Button ContinueButton;
    @FXML
    private ComboBox<String> choice1;
    @FXML
    private Label StudentWelcome;

    private EventHandler<ActionEvent> LogOutButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/StudentLogin/StudentLogin.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            StudentLoginController studentLoginController = fxmlLoader.getController();
            studentLoginController.setSBeforecontroller(this);
            Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        };
    }
    private EventHandler<ActionEvent> ContinueButtonClicked() {
        return e -> {
            String choice = choice1.getValue();
//            System.out.println("Choice : "+choice);
            if(choice == null){
                return;
            }
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/SHome/SHome.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            SHomeController sHomeController = fxmlLoader.getController();
            sHomeController.setStudent(student);
            sHomeController.setDBeforeController(this);
            Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            ViewCourses();
        };
    }
            @FXML
    public void initialize() {
//        choice1.setItems(FXCollections.observableArrayList("Advanced Programming Application", "Data Structure"));
        LogOutButton.setOnAction(LogOutButtonClicked());
        ContinueButton.setOnAction(ContinueButtonClicked());
        StudentProfile.setOnAction(StudentProfileButtonClicked());
        Notification.setOnAction(NotificationButtonClicked());
    }

    private void ViewCourses() {
        Client client = new Client();
        ObservableList<Course> courses = FXCollections.observableArrayList();
        try {
            courses = client.ViewCoursesOfStudent(student.getSid());
        } catch (IOException e) {
            System.out.println("Error in getting courses : "+e.getMessage());
        }
        ObservableList<String> courseNames = FXCollections.observableArrayList();
        for(Course course : courses){
            courseNames.add(course.getCname());
        }
        choice1.setItems(courseNames);
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
            loginController.setSBeforeController(this);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL); // This line makes the new window modal
            stage.setScene(scene);
            stage.showAndWait();
        };
    }

    private EventHandler<ActionEvent> StudentProfileButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/StudentProfile/StudentProfile.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            StudentProfileController loginController = fxmlLoader.getController();
            loginController.setStudent(student);
            loginController.setSBeforeController(this);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL); // This line makes the new window modal
            stage.setScene(scene);
            stage.showAndWait();
        };
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private StudentLoginController studentLoginController;
    public void setStudentLoginController(StudentLoginController studentLoginController) {
        this.studentLoginController = studentLoginController;
    }

    private SExamsController sExamsController;
    public void setStudentLoginController(SExamsController sExamsController) {
        this.sExamsController = sExamsController;
    }

    private SResultsController sResultsController;
    public void setStudentLoginController(SResultsController sResultsController) {
        this.sResultsController = sResultsController;
    }
    private Student student;
    public void setStudent(Student student) {
        this.student = student;
        ViewCourses();
        StudentWelcome.setText("Welcome "+student.getSname());
    }

    private DHomeController dHomeController;
    public void setStudentBeforeController(SHomeController sHomeController) {
        this.dHomeController = dHomeController;
    }
}
