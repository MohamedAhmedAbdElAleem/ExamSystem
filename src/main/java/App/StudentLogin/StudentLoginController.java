package App.StudentLogin;
import App.ErrorPopUp.ErrorPopUpController;
import App.GetPassword.GetPasswordController;
import App.SBefore.SBeforeController;
import App.SExams.SExamsController;
import App.SHome.SHomeController;
import App.SResults.SResultsController;
import App.SucessfulPopUp.SucessfulPopUpController;
import App.Welcome.WelcomeController;
import Main.Student;
import Main.Validation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import Main.Client;
import App.Welcome.WelcomeController;
import javafx.stage.Stage;

import java.io.IOException;

public class StudentLoginController {
    private String Username;
    @FXML
    private TextField UID;
    @FXML
    private PasswordField UPassword;
    @FXML
    private Button LogInButton;
    @FXML
    private Button BackButton;
    @FXML
    private Button GetPassword;

    Validation validation = new Validation();

    public EventHandler<ActionEvent> LogInButtonClicked() {
        return e -> {
            String id = UID.getText();
            String password = UPassword.getText();
            // Send the username and password to the server
            Client client = new Client();
            client.sendMessage("login");
            client.sendMessage("Student");
            client.sendMessage(id);
            client.sendMessage(password);
            String message = client.receiveMessage();
            if(message.equalsIgnoreCase("true")){
                Student student = client.getStudent();
                validation.showSuccessPopUp("Student Login Successful");
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/SBefore/SBefore.fxml")); // Reuse fxmlLoader
                Scene scene = null;
                Stage stage = null;
                try {
                    scene = new Scene(fxmlLoader.load()); // Reuse scene
                } catch (IOException ex) {
                    System.out.println("Error in loading scene : "+ex.getMessage());
                }
                SBeforeController loginController = fxmlLoader.getController();
//                loginController.setUsername(username1);
                loginController.setStudent(student);
                loginController.setStudentLoginController(this);
                stage = (Stage) ((Node)e.getSource()).getScene().getWindow(); // Reuse stage
                stage.setScene(scene);
                stage.show();
            }
            else {
                validation.showErrorPopUp("Invalid Username or Password");
            }
            client.close();
        };
    }
    @FXML
    public void initialize() {
        LogInButton.setOnAction(LogInButtonClicked());
        BackButton.setOnAction(BackButtonClicked());
        GetPassword.setOnAction(GetPasswordButtonClicked());
    }

    private EventHandler<ActionEvent> GetPasswordButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/GetPassword/GetPassword.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            GetPasswordController loginController = fxmlLoader.getController();
            loginController.setLoginController(this);
            Stage stage = new Stage();
            stage.initModality(javafx.stage.Modality.APPLICATION_MODAL); // This line makes the new window modal
            stage.setScene(scene);
            stage.showAndWait();
        };
    }

    public EventHandler<ActionEvent> BackButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/Welcome/Welcome.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            WelcomeController loginController = fxmlLoader.getController();
            loginController.setLoginController(this);
            Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        };
    }
    private WelcomeController welcomeController;
    public void setWelcomeController(WelcomeController welcomeController) {
        this.welcomeController = welcomeController;
    }
    private SBeforeController sBeforeController;
    public void setSBeforecontroller(SBeforeController sBeforeController) {
        this.sBeforeController = sBeforeController;
    }
    private SHomeController sHomeController;
    public void setSBeforecontroller(SHomeController sHomeController) {
        this.sHomeController = sHomeController;
    }
    private SExamsController sExamsController;
    public void setSBeforecontroller(SExamsController sExamsController) {
        this.sExamsController = sExamsController;
    }
    private SResultsController sResultsController;
    public void setSBeforecontroller(SResultsController sResultsController) {
        this.sResultsController = sResultsController;
    }
}
