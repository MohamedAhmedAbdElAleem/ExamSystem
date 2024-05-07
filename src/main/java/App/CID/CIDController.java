package App.CID;

import App.ACourses.ACoursesController;
import App.ErrorPopUp.ErrorPopUpController;
import App.SucessfulPopUp.SucessfulPopUpController;
import Main.Client;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CIDController {
    @FXML
    private TextField CID;
    @FXML
    private Button ProceedButton;

    private String username1;
    private ACoursesController aCoursesController;
    public void initialize() {
        ProceedButton.setOnAction(proceedButtonClicked());
    }

    private EventHandler<ActionEvent> proceedButtonClicked() {
        return e -> {
            String id = CID.getText();
            Client client = new Client();
            client.sendMessage("checkCourseId");
            client.sendMessage(id);
            String response = client.receiveMessage();
            if (response.equalsIgnoreCase("true")){
                client.sendMessage("deleteCourse");
                client.sendMessage(id);
                response = client.receiveMessage();
                if (response.equalsIgnoreCase("true")){
                    showSuccessPopUp("Course Deleted Successfully");
                }
                else {
                    showErrorPopUp("Course ID Not Found");
                }
            }
            else {
                showErrorPopUp("Course ID Not Found");
            }
        };

    }

    private void showSuccessPopUp(String message) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/SucessfulPopUp/SucessfulPopUp.fxml"));
        Parent parent = null;
        try {
            parent = fxmlLoader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        SucessfulPopUpController sucessfulPopUpController = fxmlLoader.getController();
        sucessfulPopUpController.setSuccessfulMessage(message);

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    private void showErrorPopUp(String message) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/ErrorPopUp/ErrorPopUp.fxml"));
        Parent parent = null;
        try {
            parent = fxmlLoader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        ErrorPopUpController errorPopUpController = fxmlLoader.getController();
        errorPopUpController.setErrorMessage(message);

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();


    }

    public void setUsername(String username1) {
        this.username1 = username1;
    }

    public void setACoursesController(ACoursesController aCoursesController) {
        this.aCoursesController = aCoursesController;
    }
}
