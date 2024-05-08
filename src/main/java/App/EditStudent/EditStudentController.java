package App.EditStudent;

import App.SID.SIDController;
import Main.Client;
import Main.Student;
import Main.Validation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class EditStudentController {
    @FXML
    private TextField Sname;
    @FXML
    private TextField SregistrationNumber;
    @FXML
    private TextField Sssn;
    @FXML
    private Button SaveButton;
    Validation validation = new Validation();
    public void initialize() {
        SaveButton.setOnAction(SaveButtonClicked());
    }

    private EventHandler<ActionEvent> SaveButtonClicked() {
        return e -> {
            String name = Sname.getText();
            String registrationNumber = SregistrationNumber.getText();
            String ssn = Sssn.getText();
            if (name.isEmpty() || registrationNumber.isEmpty() || ssn.isEmpty()){
                validation.showErrorPopUp("Error At The Inputs");
                return;
            }
            Client client = new Client();
            String response =  client.process("updateStudent",result);
            if(response.equals("true"))
            {
                validation.showSuccessPopUp("Student Updated Successfully");
            }
            else
            {
                validation.showErrorPopUp("Error At The Inputs");
            }
        };
    }

    private SIDController sidController;
    public void setSIDController(SIDController sidController) {
        this.sidController = sidController;
    }
    private Student result;
    public void setStudent(Student result) {
        this.result = result;
        Sname.setText(result.getSname());
        SregistrationNumber.setText(result.getSregistrationNumber());
        Sssn.setText(result.getSssn());
    }
}
