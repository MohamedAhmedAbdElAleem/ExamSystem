package App.EligableStudent;

import App.AreYouSure.AreYouSureController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class EligableStudentController {
    @FXML
    private Button DeleteStudentButton;
    @FXML
    private Button MakeExamButton;

    public void initialize() {
        DeleteStudentButton.setOnAction(DeleteStudentButtonClicked());
        MakeExamButton.setOnAction(MakeExamButtonClicked());
    }

    private EventHandler<ActionEvent> MakeExamButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(App.AreYouSure.AreYouSureApplication.class.getResource("AreYouSure.fxml"));
            Scene scene = null;
            Stage stage = new Stage();
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            AreYouSureController areYouSureController = fxmlLoader.getController();
            areYouSureController.setOnYes(MakeExam());
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        };

    }

    private Runnable MakeExam() {
        System.out.println("Make Exam");
        return null;
    }

    private EventHandler<ActionEvent> DeleteStudentButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(App.AreYouSure.AreYouSureApplication.class.getResource("AreYouSure.fxml"));
            Scene scene = null;
            Stage stage = new Stage();
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            AreYouSureController areYouSureController = fxmlLoader.getController();
            areYouSureController.setOnYes(DeleteStudent());
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        };

    }

    private Runnable DeleteStudent() {
        System.out.println("Delete Student");
        return null;
    }

}
