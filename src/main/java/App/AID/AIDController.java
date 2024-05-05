package App.AID;

import App.AAdmins.AAdminsController;

public class AIDController {

    private String username;
    private String process;
    private AAdminsController aAdminsController;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAAdminsController(AAdminsController aAdminsController) {
        this.aAdminsController = aAdminsController;
    }

    public void setProcess(String process) {
        this.process = process;
    }
}
