package App.SHome;

import App.SBefore.SBeforeController;
import App.SExams.SExamsController;
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


public class SHomeController {
    @FXML
    private Button ResultButton;
    @FXML
    private Button ExamsButton;
    @FXML
    private Button BackButton;
    @FXML
    private Button LogOutButton;
    public void initialize() {
        LogOutButton.setOnAction(LogOutButtonClicked());
        BackButton.setOnAction(BackbuttonClicked());
        ExamsButton.setOnAction(ExamsButtonClicked());
        ResultButton.setOnAction(ResultButtonClicked());
    }

    private EventHandler<ActionEvent> ResultButtonClicked() {
        return e->{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/SResults/SResults.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            SResultsController sResultsController = fxmlLoader.getController();
            sResultsController.setSHomeController(this);
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

    private SBeforeController sBeforeController;
    public void setDBeforeController(SBeforeController sBeforeController) {
        this.sBeforeController=sBeforeController;
    }
    private SResultsController sResultsController;
    public void setSResultsController(SResultsController sResultsController) {
        this.sResultsController = sResultsController;
    }

    private SExamsController sExamsController;
    public void setSExamsController(SExamsController sExamsController) {
        this.sExamsController = sExamsController;
    }
}
