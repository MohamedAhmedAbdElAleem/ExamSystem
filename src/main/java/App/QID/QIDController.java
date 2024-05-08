package App.QID;

import App.DQABank.DQABankController;
import Main.Client;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class QIDController {
    @FXML
    private Button ProceedButton;
    @FXML
    private TextField QID;
    public void initialize() {
        ProceedButton.setOnAction(ProceedButtonClicked());
    }

    private EventHandler<ActionEvent> ProceedButtonClicked() {
        return e -> {
            String questionID = QID.getText();
            Client client = new Client();
            client.sendMessage("checkQuestionIDBeforeDelete");
            client.sendMessage(questionID);
            client.sendMessage(courseId);
            String response = client.receiveMessage();
            if(response.equalsIgnoreCase("true")) {
                client.sendMessage("deleteQuestion");
                client.sendMessage(questionID);
                client.sendMessage(courseId);
                String response2 = client.receiveMessage();
                if(response2.equalsIgnoreCase("true")) {

                }else {
//                    System.out.println("Error in deleting question");
                }

            }


        };
    }

    private DQABankController dqaBankController;
    public void setDQABankController(DQABankController dqaBankController) {
        this.dqaBankController = dqaBankController;
    }
    private String courseId;
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
