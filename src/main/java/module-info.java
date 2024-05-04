module App {
    requires javafx.controls;
    requires javafx.fxml;


    opens App.AdminLogin to javafx.fxml;
    exports App.AdminLogin;

    opens App.Welcome to javafx.fxml;
    exports App.Welcome;

}