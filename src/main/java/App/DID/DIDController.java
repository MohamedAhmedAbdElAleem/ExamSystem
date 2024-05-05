package App.DID;

import App.ADoctors.ADoctorsController;

public class DIDController {
    private String username;
    private String process;
    private ADoctorsController aDoctorsController;

    public void setUsername(String username1) {
        this.username = username1;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public void setADoctorsController(ADoctorsController aDoctorsController) {
        this.aDoctorsController = aDoctorsController;
    }
}
