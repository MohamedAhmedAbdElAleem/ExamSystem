package App.StudentLogin;
import App.ErrorPopUp.ErrorPopUpController;
import App.GetPassword.GetPasswordController;
import App.SBefore.SBeforeController;
import App.SExams.SExamsController;
import App.SHome.SHomeController;
import App.SResults.SResultsController;
import App.SucessfulPopUp.SucessfulPopUpController;
import App.Welcome.WelcomeController;
import Main.HoverAnimation;
import Main.Student;
import Main.Validation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import Main.Client;
import App.Welcome.WelcomeController;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import javafx.concurrent.Task;
public class StudentLoginController {
    @FXML
    private Label StudentLabel;
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

    private static final String TEXT_TO_TYPE = "Student Login";
    private static final Duration DELAY_BETWEEN_LETTERS = Duration.seconds(0.05);
    private int currentIndex = 0;

    Validation validation = new Validation();
    HoverAnimation hoverAnimation = new HoverAnimation();


    public EventHandler<ActionEvent> LogInButtonClicked() {
        return e -> {
            String id = UID.getText();
            String password = UPassword.getText();
            StudentLoginController controllerInstance = this;

            Task<Void> task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    try {
                        Client client = new Client();
                        client.sendMessage("login");
                        client.sendMessage("Student");
                        client.sendMessage(id);
                        client.sendMessage(password);
                        String message = client.receiveMessage();
                        if (message.equalsIgnoreCase("true")) {
                            Student student = client.getStudent();
                            Platform.runLater(() -> {
                                validation.showSuccessPopUp("Student Login Successful For User : " + student.getSname());
                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/SBefore/SBefore.fxml"));
                                Scene scene = null;
                                Stage stage = null;
                                try {
                                    scene = new Scene(fxmlLoader.load());
                                } catch (IOException ex) {
                                    System.out.println("Error in loading scene : " + ex.getMessage());
                                }
                                SBeforeController loginController = fxmlLoader.getController();
                                loginController.setStudent(student);
                                loginController.setStudentLoginController(controllerInstance);
                                stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                                stage.setScene(scene);
                                stage.show();
                            });
                        } else {
                            Platform.runLater(() -> {
                                validation.showErrorPopUp("Invalid Username or Password");
                            });
                        }
                        client.close();
                    } catch (Exception ex) {
                        Platform.runLater(() -> {
                            validation.showErrorPopUp("Server is not Responding");
                        });
                    }
                    return null;
                }
            };

            new Thread(task).start();
        };
    }
        @FXML
    public void initialize() {
        LogInButton.setOnAction(LogInButtonClicked());
        BackButton.setOnAction(BackButtonClicked());
        GetPassword.setOnAction(GetPasswordButtonClicked());
        LogInButton.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_ENTERED, hoverAnimation);
        LogInButton.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_EXITED, hoverAnimation);
        BackButton.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_ENTERED, hoverAnimation);
        BackButton.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_EXITED, hoverAnimation);
        GetPassword.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_ENTERED, hoverAnimation);
        GetPassword.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_EXITED, hoverAnimation);
        typeText();

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
    private void typeText() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, e -> typeNextLetter()),
                new KeyFrame(DELAY_BETWEEN_LETTERS)
        );
        timeline.setCycleCount(TEXT_TO_TYPE.length());
        timeline.play();
    }

    private void typeNextLetter() {
        if (currentIndex < TEXT_TO_TYPE.length()) {
            StudentLabel.setText(TEXT_TO_TYPE.substring(0, currentIndex + 1));
            currentIndex++;
        }
    }
}
