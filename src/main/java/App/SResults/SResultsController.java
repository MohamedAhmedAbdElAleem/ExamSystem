package App.SResults;


import App.SBefore.SBeforeController;
import App.SExams.SExamsController;
import App.SHome.SHomeController;
import App.StudentLogin.StudentLoginController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class SResultsController {
    @FXML
    private Button ExamsButton;
    @FXML
    private Button SHomeButton;
    @FXML
    private Button BackButton;
    @FXML
    private Button LogOutButton;
    private EventHandler<ActionEvent> BackbuttonClicked() {
        return e->{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/SBefore/SBefore.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            SBeforeController sBeforeController = fxmlLoader.getController();
            sBeforeController.setStudentLoginController(this);
            Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        };
    }
    private EventHandler<ActionEvent> LogOutButtonClicked() {
        return e ->{
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
    public void initialize() {
        LogOutButton.setOnAction(LogOutButtonClicked());
        BackButton.setOnAction(BackbuttonClicked());
        ExamsButton.setOnAction(ExamsButtonClicked());
        SHomeButton.setOnAction(SHomeButtonClicked());
    }

    private EventHandler<ActionEvent> SHomeButtonClicked() {
        return e->{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/SHome/SHome.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            SHomeController sHomeController = fxmlLoader.getController();
            sHomeController.setSResultsController(this);
            Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        };
    }

    private EventHandler<ActionEvent> ExamsButtonClicked() {
        return e->{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/SExams/SExams.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            SExamsController sExamsController = fxmlLoader.getController();
            sExamsController.setSHomeController(this);
            Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        };
    }

    private SHomeController sHomeController;
    public void setSHomeController(SHomeController sHomeController) {
        this.sHomeController = sHomeController;
    }

    private SExamsController sExamsController;
    public void setSHomeController(SExamsController sExamsController) {
        this.sExamsController = sExamsController;
    }
}
