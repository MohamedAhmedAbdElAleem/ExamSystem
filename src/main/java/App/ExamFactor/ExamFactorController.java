package App.ExamFactor;

import App.DExam.DExamController;
import App.EligableStudent.EligableStudentController;
import App.ExamCard.ExamCardController;
import Main.Client;
import Main.Exam;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

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
    private Exam exam;

    public void initialize() {
        StartHour.setItems(FXCollections.observableArrayList("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"));
        StartMinute.setItems(FXCollections.observableArrayList("00", "15", "30", "45"));
        TimeHour.setItems(FXCollections.observableArrayList("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"));
        TimeMinute.setItems(FXCollections.observableArrayList("00", "15", "30", "45"));
        EasyMarks.setText("1");
        MediumMarks.setText("1");
        HardMarks.setText("1");
        EasyMCQQ.setText("0");
        MediumMCQQ.setText("0");
        HardMCQQ.setText("0");
        EasyTFQ.setText("0");
        MediumTFQ.setText("0");
        HardTFQ.setText("0");
        EasyMarks.textProperty().addListener((observable, oldValue, newValue) -> AddListener());
        MediumMarks.textProperty().addListener((observable, oldValue, newValue) -> AddListener());
        HardMarks.textProperty().addListener((observable, oldValue, newValue) -> AddListener());
        EasyMCQQ.textProperty().addListener((observable, oldValue, newValue) -> AddListener());
        MediumMCQQ.textProperty().addListener((observable, oldValue, newValue) -> AddListener());
        HardMCQQ.textProperty().addListener((observable, oldValue, newValue) -> AddListener());
        EasyTFQ.textProperty().addListener((observable, oldValue, newValue) -> AddListener());
        MediumTFQ.textProperty().addListener((observable, oldValue, newValue) -> AddListener());
        TotalMarks.textProperty().addListener((observable, oldValue, newValue) -> AddListener());
        MakeButton.setDisable(true);
        MakeButton.setOnAction(MakeButtonClicked());
    }

    public void setExam(Exam quiz) {
        this.exam = quiz;
//        System.out.println("Quiz : "+this.exam.getQbId());

    }

    private EventHandler<ActionEvent> MakeButtonClicked() {
       return e -> {
                exam.setName(ExamName.getText());
                int StartHourValue = Integer.parseInt(StartHour.getValue());
                int StartMinuteValue = Integer.parseInt(StartMinute.getValue());
                exam.setStartDate(StartDate.getValue(), StartHourValue, StartMinuteValue);
                double duration = Integer.parseInt(TimeHour.getValue()) + (Integer.parseInt(TimeMinute.getValue()) / 60.0);
                exam.setDuration(duration);
                exam.setTotalMarks(Integer.parseInt(TotalMarks.getText()));
                exam.setLectureStart(Integer.parseInt(LecStart.getText()));
                exam.setLectureEnd(Integer.parseInt(LecEnd.getText()));
                exam.setEasyMarks(Integer.parseInt(EasyMarks.getText()));
                exam.setMediumMarks(Integer.parseInt(MediumMarks.getText()));
                exam.setHardMarks(Integer.parseInt(HardMarks.getText()));
                exam.setMCQE(Integer.parseInt(EasyMCQQ.getText()));
                exam.setMCQM(Integer.parseInt(MediumMCQQ.getText()));
                exam.setMCQH(Integer.parseInt(HardMCQQ.getText()));
                exam.setTFE(Integer.parseInt(EasyTFQ.getText()));
                exam.setTFM(Integer.parseInt(MediumTFQ.getText()));
                exam.setTFH(Integer.parseInt(HardTFQ.getText()));
               FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/EligableStudent/EligableStudent.fxml"));
               EligableStudentController eligableStudentController = new EligableStudentController();
               eligableStudentController.setExamFactorController(this);
               fxmlLoader.setController(eligableStudentController);
               eligableStudentController.setExam(exam);
               try {
                   Scene scene = new Scene(fxmlLoader.load());
                   Stage stage = new Stage();
                   stage.setScene(scene);
                   stage.show();

                   // Close the current stage (the ExamFactor page)
                   ((Node)(e.getSource())).getScene().getWindow().hide();
               } catch (Exception ex) {
                   System.out.println("Error in loading scene : " + ex.getMessage());
               }
           };
    }

    private void AddListener(){
        try{
            if(EasyMarks.getText().isEmpty()||EasyMarks.getText().equals("")||EasyMarks.getText().equals(" ")||MediumMarks.getText().isEmpty()||MediumMarks.getText().equals("")||MediumMarks.getText().equals(" ")||HardMarks.getText().isEmpty()||HardMarks.getText().equals("")||HardMarks.getText().equals(" ")||EasyMCQQ.getText().isEmpty()||EasyMCQQ.getText().equals("")||EasyMCQQ.getText().equals(" ")||MediumMCQQ.getText().isEmpty()||MediumMCQQ.getText().equals("")||MediumMCQQ.getText().equals(" ")||HardMCQQ.getText().isEmpty()||HardMCQQ.getText().equals("")||HardMCQQ.getText().equals(" ")||EasyTFQ.getText().isEmpty()||EasyTFQ.getText().equals("")||EasyTFQ.getText().equals(" ")||MediumTFQ.getText().isEmpty()||MediumTFQ.getText().equals("")||MediumTFQ.getText().equals(" ")||HardTFQ.getText().isEmpty()||HardTFQ.getText().equals("")||HardTFQ.getText().equals(" ")||Integer.parseInt(EasyMarks.getText())<0||Integer.parseInt(MediumMarks.getText())<0||Integer.parseInt(HardMarks.getText())<0||Integer.parseInt(EasyMCQQ.getText())<0||Integer.parseInt(MediumMCQQ.getText())<0||Integer.parseInt(HardMCQQ.getText())<0||Integer.parseInt(EasyTFQ.getText())<0||Integer.parseInt(MediumTFQ.getText())<0||Integer.parseInt(HardTFQ.getText())<0){
                TotalDegrees.setText("0");
                TotalQuestions.setText("0");
            }else {
                int EasyMarksValue = Integer.parseInt(EasyMarks.getText());
                int MediumMarksValue = Integer.parseInt(MediumMarks.getText());
                int HardMarksValue = Integer.parseInt(HardMarks.getText());
                int EasyMCQQValue = Integer.parseInt(EasyMCQQ.getText());
                int MediumMCQQValue = Integer.parseInt(MediumMCQQ.getText());
                int HardMCQQValue = Integer.parseInt(HardMCQQ.getText());
                int EasyTFQValue = Integer.parseInt(EasyTFQ.getText());
                int MediumTFQValue = Integer.parseInt(MediumTFQ.getText());
                int HardTFQValue = Integer.parseInt(HardTFQ.getText());

                TotalDegrees.setText(String.valueOf((EasyMarksValue * (EasyMCQQValue + EasyTFQValue)) + (MediumMarksValue * (MediumMCQQValue + MediumTFQValue)) + (HardMarksValue * (HardMCQQValue + HardTFQValue))));
                TotalQuestions.setText(String.valueOf((EasyMCQQValue + EasyTFQValue) + (MediumMCQQValue + MediumTFQValue) + (HardMCQQValue + HardTFQValue)));
                if (TotalMarks.getText().isEmpty() || TotalMarks.getText().equals("") || TotalMarks.getText().equals(" ") || Integer.parseInt(TotalMarks.getText()) < 0) {
                    TotalMarks.setText("0");
                    TotalDegrees.setStyle("-fx-text-fill: red;");
                    MakeButton.setDisable(true);
                }else {
                    if(Integer.parseInt(TotalMarks.getText()) != Integer.parseInt(TotalDegrees.getText())) {
                        TotalDegrees.setStyle("-fx-text-fill: red;");
                        MakeButton.setDisable(true);
                    }else if(Integer.parseInt(TotalMarks.getText()) == Integer.parseInt(TotalDegrees.getText())){
                        TotalDegrees.setStyle("-fx-text-fill: green;");
                        MakeButton.setDisable(false);
                    }
                }
            }
        }catch (Exception e){
            System.out.println("Error in adding listener : "+e.getMessage());
        }
    }
    private DExamController dExamController;
    public void setDExamController(DExamController dExamController) {
        this.dExamController = dExamController;
    }
    private ExamCardController examCardController;
    public void setExamCardController(ExamCardController examCardController) {
        this.examCardController = examCardController;
    }
}
