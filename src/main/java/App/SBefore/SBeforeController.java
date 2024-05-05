package App.SBefore;

import App.DHome.DHomeController;
import App.SExams.SExamsController;
import App.SHome.SHomeController;
import App.SResults.SResultsController;
import App.StudentLogin.StudentLoginController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;


public class SBeforeController {
    private String username;
    @FXML
    private Button LogOutButton;
    @FXML
    private Button ContinueButton;
    @FXML
    private ComboBox<String> choice1;
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
//            loginController.setUsername(Username);
            sHomeController.setDBeforeController(this);
            Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        };
    }
            @FXML
    public void initialize() {
        choice1.setItems(FXCollections.observableArrayList("Advanced Programming Application", "Data Structure"));
        LogOutButton.setOnAction(LogOutButtonClicked());
        ContinueButton.setOnAction(ContinueButtonClicked());
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private StudentLoginController studentLoginController;
    public void setStudentLoginController(StudentLoginController studentLoginController) {
        this.studentLoginController = studentLoginController;
    }
    private SHomeController sHomeController;
    public void setStudentLoginController(SHomeController sHomeController) {
        this.sHomeController = sHomeController;
    }

    private SExamsController sExamsController;
    public void setStudentLoginController(SExamsController sExamsController) {
        this.sExamsController = sExamsController;
    }

    private SResultsController sResultsController;
    public void setStudentLoginController(SResultsController sResultsController) {
        this.sResultsController = sResultsController;
    }
}
