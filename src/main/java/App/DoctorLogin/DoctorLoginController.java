package App.DoctorLogin;

import App.AHome.AHomeController;
import App.DBefore.DBeforeController;
import App.DExam.DExamController;
import App.DHome.DHomeController;
import App.DQABank.DQABankController;
import App.DStudent.DStudentController;
import App.ErrorPopUp.ErrorPopUpController;
import App.SucessfulPopUp.SucessfulPopUpController;
import App.Welcome.WelcomeController;
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

public class DoctorLoginController {
    private String Username;

    @FXML
    private TextField UID;
    @FXML
    private PasswordField UPassword;
    @FXML
    private Button LogInButton;
    @FXML
    private Button BackButton;
    public EventHandler<ActionEvent> LogInButtonClicked() {
        return e -> {
            String id = UID.getText();
            String password = UPassword.getText();
            // Send the username and password to the server
            Client client = new Client();
            client.sendMessage("login");
            client.sendMessage("Doctor");
            client.sendMessage(id);
            client.sendMessage(password);
            String message = client.receiveMessage();
            if (message.equalsIgnoreCase("true")){
                String username1 = client.receiveMessage();
                String SSN = client.receiveMessage();
                Username = username1;

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/SucessfulPopUp/SucessfulPopUp.fxml"));
                Parent parent = null;
                try {
                    parent = fxmlLoader.load();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                SucessfulPopUpController sucessfulPopUpController = fxmlLoader.getController();
                sucessfulPopUpController.setSuccessfulMessage("Login Successful for user : "+username1);

                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                stage.initModality(javafx.stage.Modality.APPLICATION_MODAL); // Correct usage of Modality
                stage.setScene(scene);
                stage.showAndWait();

                fxmlLoader = new FXMLLoader(getClass().getResource("/App/DBefore/DBefore.fxml")); // Reuse fxmlLoader
                try {
                    scene = new Scene(fxmlLoader.load()); // Reuse scene
                } catch (IOException ex) {
                    System.out.println("Error in loading scene : "+ex.getMessage());
                }
                DBeforeController loginController = fxmlLoader.getController();
                loginController.setUsername(Username);
                loginController.setSsn(SSN);
                loginController.setID(id);
                loginController.setDoctorLoginController(this);
                stage = (Stage) ((Node)e.getSource()).getScene().getWindow(); // Reuse stage
                stage.setScene(scene);
                stage.show();
            }
            else {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/ErrorPopUp/ErrorPopUp.fxml"));
                Parent parent = null;
                try {
                    parent = fxmlLoader.load();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                ErrorPopUpController errorPopUpController = fxmlLoader.getController();
                errorPopUpController.setErrorMessage("Wrong ID or Password");

                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                stage.initModality(javafx.stage.Modality.APPLICATION_MODAL); // This line makes the new window modal
                stage.setScene(scene);
                stage.showAndWait();
            }
            client.close();
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
    @FXML
    public void initialize() {
        LogInButton.setOnAction(LogInButtonClicked());
        BackButton.setOnAction(BackButtonClicked());
    }
    private WelcomeController welcomeController;
    public void setWelcomeController(WelcomeController welcomeController) {
        this.welcomeController = welcomeController;
    }
    private DBeforeController dBeforeController;
    public void setDBeforeController(DBeforeController dBeforeController) {
        this.dBeforeController = dBeforeController;
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
}
