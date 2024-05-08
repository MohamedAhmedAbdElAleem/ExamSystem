package App.AddStudent;

import App.DStudent.DStudentController;
import Main.Client;
import Main.Student;
import Main.Validation;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddStudentController {
    @FXML
    private TextField StudentName;
    @FXML
    private TextField StudentRegistartionNumber;
    @FXML
    private TextField StudentSSN;
    @FXML
    private TextField StudentEmail;
    @FXML
    private Button AddStudentButton;
    Validation validation = new Validation();
    private void AddStudentButtonClicked() {
        String name = StudentName.getText();
        String registrationNumber = StudentRegistartionNumber.getText();
        String ssn = StudentSSN.getText();
        String email = StudentEmail.getText();
//        System.out.println(name + " " + registrationNumber + " " + ssn + " " + email);
        Student student = new Student(name, registrationNumber, ssn, email);
        Client client = new Client();
        client.sendMessage("addStudent");
        String message = client.sendStudent(student,courseid);
        if(message.equalsIgnoreCase("true")) {
            validation.showSuccessPopUp("Student Added Successfully");

        }else {
            validation.showErrorPopUp("Error At The Inputs");
        }
    }
    public void initialize() {
        AddStudentButton.setOnAction(e -> AddStudentButtonClicked());
    }
    DStudentController dStudentController;
    public void setDStudentController(DStudentController dStudentController) {
        this.dStudentController = dStudentController;
    }
    private String courseid;
    public void setCourseId(String courseId) {
        this.courseid = courseId;
    }
}
