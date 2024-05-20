package App.DStudent;

import App.AddStudent.AddStudentController;
import App.AssignStudent.AssignStudentController;
import App.DBefore.DBeforeController;
import App.DExam.DExamController;
import App.DHome.DHomeController;
import App.DID.DIDController;
import App.DQABank.DQABankController;
import App.DoctorLogin.DoctorLoginController;
import App.DoctorProfile.DoctorProfileController;
import App.Notification.NotificationController;
import App.SID.SIDController;
import Main.Client;
import Main.Student;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Iterator;

public class DStudentController {
    private String username;
    @FXML
    private Button DoctorProfile;
    @FXML
    private Button Notification;
    @FXML
    private Button EditStudent;
    @FXML
    private Button DeleteStudent;
    @FXML
    private Button AddStudent;
    @FXML
    private Button DHomeButton;
    @FXML
    private Button QBankButton;
    @FXML
    private Button ExamsButton;
    @FXML
    private Button BackButton;
    @FXML
    private Button LogOutButton;
    @FXML
    private TableView<Student> StudentsView;
    @FXML
    private TableColumn<Student,String> ID;
    @FXML
    private TableColumn<Student,String> Name;
    @FXML
    private TableColumn<Student,String> Email;
    @FXML
    private TableColumn<Student,String> RegistrationNumber;
    @FXML
    private Button AssignButton;
    @FXML
    private Button ExcelImportStudents;
    @FXML
    private Button ExcelExportStudents;

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
            dHomeController.setUsername(username);
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
            dqaBankController.setUsername(username);
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
    private EventHandler<ActionEvent> ExamsButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/DExam/DExam.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            DExamController dExamController = fxmlLoader.getController();
            dExamController.setUsername(username);
            dExamController.setSsn(ssn);
            dExamController.setId(id);
            dExamController.setCourseId(courseId);
            dExamController.setSelectedCourse(selectedCourse);
            dExamController.setDStudentController(this);
            Stage stage = (Stage) DHomeButton.getScene().getWindow();
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
            dBeforeController.setUsername(username);
            dBeforeController.setSsn(ssn);
            dBeforeController.setId(id);
            dBeforeController.setDBeforeController(this);
            Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        };
    }

    private EventHandler<ActionEvent> AddStudentButtonClicked() {
        return e -> {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/AddStudent/AddStudent.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            AddStudentController addStudentController = fxmlLoader.getController();
            addStudentController.setCourseId(courseId);
            addStudentController.setDStudentController(this);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
            ViewStudents();
        };
    }

    public void initialize(){
        DHomeButton.setOnAction(DHomeButtonClicked());
        QBankButton.setOnAction(QBankButtonClicked());
        ExamsButton.setOnAction(ExamsButtonClicked());
        BackButton.setOnAction(BackButtonClicked());
        LogOutButton.setOnAction(LogOutButtonClicked());
        AddStudent.setOnAction(AddStudentButtonClicked());
        EditStudent.setOnAction(EditDeleteStudentButtonClicked("Edit"));
        DeleteStudent.setOnAction(EditDeleteStudentButtonClicked("UnAssiqn"));
        DoctorProfile.setOnAction(DoctorProfileButtonClicked());
        Notification.setOnAction(NotificationButtonClicked());
//        ViewStudents();
        ID.setCellValueFactory(new PropertyValueFactory<>("Sid"));
        Name.setCellValueFactory(new PropertyValueFactory<>("Sname"));
        Email.setCellValueFactory(new PropertyValueFactory<>("Semail"));
        RegistrationNumber.setCellValueFactory(new PropertyValueFactory<>("SregistrationNumber"));
        AssignButton.setOnAction(AssignButtonClicked());
        applyHoverEffect(DHomeButton);
        applyHoverEffect(QBankButton);
        applyHoverEffect(ExamsButton);
        applyHoverEffect(BackButton);
        applyHoverEffect(LogOutButton);
        ExcelExportStudents.setOnAction(ExcelExportStudentsButtonClicked());
        ExcelImportStudents.setOnAction(ExcelImportStudentsButtonClicked());
    }

    private EventHandler<ActionEvent> ExcelImportStudentsButtonClicked() {
        return e ->{
            try {
                openFile();
                updateStudents();
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
                ViewStudents();
        };

    }

    private void updateStudents() {
        Client client = new Client();
        client.sendMessage("AddStudents");
        client.sendMessage(courseId);
        for (Student student : students) {
            client.sendMessage(student.getSname());
            client.sendMessage(student.getSid());
            client.sendMessage(student.getSssn());
            client.sendMessage(student.getSemail());
            client.sendMessage(student.getSregistrationNumber());
            client.sendMessage(student.getSpassword());
        }
        client.sendMessage("end");
        ViewStudents();
        Platform.runLater(() -> {
            StudentsView.refresh();
        });
    }

    private void openFile() throws IOException {
        // System.out.println("Open File Chooser");
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            getStudentsFromExcel(selectedFile);
        }
    }
    public ObservableList<Student> getStudentsFromExcel(File file) throws IOException {
//        ObservableList<Student> studentList = FXCollections.observableArrayList();
        try {
            FileInputStream excelFile = new FileInputStream(file);
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            int rowCount = datatypeSheet.getPhysicalNumberOfRows();
            int colCount = datatypeSheet.getRow(0).getPhysicalNumberOfCells();
            Iterator<Row> iterator = datatypeSheet.iterator();

            for (int i = 0; i < rowCount; i++) {
                Student student = new Student();
                Row currentRow = datatypeSheet.getRow(i);
                Iterator<Cell> cellIterator = currentRow.cellIterator();

                while (cellIterator.hasNext()) {
                    Cell currentCell = cellIterator.next();
                    int columnIndex = currentCell.getColumnIndex();

                    switch (columnIndex) {
                        case 0:
                            student.setSname(currentCell.getStringCellValue());
                            break;
                        case 1:
                            student.setSid(String.valueOf(currentCell.getStringCellValue()));
                            break;
                        case 2:
                            student.setSssn(currentCell.getStringCellValue());
                            break;
                        case 3:
                            student.setSemail(currentCell.getStringCellValue());
                            break;
                        case 4:
                            student.setSregistrationNumber( String.valueOf(currentCell.getStringCellValue()));
                            break;
                        case 5:
                            student.setSpassword( String.valueOf(currentCell.getStringCellValue()));
                            break;
                        default:
                            break;
                    }
                }
                boolean flag = false;
                for (Student student1 : students)
                {
                    if(student1.getSregistrationNumber().equalsIgnoreCase(student.getSregistrationNumber())){
                        flag=true;
                        break;
                    }
                }
                if (flag){
                    continue;
                }else{
                students.add(student);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }
    private EventHandler<ActionEvent> ExcelExportStudentsButtonClicked() {
        return e -> {
            try {
                Workbook newWorkbook = new XSSFWorkbook();
                Sheet newSheet = newWorkbook.createSheet("StudentData");
                for (int i = 0; i < students.size(); i++) {
                    Row row = newSheet.createRow(i);
                    row.createCell(0).setCellValue(students.get(i).getSname());
                    row.createCell(1).setCellValue(students.get(i).getSid());
                    row.createCell(2).setCellValue(students.get(i).getSssn());
                    row.createCell(3).setCellValue(students.get(i).getSemail());
                    row.createCell(4).setCellValue(students.get(i).getSregistrationNumber());
                    row.createCell(5).setCellValue(students.get(i).getSpassword());
                }

                // Open a FileChooser to let the user select where to save the file
                FileChooser fileChooser = new FileChooser();
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));
                File file = fileChooser.showSaveDialog(null);

                if (file != null) {
                    FileOutputStream outputStream = new FileOutputStream(file);
                    newWorkbook.write(outputStream);
                    newWorkbook.close();
                    outputStream.close();
                }
            } catch (Exception err) {
                err.printStackTrace();
            }
        };
    }

    private EventHandler<ActionEvent> AssignButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/AssignStudent/AssignStudent.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                System.out.println("Error in loading scene : "+ex.getMessage());
            }
            AssignStudentController assignStudentController = fxmlLoader.getController();
            assignStudentController.setCourseId(courseId);
            assignStudentController.setDStudentController(this);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL); // This line makes the new window modal
            stage.setScene(scene);
            stage.showAndWait();
            ViewStudents();
        };
    }
    private ObservableList<Student> students;
    private void ViewStudents() {
        Client client = new Client();
        client.sendMessage("GetStudentsOfCourse");
        client.sendMessage(courseId);
        students = client.getStudents();
        StudentsView.setItems(students);
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
            loginController.setDStudentController(this);
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
            doctorProfileController.setUsername(username);
            doctorProfileController.setSsn(ssn);
            doctorProfileController.setId(id);
            doctorProfileController.setDStudentController(this);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL); // This line makes the new window modal
            stage.setScene(scene);
            stage.showAndWait();
        };
    }
    private EventHandler<ActionEvent> EditDeleteStudentButtonClicked(String Process) {
        return e -> {
            // Get the selected student from the TableView
            Student selectedStudent = StudentsView.getSelectionModel().getSelectedItem();

            // Check if a row is selected
                // A row is selected, proceed with the unassign operation
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/App/SID/SID.fxml"));
                Scene scene = null;
                try {
                    scene = new Scene(fxmlLoader.load());
                    SIDController sidController = fxmlLoader.getController();
                    sidController.setUsername(username);
                    sidController.setId(id);
                if (selectedStudent != null) {
                    sidController.setSelectedStudentId(selectedStudent.getSid());
                } else {
                }
                    sidController.setCourseId(courseId);
                    sidController.setProcess(Process);
                    sidController.setDStudentController(this);

                    // Send the ID of the selected student to the SIDController

                } catch (IOException ex) {
                    System.out.println("Error in loading scene : "+ex.getMessage());
                }
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();
                ViewStudents();
                // No row is selected, show an error message or do nothing
        };
    }

    private DHomeController dHomeController;
    public void setDHomeController(DHomeController dHomeController) {
        this.dHomeController = dHomeController;
    }

    private DQABankController dqaBankController;
    public void setDHomeController(DQABankController dqaBankController) {
        this.dqaBankController = dqaBankController;
    }

    private DExamController dExamController;
    public void setDHomeController(DExamController dExamController) {
        this.dExamController = dExamController;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    private String id;
    public void setId(String id) {
        this.id = id;
    }
    private String selectedCourse;
    public void setSelectedCourse(String selectedCourse) {
        this.selectedCourse = selectedCourse;
//        System.out.println(selectedCourse);
    }
    private String courseId;
    public void setCourseId(String courseId) {
        this.courseId = courseId;
        ViewStudents();
//        System.out.println("CourseId in DStudentController : "+courseId);
    }

    private String ssn;
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    private void applyHoverEffect(Button button) {
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #2e2e2e")); // Hover color
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #202020")); // Default color
    }
}

