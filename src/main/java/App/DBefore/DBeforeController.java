package App.DBefore;

import App.DExam.DExamController;
import App.DHome.DHomeController;
import App.DQABank.DQABankController;
import App.DStudent.DStudentController;
import App.DoctorLogin.DoctorLoginController;
import App.DoctorProfile.DoctorProfileController;
import App.Notification.NotificationController;
import Main.Client;
import Main.Course;
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


public class DBeforeController {
    @FXML
    private Button DoctorProfile;
    @FXML
    private Button Notification;
    @FXML
    private Button ContinueButton;
    @FXML
    private Button LogOutButton;
    @FXML
    private ComboBox<String> choice1;
    @FXML
    private Label WelcomeText;
    private String Id;
    private ObservableList<Course> courses;
    private String Courseid;
    @FXML
    private Label DoctorNameLabel;
    @FXML
    private Label DoctorSsnLabel;
    @FXML
    private Label DoctorIdLabel;
    public void setID(String id) {
        this.Id = id;
//        this.DoctorIdLabel.setText("Doctor ID : "+id);
        loadChoices();
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
    private EventHandler<ActionEvent> ContinueButtonClicked() {
        return e -> {
            String choice = choice1.getValue();
//            System.out.println("Choice : "+choice);
            if(choice == null){
                return;
            }
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/DHome/DHome.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            DHomeController loginController = fxmlLoader.getController();
            loginController.setUsername(Username);
            loginController.setSsn(ssn);
            loginController.setId(Id);
            loginController.setSelectedCourse(choice);
            for (Course course : courses){
                if(course.getCname().equals(choice)){
                    Courseid = course.getCid();
                    break;
                }
            }
            loginController.setCourseId(Courseid);
            loginController.setDBeforeController(this);
            Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            loadChoices();
        };
    }
    public void loadChoices(){
        Client client = new Client();
        client.sendMessage("viewCoursesOfDoctor");
        client.sendMessage(Id);
        courses = null;
        courses = client.getCoursesOfDoctor();
        ObservableList<String> courseNames = FXCollections.observableArrayList();
        for (Course course : courses){
            courseNames.add(course.getCname());
        }
        choice1.setItems(courseNames);

    }
    @FXML
    public void initialize() {
        LogOutButton.setOnAction(LogOutButtonClicked());
        ContinueButton.setOnAction(ContinueButtonClicked());
        DoctorProfile.setOnAction(DoctorProfileButtonClicked());
        Notification.setOnAction(NotificationButtonClicked());

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
            loginController.setDBeforeController(this);
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
            DoctorProfileController loginController = fxmlLoader.getController();
             loginController.setUsername(Username);
            loginController.setSsn(ssn);
            loginController.setId(Id);
            loginController.setCourseId(Courseid);
            loginController.setDBeforeController(this);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL); // This line makes the new window modal
            stage.setScene(scene);
            stage.showAndWait();
        };
    }


    private DoctorLoginController doctorLoginController;

    private String Username;
    public void setUsername(String username) {
        this.Username = username;
//        this.DoctorNameLabel.setText("Doctor Name : "+username);
        WelcomeText.setText("Welcome "+username);
    }
    private DHomeController dHomeController;
    public void setDBeforeController(DHomeController dHomeController) {
        this.dHomeController = dHomeController;
    }

    private DStudentController dStudentController;
    public void setDBeforeController(DStudentController dStudentController) {
        this.dStudentController = dStudentController;
    }

    private DQABankController dqaBankController;
    public void setDBeforeController(DQABankController dqaBankController) {
        this.dqaBankController = dqaBankController;
    }

    private DExamController dExamController;
    public void setDBeforeController(DExamController dExamController) {
        this.dExamController = dExamController;
    }

    public void setDoctorLoginController(DoctorLoginController doctorLoginController) {
        this.doctorLoginController = doctorLoginController;
    }
    private String ssn;
    public void setSsn(String ssn) {
        this.ssn = ssn;
//        this.DoctorSsnLabel.setText("Doctor SSN : "+ssn);
    }

    public void setId(String id) {
        this.Id = id;
        loadChoices();
    }
}
