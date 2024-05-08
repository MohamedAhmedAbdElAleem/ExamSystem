package App.DHome;

import App.DBefore.DBeforeController;
import App.DExam.DExamController;
import App.DQABank.DQABankController;
import App.DStudent.DStudentController;
import App.DoctorLogin.DoctorLoginController;
import App.DoctorProfile.DoctorProfileController;
import App.Notification.NotificationController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLOutput;

public class DHomeController {
    @FXML
    private Label WelcomeText;
    @FXML
    private Button DoctorProfile;
    @FXML
    private Button Notification;
    @FXML
    private Button LogOutButton;
    @FXML
    private Button StudentsButton;
    @FXML
    private Button ExamsButton;
    @FXML
    private Button BackButton;
    @FXML
    private Button QBankButton;
    @FXML
    private DoctorLoginController doctorLoginController;
    private String id;

    public void setId(String id) {
        this.id = id;
    }

    private DBeforeController dBeforeController;
    public void setDBeforeController(DBeforeController dBeforeController) {
        this.dBeforeController = dBeforeController;
    }
    private String Username;
    public void setUsername(String username) {
        Username = username;
        WelcomeText.setText("Welcome "+username);
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
            DBeforeController loginController = fxmlLoader.getController();
            loginController.setUsername(Username);
            loginController.setSsn(ssn);
            loginController.setId(id);
            loginController.setDBeforeController(this);
            loginController.loadChoices();
            Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        };
    }
    private EventHandler<ActionEvent> StudentsButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/DStudent/DStudent.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            DStudentController dStudentController = fxmlLoader.getController();
            dStudentController.setUsername(Username);
            dStudentController.setSsn(ssn);
            dStudentController.setId(id);
            System.out.println("CourseId in DHomeController : "+CourseId);
            dStudentController.setCourseId(CourseId);
            dStudentController.setSelectedCourse(selectedCourse);
            dStudentController.setDHomeController(this);
            Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
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
            DExamController loginController = fxmlLoader.getController();
            loginController.setUsername(Username);
            loginController.setSsn(ssn);
            loginController.setId(id);
            loginController.setCourseId(CourseId);
            loginController.setSelectedCourse(selectedCourse);
            loginController.setDHomeController(this);
            Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
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
            dqaBankController.setUsername(Username);
            dqaBankController.setSsn(ssn);
            dqaBankController.setId(id);
            dqaBankController.setCourseId(CourseId);
            dqaBankController.setSelectedCourse(selectedCourse);
            dqaBankController.setDHomeController(this);
            Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        };
    }
    public void initialize() {
        LogOutButton.setOnAction(LogOutButtonClicked());
        StudentsButton.setOnAction(StudentsButtonClicked());
        ExamsButton.setOnAction(ExamsButtonClicked());
        BackButton.setOnAction(BackButtonClicked());
        QBankButton.setOnAction(QBankButtonClicked());
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
            loginController.setDHomeController(this);
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
            loginController.setId(id);
            loginController.setSsn(ssn);
            loginController.setDHomeController(this);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL); // This line makes the new window modal
            stage.setScene(scene);
            stage.showAndWait();
        };
    }

    private DStudentController dStudentController;
    public void setDStudentController(DStudentController dStudentController) {
        this.dStudentController = dStudentController;
    }


    private DQABankController dqaBankController;
    public void setDStudentController(DQABankController dqaBankController) {
        this.dqaBankController = dqaBankController;
    }

    private DExamController dExamController;
    public void setDStudentController(DExamController dExamController) {
        this.dExamController = dExamController;
    }
    private String selectedCourse;
    public void setSelectedCourse(String choice) {
        selectedCourse = choice;
    }

    public void setDoctorLoginController(DoctorLoginController doctorLoginController) {
        this.doctorLoginController = doctorLoginController;}
    private String CourseId;
    public void setCourseId(String courseid) {
        this.CourseId = courseid;
//        System.out.println("CourseId in DHomeController : "+CourseId);
    }
    private String ssn;
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
}
