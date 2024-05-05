package App.AddDoctor;

import App.ADoctors.ADoctorsController;

public class AddDoctorController {

    private String username;
    private ADoctorsController aDoctorsController;
    public void setUsername(String username1) {
        this.username = username1;
    }

    public void setAAdminsController(ADoctorsController aDoctorsController) {
        this.aDoctorsController = aDoctorsController;
    }
}
