package App.StudentChangePassword;

import App.SExams.SExamsController;
import App.SHome.SHomeController;
import App.SResults.SResultsController;
import Main.Client;
import Main.Student;
import Main.Validation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class StudentChangePasswordController {


    Validation validation = new Validation();
    @FXML
    private TextField oldPassword;
    @FXML
    private TextField newPassword;
    @FXML
    private TextField confirmPassword;

    @FXML
    private Button changePasswordButton;

    public void initialize() {
        changePasswordButton.setOnAction(changePasswordButtonClicked());
    }

    private EventHandler<ActionEvent> changePasswordButtonClicked() {
        return e -> {
            Client client = new Client();
            String oldPass = oldPassword.getText();
            String newPass = newPassword.getText();
            String confirmPass = confirmPassword.getText();
            if (oldPass.isEmpty() || newPass.isEmpty() || confirmPass.isEmpty()) {
                validation.showErrorPopUp("Please fill all the fields");
                return;
            }
            if (!newPass.equals(confirmPass)) {
                validation.showErrorPopUp("Passwords do not match");
                return;
            }
            client.sendMessage("changePassword");
            client.sendMessage(student.getSid());
            client.sendMessage(oldPass);
            client.sendMessage(newPass);
            String response = client.receiveMessage();
            if (response.equalsIgnoreCase("true")) {
                validation.showSuccessPopUp("Password changed successfully");
                oldPassword.clear();
                newPassword.clear();
                confirmPassword.clear();
            } else {
                validation.showErrorPopUp("Incorrect old password");
            }
        };
    }

    private SHomeController sHomeController;

    private SResultsController sResultsController;

    private SExamsController sExamsController;
    public void setSHomeController(SHomeController sHomeController) {
        this.sHomeController = sHomeController;
    }

    public void setSResultsController(SResultsController sResultsController) {
        this.sResultsController = sResultsController;
    }

    public void setSExamsController(SExamsController sExamsController) {
        this.sExamsController = sExamsController;
    }

    Student student;
    public void setStudent(Student student) {
        this.student = student;
    }
}
