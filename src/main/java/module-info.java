module App {
    requires javafx.controls;
    requires javafx.fxml;


    opens App.Login to javafx.fxml;
    exports App.Login;

    opens App.Register to javafx.fxml;
    exports App.Register;

    opens App.Welcome to javafx.fxml;
    exports App.Welcome;

}