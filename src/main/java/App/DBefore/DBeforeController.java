package App.DBefore;

import App.DExam.DExamController;
import App.DHome.DHomeController;
import App.DQABank.DQABankController;
import App.DStudent.DStudentController;
import App.DoctorLogin.DoctorLoginController;
import App.DoctorProfile.DoctorProfileController;
import App.Notification.NotificationController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class DBeforeController {
    @FXML
    private Button DoctorProfile;
    @FXML
    private Button Notification;
    @FXML
    private Button ContinueButton;
    @FXML
    private Button LogOutButton;
    @FXML
    private ComboBox<String> choice1;

    private EventHandler<ActionEvent> LogOutButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/DoctorLogin/DoctorLogin.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            DoctorLoginController loginController = fxmlLoader.getController();
            loginController.setDBeforeController(this);
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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/DHome/DHome.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            DHomeController loginController = fxmlLoader.getController();
//            loginController.setUsername(Username);
            loginController.setDBeforeController(this);
            Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();


//            if (choice.equalsIgnoreCase("Advanced Programming Application")){
//                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/AdvancedProgrammingApplication/AdvancedProgrammingApplication.fxml"));
//                Scene scene = null;
//                try {
//                    scene = new Scene(fxmlLoader.load());
//                } catch (IOException ex) {
//                    System.out.println("Error in loading scene : "+ex.getMessage());
//                }
//                AdvancedProgrammingApplicationController loginController = fxmlLoader.getController();
//                loginController.setUsername(Username);
//                loginController.setDBeforeController(this);
//                Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
//                stage.setScene(scene);
//                stage.show();
//            }
//            else if (choice.equalsIgnoreCase("Data Structure")){
//                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/DataStructure/DataStructure.fxml"));
//                Scene scene = null;
//                try {
//                    scene = new Scene(fxmlLoader.load());
//                } catch (IOException ex) {
//                    System.out.println("Error in loading scene : "+ex.getMessage());
//                }
//                DataStructureController loginController = fxmlLoader.getController();
//                loginController.setUsername(Username);
//                loginController.setDBeforeController(this);
//                Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
//                stage.setScene(scene);
//                stage.show();
//            }
//            else if (choice.equalsIgnoreCase("Algorithms")){
//                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/Algorithms/Algorithms.fxml"));
//                Scene scene = null;
//                try {
//                    scene = new Scene(fxmlLoader.load());
//                } catch (IOException ex) {
//                    System.out.println("Error in loading scene : "+ex.getMessage());
//                }
//                AlgorithmsController loginController = fxmlLoader.getController();
//                loginController.setUsername(Username);
//                loginController.setDBeforeController(this);
//                Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
//                stage.setScene(scene);
//                stage.show();
//            }
        };
    }
    @FXML
    public void initialize() {
        choice1.setItems(FXCollections.observableArrayList("Advanced Programming Application", "Data Structure"));
        choice1.getItems().add("Algorithms");
        LogOutButton.setOnAction(LogOutButtonClicked());
        ContinueButton.setOnAction(ContinueButtonClicked());
        DoctorProfile.setOnAction(DoctorProfileButtonClicked());
        Notification.setOnAction(NotificationButtonClicked());
    }


    private EventHandler<ActionEvent> NotificationButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/Notification/Notification.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            NotificationController loginController = fxmlLoader.getController();
//           //loginController.setUsername(Username);
            loginController.setDBeforeController(this);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL); // This line makes the new window modal
            stage.setScene(scene);
            stage.showAndWait();
        };
    }

    private EventHandler<ActionEvent> DoctorProfileButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/DoctorProfile/DoctorProfile.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            DoctorProfileController loginController = fxmlLoader.getController();
//           //loginController.setUsername(Username);
            loginController.setDBeforeController(this);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL); // This line makes the new window modal
            stage.setScene(scene);
            stage.showAndWait();
        };
    }


    private DoctorLoginController doctorLoginController;
    public void setAdminLoginController(DoctorLoginController doctorLoginController) {
        this.doctorLoginController = doctorLoginController;
    }
    private String Username;
    public void setUsername(String username) {
        this.Username = username;
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
