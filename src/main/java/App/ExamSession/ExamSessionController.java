package App.ExamSession;

import App.SExams.SExamsController;
import App.StudentCardJoin.StudentCardJoinController;
import Main.Client;
import Main.Exam;
import Main.Question;
import Main.Student;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

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
        SubmitButton.setOnAction(SubmitButtonClicked());
        StartTimer();
    }

    private EventHandler<ActionEvent> SubmitButtonClicked() {
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
            updateStudentAnswers();
            calculateGrade();
            Stage stage = (Stage) SubmitButton.getScene().getWindow();
            stage.close();
        };
    }

    private void calculateGrade() {
        Client client = new Client();
        client.sendMessage("calculateGrade");
        client.sendMessage(student.getSid());
        client.sendMessage(String.valueOf(exam.getExamId()));
        client.sendMessage(exam.getQuestionsIds());
        String studentAnswers = "";
        for (Question question : questions) {
            studentAnswers += question.getStudentAnswer() + ",";
        }
        System.out.println(studentAnswers);
        client.sendMessage(studentAnswers);
        String response = client.receiveMessage();
        System.out.println(response);
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
            Platform.runLater(this::displayQuestion);
            updateStudentAnswers();

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
            Platform.runLater(this::displayQuestion);
            updateStudentAnswers();
        };
    }
    private void updateStudentAnswers() {
        Client client = new Client();
        client.sendMessage("updateStudentAnswers");
        client.sendMessage(student.getSid());
        client.sendMessage(String.valueOf(exam.getExamId()));
        String studentAnswers = "";
        for (Question question : questions) {
            studentAnswers += question.getStudentAnswer() + ",";
        }
        System.out.println(studentAnswers);
        client.sendMessage(studentAnswers);
        String response = client.receiveMessage();
//        System.out.println(response);

//        client.sendQuestions(questions);
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
            ARadio.setSelected(false);
            BRadio.setSelected(false);
            CRadio.setSelected(false);
            DRadio.setSelected(false);
            if (question.getStudentAnswer().equalsIgnoreCase(question.getAnswer())) {
                ARadio.setSelected(true);
            } else if (question.getStudentAnswer().equalsIgnoreCase(question.getOption2())) {
                BRadio.setSelected(true);
            } else if (question.getStudentAnswer().equalsIgnoreCase(question.getOption3())) {
                CRadio.setSelected(true);
            } else if (question.getStudentAnswer().equalsIgnoreCase(question.getOption4())) {
                DRadio.setSelected(true);
            }
        } else {
            ARadio.setVisible(true);
            BRadio.setVisible(true);
            CRadio.setVisible(false);
            DRadio.setVisible(false);
            CLabel.setText("");
            DLabel.setText("");
            ALabel.setText("True");
            BLabel.setText("False");
            ARadio.setSelected(false);
            BRadio.setSelected(false);
            if (question.getStudentAnswer().equalsIgnoreCase("True")) {
                ARadio.setSelected(true);
            } else if (question.getStudentAnswer().equalsIgnoreCase("False")) {
                BRadio.setSelected(true);
            }

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
        client.sendMessage("getStudentQIds");
        String quizId = String.valueOf(exam.getQbId());
        client.sendMessage(quizId);
        client.sendMessage(student.getSid());
        String studentQIds = client.receiveMessage();
        client.sendMessage("getQuestionsOfExam");
        client.sendMessage(quizId);
        if(studentQIds.equals("null")) {
        client.sendMessage(exam.getQuestionsIds());
        }else{
            client.sendMessage(studentQIds);
        }
        ObservableList<Question> questions = client.getQuestionsOfExam();
        List<Question> list = new ArrayList<>(questions);
        if (exam.getQuestionsIds().equals("null")) {
        Collections.shuffle(list);
        questions.setAll(list);
        String newQuestionsIds = "";
        for (Question question : questions) {
            newQuestionsIds += question.getQuestionId() + ",";
        }
        exam.setQuestionsIds(newQuestionsIds);
        client.sendMessage("updateExamQuestions");
        client.sendMessage(String.valueOf(exam.getExamId()));
        client.sendMessage(newQuestionsIds);
        client.sendMessage(student.getSid());
        }else {
            questions.setAll(list);
        }
        return questions;
    }

    private Exam exam;
    public void setExam(Exam exam) {
        this.exam = exam;
        questionIndex = 0;
        questions = getQuestions();
        for (Question question : questions) {
            question.setStudentAnswer("");
        }
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
    private Student student;
    public void setStudent(Student student) {
        this.student = student;
    }

    public void setStudentAnswers(String studentAnswers) {
        String[] answers = studentAnswers.split(",");
        for (int i = 0; i < answers.length; i++) {
            questions.get(i).setStudentAnswer(answers[i]);
        }
    }

    public void setExamQuestions(String examQuestions) {
        String[] questionsIds = examQuestions.split(",");
        for (int i = 0; i < questionsIds.length; i++) {
            questions.get(i).setQuestionId(Integer.parseInt(questionsIds[i]));
        }
    }
}
