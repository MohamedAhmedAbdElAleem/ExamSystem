package App.StudentLogin;
import App.SBefore.SBeforeController;
import App.SExams.SExamsController;
import App.SHome.SHomeController;
import App.SResults.SResultsController;
import App.Welcome.WelcomeController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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

    public EventHandler<ActionEvent> LogInButtonClicked() {
        return e -> {
            String username = UID.getText();
            String password = UPassword.getText();
            // Send the username and password to the server
            Client client = new Client();
            client.sendMessage("login");
            client.sendMessage("Student");
            client.sendMessage(username);
            client.sendMessage(password);
            String message = client.receiveMessage();
            if(message.equalsIgnoreCase("true")){
                String username1 = client.receiveMessage();
                Username = username1;
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/SBefore/SBefore.fxml"));
                Scene scene = null;
                try {
                    scene = new Scene(fxmlLoader.load());
                } catch (IOException ex) {
                    System.out.println("Error in loading scene : "+ex.getMessage());
                }
                SBeforeController sBeforeController = fxmlLoader.getController();
                sBeforeController.setUsername(Username);
                sBeforeController.setStudentLoginController(this);
                Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }
            // Display the message from the server
//            System.out.println(message);
            client.close();
        };
    }
    @FXML
    public void initialize() {
        LogInButton.setOnAction(LogInButtonClicked());
        BackButton.setOnAction(BackButtonClicked());
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
