package App.ExamFactor;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ExamFactorController {
    @FXML
    private TextField ExamName;
    @FXML
    private DatePicker StartDate;
    @FXML
    private ComboBox<String> StartHour;
    @FXML
    private ComboBox<String> StartMinute;
    @FXML
    private ComboBox<String> TimeHour;
    @FXML
    private ComboBox<String> TimeMinute;
    @FXML
    private TextField LecStart;
    @FXML
    private TextField LecEnd;
    @FXML
    private TextField TotalMarks;
    @FXML
    private TextField EasyMarks;
    @FXML
    private TextField MediumMarks;
    @FXML
    private TextField HardMarks;
    @FXML
    private TextField EasyMCQQ;
    @FXML
    private TextField MediumMCQQ;
    @FXML
    private TextField HardMCQQ;
    @FXML
    private TextField EasyTFQ;
    @FXML
    private TextField MediumTFQ;
    @FXML
    private TextField HardTFQ;
    @FXML
    private Button MakeButton;
    @FXML
    private Label TotalDegrees;
    @FXML
    private Label TotalQuestions;





    public void initialize() {
        StartHour.setItems(FXCollections.observableArrayList("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"));
        StartMinute.setItems(FXCollections.observableArrayList("00", "15", "30", "45"));
        TimeHour.setItems(FXCollections.observableArrayList("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"));
        TimeMinute.setItems(FXCollections.observableArrayList("00", "15", "30", "45"));
    }

}
