module App {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    exports Main to javafx.graphics;

    opens App.AdminLogin to javafx.fxml;
    exports App.AdminLogin;

    opens App.Welcome to javafx.fxml;
    exports App.Welcome;

    opens App.DoctorLogin to javafx.fxml;
    exports App.DoctorLogin;

    opens App.StudentLogin to javafx.fxml;
    exports App.StudentLogin;

    opens App.AHome to javafx.fxml;
    exports App.AHome;

    opens App.AAdmins to javafx.fxml;
    exports App.AAdmins;

    opens App.ADoctors to javafx.fxml;
    exports App.ADoctors;

    opens App.ACourses to javafx.fxml;
    exports App.ACourses;

    opens App.DHome to javafx.fxml;
    exports App.DHome;

    opens App.DBefore to javafx.fxml;
    exports App.DBefore;

    opens App.DStudent to javafx.fxml;
    exports App.DStudent;

    opens App.DQABank to javafx.fxml;
    exports App.DQABank;

    opens App.DExam to javafx.fxml;
    exports App.DExam;

    opens App.SBefore to javafx.fxml;
    exports App.SBefore;

    opens App.SHome to javafx.fxml;
    exports App.SHome;

    opens App.SResults to javafx.fxml;
    exports App.SResults;

    opens App.SExams to javafx.fxml;
    exports App.SExams;

    opens App.AddAdmin to javafx.fxml;
    exports App.AddAdmin;

    opens App.AID to javafx.fxml;
    exports App.AID;

    opens App.EditAdmin to javafx.fxml;
    exports App.EditAdmin;

    opens App.AddDoctor to javafx.fxml;
    exports App.AddDoctor;

    opens App.DID to javafx.fxml;
    exports App.DID;

    opens App.EditDoctor to javafx.fxml;
    exports App.EditDoctor;

    opens App.AddCourse to javafx.fxml;
    exports App.AddCourse;

    opens App.CID to javafx.fxml;
    exports App.CID;

    opens App.AddStudent to javafx.fxml;
    exports App.AddStudent;

    opens App.SID to javafx.fxml;
    exports App.SID;

    opens App.EditStudent to javafx.fxml;
    exports App.EditStudent;

    opens App.AddQuestion to javafx.fxml;
    exports App.AddQuestion;

    opens App.MCQ to javafx.fxml;
    exports App.MCQ;

    opens App.TF to javafx.fxml;
    exports App.TF;

    opens App.QID to javafx.fxml;
    exports App.QID;

    opens App.EditMCQ to javafx.fxml;
    exports App.EditMCQ;

    opens App.EditTrueFalse to javafx.fxml;
    exports App.EditTrueFalse;

    opens App.GetPassword to javafx.fxml;
    exports App.GetPassword;

    opens App.StudentChangePassword to javafx.fxml;
    exports App.StudentChangePassword;

    opens App.EditQuestion to javafx.fxml;
    exports App.EditQuestion;

    opens App.QuestionID to javafx.fxml;
    exports App.QuestionID;


}