package App.AssignStudent;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;


public class AssignStudentApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.AssignStudent.AssignStudentApplication.class.getResource("AssignStudent.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Examak System");
        Image icon = new Image(getClass().getResourceAsStream("/App/images/logo.png"));
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        stage.setFullScreen(false);

    }
    public static void main(String[] args) {
        launch();
    }
}
