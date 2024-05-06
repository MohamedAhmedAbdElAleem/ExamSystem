package App.QuestionID;

import App.DQABank.DQABankController;
import App.EditQuestion.EditQuestionController;

public class QuestionIDController {


    private EditQuestionController editQuestionController;
    private DQABankController dqaBankController;
    public void setEditQuestionController(EditQuestionController editQuestionController) {
        this.editQuestionController = editQuestionController;
    }

    public void setDQABankController(DQABankController dqaBankController) {
        this.dqaBankController = dqaBankController;
    }
}
