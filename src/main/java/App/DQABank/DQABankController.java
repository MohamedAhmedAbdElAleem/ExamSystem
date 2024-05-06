package App.DQABank;

import App.AddQuestion.AddQuestionController;
import App.DBefore.DBeforeController;
import App.DExam.DExamController;
import App.DHome.DHomeController;
import App.DStudent.DStudentController;
import App.DoctorLogin.DoctorLoginController;
import App.DoctorProfile.DoctorProfileController;
import App.EditQuestion.EditQuestionController;
import App.Notification.NotificationController;
import App.QuestionID.QuestionIDController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class DQABankController {
    @FXML
    private Button DoctorProfile;
    @FXML
    private Button Notification;
    @FXML
    private Button AddQuestion;
    @FXML
    private Button EditQuestion;
    @FXML
    private Button DHomeButton;
    @FXML
    private Button StudentsButton;
    @FXML
    private Button ExamsButton;
    @FXML
    private Button BackButton;
    @FXML
    private Button LogOutButton;
    @FXML
    private Button DeleteQuestion;


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
    private EventHandler<ActionEvent> StudentsButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/DStudent/DStudent.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            DStudentController loginController = fxmlLoader.getController();
//            loginController.setUsername(Username);
            loginController.setDHomeController(this);
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
            loginController.setDStudentController(this);
            Stage stage = (Stage) DHomeButton.getScene().getWindow();
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
//            loginController.setUsername(Username);
            loginController.setDBeforeController(this);
            Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        };
    }
    private EventHandler<ActionEvent> DHomeButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/DHome/DHome.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            DHomeController loginController = fxmlLoader.getController();
            loginController.setDStudentController(this);
            Stage stage = (Stage) DHomeButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        };
    }
    public void initialize() {
        LogOutButton.setOnAction(LogOutButtonClicked());
        StudentsButton.setOnAction(StudentsButtonClicked());
        ExamsButton.setOnAction(ExamsButtonClicked());
        BackButton.setOnAction(BackButtonClicked());
        DHomeButton.setOnAction(DHomeButtonClicked());
        AddQuestion.setOnAction(AddQuestionButtonClicked());
        EditQuestion.setOnAction(EditQuestionButtonClicked());
        DeleteQuestion.setOnAction(DeleteQuestionButtonClicked());
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
            loginController.setDQABankController(this);
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
//           //loginController.setUsername(Username);
            loginController.setDQABankController(this);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL); // This line makes the new window modal
            stage.setScene(scene);
            stage.showAndWait();
        };
    }



    private EventHandler<ActionEvent> DeleteQuestionButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/QuestionID/QuestionID.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            QuestionIDController loginController = fxmlLoader.getController();
            loginController.setDQABankController(this);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL); // This line makes the new window modal
            stage.setScene(scene);
            stage.showAndWait();
        };
    }

    private EventHandler<ActionEvent> EditQuestionButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/EditQuestion/EditQuestion.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            EditQuestionController loginController = fxmlLoader.getController();
            loginController.setDQABankController(this);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL); // This line makes the new window modal
            stage.setScene(scene);
            stage.showAndWait();
        };
    }

    private EventHandler<ActionEvent> AddQuestionButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/AddQuestion/AddQuestion.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            AddQuestionController loginController = fxmlLoader.getController();
            loginController.setDQABankController(this);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL); // This line makes the new window modal
            stage.setScene(scene);
            stage.showAndWait();
        };
    }

    private DHomeController dHomeController;
    public void setDHomeController(DHomeController dHomeController) {
        this.dHomeController = dHomeController;
    }
    private DStudentController dStudentController;
    public void setDStudentController(DStudentController dStudentController) {
        this.dStudentController = dStudentController;
    }

    private DExamController dExamController;
    public void setDStudentController(DExamController dExamController) {
        this.dExamController = dExamController;
    }
}
