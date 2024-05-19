package App.ViewExamDoctor;

import App.AreYouSure.AreYouSureController;
import App.DExam.DExamController;
import App.ExamView.ExamViewController;
import App.Notification.NotificationController;
import App.PdfNum.PdfNumController;
import Main.Client;
import Main.Exam;
import Main.Question;
import Main.Validation;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class ViewExamDoctorController {
    Validation validation = new Validation();
    @FXML
    private Button DoneButton;
    @FXML
    private Button saveAsPDFButton;
    @FXML
    private TableView<Question> QuestionView;
    @FXML
    private TableColumn<Question, String> IDColumn;
    @FXML
    private TableColumn<Question, String> QuestionColumn;
    @FXML
    private TableColumn<Question, String> AnswerColumn;
    @FXML
    private TableColumn<Question, String> DifficultyColumn;
    @FXML
    private TableColumn<Question, String> LectureColumn;
    @FXML
    private TableColumn<Question, String> TypeColumn;
    @FXML
    private RadioButton True;
    @FXML
    private RadioButton False;
    @FXML
    private Button DownloadPDFButton;


    private void ViewQuestions() {
        Client client = new Client();
        client.sendMessage("getQuestionsOfExam");
        String courseId = String.valueOf(quiz.getQbId());
        client.sendMessage(courseId);
        client.sendMessage(quiz.getQuestionsIds());
        ObservableList<Question> questions = client.getQuestionsOfExam();
        QuestionView.setItems(questions);
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("questionId"));
        QuestionColumn.setCellValueFactory(new PropertyValueFactory<>("Question"));
        AnswerColumn.setCellValueFactory(new PropertyValueFactory<>("Answer"));
        DifficultyColumn.setCellValueFactory(new PropertyValueFactory<>("difficultyLevel"));
        LectureColumn.setCellValueFactory(new PropertyValueFactory<>("lecture"));
        TypeColumn.setCellValueFactory(new PropertyValueFactory<>("QuestionType"));
        DoneButton.setOnAction(DoneButtonClicked());
    }

    private EventHandler<ActionEvent> DoneButtonClicked() {
        return e -> {
            if (True.isSelected()||False.isSelected()){
                Client client = new Client();
                client.sendMessage("updateExam");
                client.sendMessage(String.valueOf(quiz.getExamId()));
                client.sendMessage(True.isSelected()?"true":"false");
                String response = client.receiveMessage();
                if(response.equalsIgnoreCase("true")) {
                    validation.showSuccessPopUp("Exam Updated Successfully");
                    Stage stage = (Stage) DoneButton.getScene().getWindow();
                    stage.close();
                } else {
                    validation.showErrorPopUp("Error in Updating Exam");
                    Stage stage = (Stage) DoneButton.getScene().getWindow();
                    stage.close();
                }
            } else {
                validation.showErrorPopUp("Please Select the Result Option");
            }
        };
    }

//    public void initialize() {
////        DownloadPDFButton.setOnAction(DownloadPDFButtonClicked());
//    }

    private EventHandler<ActionEvent> DownloadPDFButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/PdfNum/PdfNum.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            PdfNumController loginController = fxmlLoader.getController();
//           //loginController.setUsername(Username);
            loginController.setPdfNumController(this);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL); // This line makes the new window modal
            stage.setScene(scene);
            stage.showAndWait();
        };
    }

    @FXML
    private void initialize() {
        saveAsPDFButton.setOnAction(e -> {
            try {
                saveExamAsPDF();
            } catch (IOException ioException) {
                validation.showErrorPopUp("Error saving PDF: " + ioException.getMessage());
            }
        });
    }

    private void saveExamAsPDF() throws IOException {
        String imagePath = Paths.get("src/main/resources/test/logo.png").toAbsolutePath().toString();
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        // Draw frame
        float margin = 20;
        float width = PDRectangle.A4.getWidth() - 2 * margin;
        float height = PDRectangle.A4.getHeight() - 2 * margin;

        contentStream.setLineWidth(2);
        contentStream.addRect(margin, margin, width, height);
        contentStream.stroke();

        // Adding logo
        PDImageXObject pdImage = PDImageXObject.createFromFile(imagePath, document);
        contentStream.drawImage(pdImage, margin + 10, height - 90, 100, 100);

        // Adding text with default font
        contentStream.beginText();
        contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 28);
        contentStream.newLineAtOffset(margin + 120, height - 20);
        contentStream.showText("Final Exam");
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 14);
        contentStream.newLineAtOffset(margin + 120, height - 40);
        contentStream.showText("Advanced OOP");
        contentStream.newLineAtOffset(250, -20);
        contentStream.showText("Date: 2024-5-19 10:00 AM");
        contentStream.newLineAtOffset(0, -20);
        contentStream.showText("Duration: 02:00 hours");
        contentStream.endText();

        // Adding student name and registration number
        contentStream.beginText();
        contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 14);
        contentStream.newLineAtOffset(margin + 120, height - 90);
        contentStream.showText("Name: ");
        contentStream.newLineAtOffset(0, -20);
        contentStream.showText("Registration Number: ");
        contentStream.endText();

        ObservableList<Question> questions = QuestionView.getItems();
        float questionY = height - 150;

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);

            if (questionY < 50) { // Check if there's enough space on the current page
                contentStream.close();
                page = new PDPage(PDRectangle.A4);
                document.addPage(page);
                contentStream = new PDPageContentStream(document, page);
                questionY = height - 50;
            }

            // Adding question text
            contentStream.beginText();
            contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);
            contentStream.newLineAtOffset(margin + 10, questionY);
            contentStream.showText((i + 1) + ". " + question.getQuestion());
            contentStream.endText();
            questionY -= 20;

            // Adding answer choices
            String[] choices = question.getAnswer().split("\n"); // assuming answers are split by newline
            for (String choice : choices) {
                contentStream.beginText();
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);
                contentStream.newLineAtOffset(margin + 30, questionY);
                contentStream.showText(choice);
                contentStream.endText();
                questionY -= 20;
            }

            questionY -= 10; // Space between questions
        }

        contentStream.close();

        // Save the document
        document.save(new File("exam.pdf"));
        document.close();

        validation.showSuccessPopUp("Exam saved as PDF successfully.");
    }

    private DExamController dExamController;
    public void setDExamController(DExamController dExamController) {
        this.dExamController = dExamController;
    }
    private ExamViewController examCardController;
    public void setExamCardController(ExamViewController examViewController) {
        this.examCardController = examViewController;
    }
    private Exam quiz;
    public void setExam(Exam quiz) {
        this.quiz = quiz;
        Platform.runLater(this::ViewQuestions);
    }
}
