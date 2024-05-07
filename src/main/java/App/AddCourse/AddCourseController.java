package App.AddCourse;

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

public class AddCourseController {
    @FXML
    private TextField CourseName;
    @FXML
    private TextField CCreditHours;
    @FXML
    private TextField CDID;
    @FXML
    private Button AddButton;

    private String username;
    private String message = "";
    private ACoursesController aCoursesController;

    public void initialize() {
        AddButton.setOnAction(addButtonClicked());
    }

    private EventHandler<ActionEvent> addButtonClicked() {
        return e -> {
            String name = CourseName.getText();
            String creditHours = CCreditHours.getText();
            String id = CDID.getText();
            if (name.isEmpty() || creditHours.isEmpty() || id.isEmpty()){
                showErrorPopUp("Error At The Inputs or Doctor ID");
                return;
            }
            Client client = new Client();
            client.sendMessage("addCourse");
            client.sendMessage(name);
            client.sendMessage(creditHours);
            client.sendMessage(id);
            String response = client.receiveMessage();
            if (response.equalsIgnoreCase("true")){
                showSuccessPopUp(name+" Course Added Successfully");
            } else {
                showErrorPopUp("Error At The Inputs or Doctor ID");
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
        this.username = username1;
    }

    public void setACoursesController(ACoursesController aCoursesController) {
        this.aCoursesController = aCoursesController;
    }
}
