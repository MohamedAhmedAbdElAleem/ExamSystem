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

}