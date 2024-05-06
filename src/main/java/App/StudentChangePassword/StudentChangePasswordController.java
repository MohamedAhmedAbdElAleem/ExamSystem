package App.StudentChangePassword;

import App.SExams.SExamsController;
import App.SHome.SHomeController;
import App.SResults.SResultsController;

public class StudentChangePasswordController {


    private SHomeController sHomeController;

    private SResultsController sResultsController;

    private SExamsController sExamsController;
    public void setSHomeController(SHomeController sHomeController) {
        this.sHomeController = sHomeController;
    }

    public void setSResultsController(SResultsController sResultsController) {
        this.sResultsController = sResultsController;
    }

    public void setSExamsController(SExamsController sExamsController) {
        this.sExamsController = sExamsController;
    }
}
