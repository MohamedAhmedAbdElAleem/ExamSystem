package App.DExam;
import App.DBefore.DBeforeController;
import App.DHome.DHomeController;
import App.DQABank.DQABankController;
import App.DStudent.DStudentController;
import App.DoctorLogin.DoctorLoginController;
import App.DoctorProfile.DoctorProfileController;
import App.EligableStudent.EligableStudentController;
import App.ExamCard.ExamCardController;
import App.ExamView.ExamViewController;
import App.Notification.NotificationController;
import Main.Client;
import Main.Exam;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class DExamController {
    @FXML
    private HBox PendingExamsPane;
    @FXML
    private HBox CompletedExamsPane;
//    @FXML
//    private VBox newQuizPane;
    @FXML
    private Button ExamCardButton;
    @FXML
    private Button DoctorProfile;
    @FXML
    private Button Notification;
    @FXML
    private Button DHomeButton;
    @FXML
    private Button QBankButton;
    @FXML
    private Button StudentsButton;
    @FXML
    private Button BackButton;
    @FXML
    private Button LogOutButton;
    private EventHandler<ActionEvent> DHomeButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/DHome/DHome.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            DHomeController dHomeController = fxmlLoader.getController();
            dHomeController.setUsername(Username);
            dHomeController.setSsn(ssn);
            dHomeController.setId(id);
            dHomeController.setCourseId(courseId);
            dHomeController.setSelectedCourse(selectedCourse);
            dHomeController.setDStudentController(this);
            Stage stage = (Stage) DHomeButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        };
    }
    private EventHandler<ActionEvent> QBankButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/DQABank/DQABank.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            DQABankController dqaBankController = fxmlLoader.getController();
            dqaBankController.setUsername(Username);
            dqaBankController.setSsn(ssn);
            dqaBankController.setId(id);
            dqaBankController.setCourseId(courseId);
            dqaBankController.setSelectedCourse(selectedCourse);
            dqaBankController.setDStudentController(this);
            Stage stage = (Stage) DHomeButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        };
    }
    private EventHandler<ActionEvent> StudentsButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/DStudent/DStudent.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            DStudentController dStudentController = fxmlLoader.getController();
            dStudentController.setUsername(Username);
            dStudentController.setSsn(ssn);
            dStudentController.setId(id);
            dStudentController.setCourseId(courseId);
            dStudentController.setSelectedCourse(selectedCourse);
            dStudentController.setDHomeController(this);
            Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        };
    }
    private EventHandler<ActionEvent> BackButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/DBefore/DBefore.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            DBeforeController dBeforeController = fxmlLoader.getController();
            dBeforeController.setUsername(Username);
            dBeforeController.setSsn(ssn);
            dBeforeController.setId(id);
            dBeforeController.setDBeforeController(this);
            Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        };
    }
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
            loginController.setDExamController(this);
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
            DoctorProfileController doctorProfileController = fxmlLoader.getController();
            doctorProfileController.setUsername(Username);
            doctorProfileController.setSsn(ssn);
            doctorProfileController.setId(id);
            doctorProfileController.setDExamController(this);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL); // This line makes the new window modal
            stage.setScene(scene);
            stage.showAndWait();
        };
    }


    public void initialize() {
        DHomeButton.setOnAction(DHomeButtonClicked());
        QBankButton.setOnAction(QBankButtonClicked());
        StudentsButton.setOnAction(StudentsButtonClicked());
        BackButton.setOnAction(BackButtonClicked());
        LogOutButton.setOnAction(LogOutButtonClicked());
        DoctorProfile.setOnAction(DoctorProfileButtonClicked());
        Notification.setOnAction(NotificationButtonClicked());
        applyHoverEffect(DHomeButton);
        applyHoverEffect(QBankButton);
        applyHoverEffect(StudentsButton);
        applyHoverEffect(BackButton);
        applyHoverEffect(LogOutButton);


    }
    public void ViewExams() {
        try {
            PendingExamsPane.getChildren().clear();
            CompletedExamsPane.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/App/ExamCard/ExamCard.fxml"));
            VBox newQuizPane = loader.load();
            ExamCardController examCardController = loader.getController();
            Exam quiz = new Exam();
            PendingExamsPane.getChildren().add(newQuizPane);
            quiz.setDoctorId(Integer.parseInt(id));
//            System.out.println("Course ID: "+courseId);
            quiz.setQbId(Integer.parseInt(courseId));
            examCardController.setDExamController(this);
            examCardController.setExam(quiz);
//            System.out.println(quiz.getQbId());
        } catch (IOException e) {
            System.out.println("Error in loading scene : "+e.getMessage());
        }


        Client client = new Client();
        client.sendMessage("getExamsOfCourse");
        client.sendMessage(courseId);
        List<Exam> quizzes = client.getExams();
        if (quizzes == null) {
            System.out.println("No Exams Found");
            return;
        }

        for (Exam quiz : quizzes) {

            if (quiz.getStartDate().isAfter(java.time.LocalDateTime.now()))
            {
                addExamUpcoming(quiz);
            }
            else {
                addExamCompleted(quiz);
            }
        }
    }
    private void addExamCompleted(Exam quiz) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/App/ExamView/ExamView.fxml"));
            VBox newQuizPane = loader.load();
            ExamViewController examCardController = loader.getController();
            examCardController.setExam(quiz);
            examCardController.setDExamController(this);
            CompletedExamsPane.getChildren().add(newQuizPane);
        } catch (IOException e) {
            System.out.println("Error in loading scene : "+e.getMessage());
        }
    }

    private void addExamUpcoming(Exam quiz) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/App/ExamView/ExamView.fxml"));
            VBox newQuizPane = loader.load();
            ExamViewController examCardController = loader.getController();
            quiz.setDoctorId(Integer.parseInt(id));
            quiz.setQbId(Integer.parseInt(courseId));
            examCardController.setExam(quiz);
            examCardController.setDExamController(this);
            PendingExamsPane.getChildren().add(newQuizPane);
        } catch (IOException e) {
            System.out.println("Error in loading scene : "+e.getMessage());
        }
    }


    private DHomeController dHomeController;
    public void setDHomeController(DHomeController dHomeController) {
        this.dHomeController = dHomeController;
    }

    private DStudentController dStudentController;
    public void setDStudentController(DStudentController dStudentController) {
        this.dStudentController = dStudentController;
    }

    private DQABankController dQABankController;
    public void setDStudentController(DQABankController dqaBankController) {
        this.dQABankController = dqaBankController;
    }
    private String Username;
    public void setUsername(String username) {
        this.Username = username;
    }
    private String id;
    public void setId(String id) {
        this.id = id;
    }
    private String selectedCourse;
    public void setSelectedCourse(String selectedCourse) {
        this.selectedCourse = selectedCourse;
    }
    private String courseId;
    public void setCourseId(String courseId) {
        this.courseId = courseId;
        ViewExams();
    }
    private String ssn;
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    private void applyHoverEffect(Button button) {
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #2e2e2e")); // Hover color
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #202020")); // Default color
    }
    private EligableStudentController eligableStudentController;
    public void setEligableStudentController(EligableStudentController eligableStudentController) {
        this.eligableStudentController = eligableStudentController;
    }
}
