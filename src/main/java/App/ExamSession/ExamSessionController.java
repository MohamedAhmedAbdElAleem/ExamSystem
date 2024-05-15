package App.ExamSession;

import App.SExams.SExamsController;
import App.StudentCardJoin.StudentCardJoinController;
import Main.Client;
import Main.Exam;
import Main.Question;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

import java.security.spec.RSAOtherPrimeInfo;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExamSessionController {
    @FXML
    private Button NextButton;
    @FXML
    private Button BackButton;
    @FXML
    private Button SubmitButton;
    @FXML
    private Label QLabel;
    @FXML
    private RadioButton ARadio;
    @FXML
    private Label ALabel;
    @FXML
    private RadioButton BRadio;
    @FXML
    private Label BLabel;
    @FXML
    private RadioButton CRadio;
    @FXML
    private Label CLabel;
    @FXML
    private RadioButton DRadio;
    @FXML
    private Label DLabel;
    @FXML
    private Label QuestionNumber;
    @FXML
    private Label TimerLabel;
    private int questionIndex;
    private ObservableList<Question> questions;
    private String ExamDuration;
    @FXML
    public void initialize() {
        ALabel.setOnMouseClicked(event -> ARadio.setSelected(true));
        BLabel.setOnMouseClicked(event -> BRadio.setSelected(true));
        CLabel.setOnMouseClicked(event -> CRadio.setSelected(true));
        DLabel.setOnMouseClicked(event -> DRadio.setSelected(true));
        NextButton.setOnAction(nextButtonClicked());
        BackButton.setOnAction(backButtonClicked());
        StartTimer();
    }

    private EventHandler<ActionEvent> backButtonClicked() {
        return e -> {
            if(ARadio.isSelected()) {
                questions.get(questionIndex).setStudentAnswer(ALabel.getText());
            } else if(BRadio.isSelected()) {
                questions.get(questionIndex).setStudentAnswer(BLabel.getText());
            } else if(CRadio.isSelected()) {
                questions.get(questionIndex).setStudentAnswer(CLabel.getText());
            } else if(DRadio.isSelected()) {
                questions.get(questionIndex).setStudentAnswer(DLabel.getText());
            }
            questionIndex--;
            displayQuestion();
        };
    }

    private EventHandler<ActionEvent> nextButtonClicked() {
        return e -> {
            if(ARadio.isSelected()) {
                questions.get(questionIndex).setStudentAnswer(ALabel.getText());
            } else if(BRadio.isSelected()) {
                questions.get(questionIndex).setStudentAnswer(BLabel.getText());
            } else if(CRadio.isSelected()) {
                questions.get(questionIndex).setStudentAnswer(CLabel.getText());
            } else if(DRadio.isSelected()) {
                questions.get(questionIndex).setStudentAnswer(DLabel.getText());
            }
            questionIndex++;
            displayQuestion();
        };
    }

    private void displayQuestion() {
        Question question = questions.get(questionIndex);
        QLabel.setText(question.getQuestion());
        if (question.getQuestionType().equalsIgnoreCase("MCQ")) {
            ARadio.setVisible(true);
            BRadio.setVisible(true);
            CRadio.setVisible(true);
            DRadio.setVisible(true);
            ALabel.setText(question.getAnswer());
            BLabel.setText(question.getOption2());
            CLabel.setText(question.getOption3());
            DLabel.setText(question.getOption4());
        } else {
            ARadio.setVisible(true);
            BRadio.setVisible(true);
            CRadio.setVisible(false);
            DRadio.setVisible(false);
            CLabel.setText("");
            DLabel.setText("");
            ALabel.setText("True");
            BLabel.setText("False");
        }
        if(questionIndex == 0) {
            BackButton.setDisable(true);
        } else {
            BackButton.setDisable(false);
        }
        if(questionIndex == questions.size()-1) {
            NextButton.setDisable(true);
            SubmitButton.setDisable(false);
        } else {
            NextButton.setDisable(false);
            SubmitButton.setDisable(true);
        }
        QuestionNumber.setText((questionIndex+1) + " / " + questions.size());
    }

    private ObservableList<Question> getQuestions() {
        Client client = new Client();
        client.sendMessage("getQuestionsOfExam");
        String quizId = String.valueOf(exam.getQbId());
        client.sendMessage(quizId);
        client.sendMessage(exam.getQuestionsIds());
        ObservableList<Question> questions = client.getQuestionsOfExam();
        List<Question> list = new ArrayList<>(questions);
        // Shuffle the list
        Collections.shuffle(list);
        // Update the ObservableList with shuffled data
        questions.setAll(list);
        return questions;
    }

    private Exam exam;
    public void setExam(Exam exam) {
        this.exam = exam;
        questionIndex = 0;
        questions = getQuestions();
        Platform.runLater(this::displayQuestion);
    }
    private void StartTimer(){
        new Thread(
                () -> {
                    while (true){
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Platform.runLater(() -> {
                            LocalDateTime now = LocalDateTime.now();
                            LocalDateTime end = exam.getStartDate().plusMinutes((long)(exam.getDuration() * 60));
                            if (now.isAfter(end)){
                                SubmitButton.fire();
                            }
                            long seconds = java.time.Duration.between(now,end).getSeconds();
                            long hours = seconds / 3600;
                            long minutes = (seconds % 3600) / 60;
                            long remainingSeconds = seconds % 60;
                            String formattedDuration = String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds);
                            TimerLabel.setText(String.valueOf(formattedDuration));

                        });
                    }
                }
        ).start();
    }
    private SExamsController sExamsController;
    public void setSExamsController(SExamsController sExamsController) {
        this.sExamsController = sExamsController;
    }
    private StudentCardJoinController studentCardJoinController;
    public void setStudentCardJoinController(StudentCardJoinController studentCardJoinController) {
        this.studentCardJoinController = studentCardJoinController;
    }

    public void setExamCardJoinController(StudentCardJoinController studentCardJoinController) {
        this.studentCardJoinController = studentCardJoinController;
    }
}
