package App.EditStudent;

import App.SID.SIDController;
import Main.Client;
import Main.Student;
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

    public void initialize() {
        SaveButton.setOnAction(SaveButtonClicked());
    }

    private EventHandler<ActionEvent> SaveButtonClicked() {
        return e -> {
            String name = Sname.getText();
            String registrationNumber = SregistrationNumber.getText();
            String ssn = Sssn.getText();
            if(name.isEmpty())
            {
                name = result.getSname();
            }else {
                result.setSname(name);
            }
            if(registrationNumber.isEmpty())
            {
                registrationNumber = result.getSregistrationNumber();
            }else {
                result.setSregistrationNumber(registrationNumber);
            }
            if(ssn.isEmpty())
            {
                ssn = result.getSssn();
            }
            else {
                result.setSssn(ssn);
            }

            Client client = new Client();
            String response =  client.process("updateStudent",result);
            if(response.equals("true"))
            {
//                sidController.showSuccessPopUp("Student Updated Successfully");
                System.out.println("Student Updated Successfully");
            }
            else
            {
//                sidController.showErrorPopUp("Student not found");
                System.out.println("Student not found");
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
