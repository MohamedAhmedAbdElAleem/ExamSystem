package App.PdfNum;

import App.ViewExamDoctor.ViewExamDoctorController;
import Main.Exam;
import Main.Question;
import Main.Validation;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
//import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PdfNumController {
    @FXML
    private Button Download;
    @FXML
    private TextField PdfNum;
    private ViewExamDoctorController viewExamDoctorController;
    private ObservableList<Question> questions;
    Validation validation = new Validation();
    int flag = 0;

    public void initialize() {
        Download.setOnAction(downloadButtonClicked());
    }

    private EventHandler<ActionEvent> downloadButtonClicked() {
    return e -> {
        String pdfNumStr = PdfNum.getText();
        try {
            int pdfNum = Integer.parseInt(pdfNumStr);
            if (pdfNum <= 0) {
                validation.showErrorPopUp("Please enter a valid number.");
            } else {
                DirectoryChooser directoryChooser = new DirectoryChooser();
                File selectedDirectory = directoryChooser.showDialog(null);

                if (selectedDirectory == null) {
                    validation.showErrorPopUp("Please select a directory.");
                    return;
                }
                List<Question> quiz = new ArrayList<>(questions);
                for (int i = 0; i < pdfNum; i++)
                {
                    //suffle the questions
                    Collections.shuffle(quiz);
                    generatePDF(quiz.size(), quiz, i+1, selectedDirectory);
                }
                ((Button) e.getSource()).getScene().getWindow().hide();
                if (flag == 1) {
                    validation.showSuccessPopUp("PDFs generated successfully.");
                    flag = 0;
                }
            }
        } catch (NumberFormatException ex) {
            validation.showErrorPopUp("Please enter a valid number.");
        } catch (IOException ioException) {
            validation.showErrorPopUp("An error occurred while generating the PDF.");
        }
    };
}

    private void generatePDF(int numQuestions, List<Question> quiz, int examNumber, File selectedDirectory) throws IOException {
        String imagePath = Paths.get("src/main/resources/App/logo.png").toAbsolutePath().toString();
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        flag = 0;

        // Draw frame
        float margin = 20;
        float width = PDRectangle.A4.getWidth() - 2 * margin;
        float height = PDRectangle.A4.getHeight() - 2 * margin;

        contentStream.setLineWidth(2);
        contentStream.addRect(margin, margin, width, height);
        contentStream.stroke();

        // Adding logo
        PDImageXObject pdImage = PDImageXObject.createFromFile(imagePath, document);
        contentStream.drawImage(pdImage, margin + 20, height - 70, 80, 80);

        // Adding text with default font
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA, 28);
        contentStream.newLineAtOffset(margin + 120, height - 20);
        contentStream.showText(exam.getName());
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA, 14);
        contentStream.newLineAtOffset(margin + 120, height - 40);
        contentStream.showText(exam.getCourseName());
        contentStream.newLineAtOffset(250, -20);
        LocalTime time = LocalTime.parse(exam.getTime()); // Replace with your time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        String formattedTime = time.format(formatter);
        contentStream.showText("Date: "+exam.getDate()+" "+formattedTime);
        contentStream.newLineAtOffset(0, -20);
        double duration = exam.getDuration(); // Replace with your method to get the duration
        int hours = (int) duration;
        int minutes = (int) ((duration - hours) * 60);

        contentStream.showText("Duration: " + hours + " : " + minutes+" hours");

        contentStream.endText();

        // Adding student name and registration number
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA, 14);
        contentStream.newLineAtOffset(margin + 120, height - 60);
        contentStream.showText("Name: ");
        contentStream.newLineAtOffset(0, -20);
        contentStream.showText("Registration Number: ");
        contentStream.newLineAtOffset(-110,-20);
        contentStream.setFont(PDType1Font.HELVETICA, 20);
        contentStream.showText("I)Answers:");
        contentStream.endText();
        float tableHeight = drawTable(contentStream, margin + 10, height - 110, width - 2 * margin, numQuestions);
        float chooseLabelY = height - (140+tableHeight) ; // Subtract an additional 20 for some space between the table and the label
        float questionY = height - (170+tableHeight);

        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA, 20);
        contentStream.newLineAtOffset(margin + 10, chooseLabelY);
        contentStream.showText("II)Choose:");
        contentStream.endText();
        for (int i = 0; i < numQuestions && i < quiz.size(); i++) {
            Question question = quiz.get(i);

            if (questionY < 50) { // Check if there's enough space on the current page
                contentStream.close();
                page = new PDPage(PDRectangle.A4);
                document.addPage(page);
                contentStream = new PDPageContentStream(document, page);
                questionY = height - 20;
                // Draw frame
                contentStream.setLineWidth(2);
                contentStream.addRect(margin, margin, width, height);
                contentStream.stroke();
            }

            // Adding question text
            String questionText = (i + 1) + ". " + question.getQuestion();
            List<String> lines = splitTextIntoLines(questionText, width, 14, PDType1Font.HELVETICA);

            int numLines = lines.size();

            if (question.getQuestionType().equalsIgnoreCase("MCQ")) {
                List<String> answerLinesA = splitTextIntoLines("A) " + question.getAnswer(), width, 12, PDType1Font.HELVETICA);
                List<String> answerLinesB = splitTextIntoLines("B) " + question.getOption2(), width, 12, PDType1Font.HELVETICA);
                List<String> answerLinesC = splitTextIntoLines("C) " + question.getOption3(), width, 12, PDType1Font.HELVETICA);
                List<String> answerLinesD = splitTextIntoLines("D) " + question.getOption4(), width, 12, PDType1Font.HELVETICA);
                numLines += answerLinesA.size() + answerLinesB.size() + answerLinesC.size() + answerLinesD.size();
            } else {
                List<String> answerLinesTrue = splitTextIntoLines("True", width, 12, PDType1Font.HELVETICA);
                List<String> answerLinesFalse = splitTextIntoLines("False", width, 12, PDType1Font.HELVETICA);
                numLines += answerLinesTrue.size() + answerLinesFalse.size();
            }


            // Check if there's enough space on the current page for the question and its answers
            if (questionY - numLines * 20 < margin) {
                // If not, start a new page
                contentStream.close();
                page = new PDPage(PDRectangle.A4);
                document.addPage(page);
                contentStream = new PDPageContentStream(document, page);
                questionY = height - 20;
                // Draw frame
                contentStream.setLineWidth(2);
                contentStream.addRect(margin, margin, width, height);
                contentStream.stroke();
            }

            for (String line : lines) {
                contentStream.beginText();
                contentStream.newLineAtOffset(margin + 20, questionY);
                contentStream.setFont(PDType1Font.HELVETICA, 14);
                contentStream.showText(line);
                contentStream.endText();
                questionY -= 20; // Move to the next line
            }

        // Adding answer choices
            if (question.getQuestionType().equalsIgnoreCase("MCQ"))
            {
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.newLineAtOffset(margin + 30, questionY);
            contentStream.showText("A)"+question.getAnswer());
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("B) " + question.getOption2());
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("C) " + question.getOption3());
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("D) " + question.getOption4());
            contentStream.endText();
            questionY -= 80;
            questionY -= 10; // Space between questions
            }else {
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.newLineAtOffset(margin + 30, questionY);
                contentStream.showText("A) true");
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("B) false");
                contentStream.endText();
                questionY -= 40;
                questionY -= 10; // Space between questions
            }
        }

        contentStream.close();

        // Save the document
        document.save(new File(selectedDirectory, exam.getName() + " " + examNumber + ".pdf"));
        document.close();
        flag = 1;



    }
    private float drawTable(PDPageContentStream contentStream, float x, float y, float tableWidth, int numQuestions) throws IOException {
        int maxQuestionsPerRow = 8; // Maximum number of questions per row
        int rows = (int) Math.ceil((double) numQuestions / maxQuestionsPerRow) * 2; // Number of rows is twice the number of question rows (one for question numbers and one for answers)
        int cols = Math.min(numQuestions, maxQuestionsPerRow); // Number of columns is the smaller of the number of questions and maxQuestionsPerRow
        float rowHeight = 20f;
        float tableHeight = rowHeight * rows;
        float colWidth = tableWidth / (float)cols;

        // Draw rows
        for (int i = 0; i <= rows; i++) {
            contentStream.moveTo(x, y - i * rowHeight);
            contentStream.lineTo(x + tableWidth, y - i * rowHeight);
        }

        // Draw columns
        for (int i = 0; i <= cols; i++) {
            contentStream.moveTo(x + i * colWidth, y);
            contentStream.lineTo(x + i * colWidth, y - tableHeight);
        }

        contentStream.stroke();

        // Adding content
        contentStream.setFont(PDType1Font.HELVETICA, 16);
        float textX = x + 5;
        float textY = y - 15;

        for (int i = 0; i < numQuestions; i++) {
            int row = i / maxQuestionsPerRow;
            int col = i % maxQuestionsPerRow;

            // Add question number
            contentStream.beginText();
            contentStream.newLineAtOffset(textX + col * colWidth, textY - row * 2 * rowHeight);
            contentStream.showText(Integer.toString(i + 1));
            contentStream.endText();

            // Add empty space for answer
            contentStream.beginText();
            contentStream.newLineAtOffset(textX + col * colWidth, textY - (row * 2 + 1) * rowHeight);
            contentStream.showText(" ");
            contentStream.endText();
        }

        // Return the height of the table
        return tableHeight;
    }


    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    private List<String> splitTextIntoLines(String text, float width, float fontSize, PDFont font) throws IOException {
        List<String> lines = new ArrayList<>();
        String[] words = text.split(" ");
        String line = "";

        for (String word : words) {
            if (font.getStringWidth(line + word) / 1000 * fontSize > width-20) {
                lines.add(line);
                line = word + " ";
            } else {
                line += word + " ";
            }
        }

        if (!line.isEmpty()) {
            lines.add(line);
        }

        return lines;
    }
    public void setPdfNumController(ViewExamDoctorController viewExamDoctorController) {
        this.viewExamDoctorController = viewExamDoctorController;
    }

    public void setQuestions(ObservableList<Question> questions) {
        this.questions = questions;
    }
    private Exam exam;
    public void setExam(Exam quiz) {
        this.exam = quiz;
    }
    private String selectedCourse;
    public void setCourse(String selectedCourse) {
        this.selectedCourse = selectedCourse;

    }
}
