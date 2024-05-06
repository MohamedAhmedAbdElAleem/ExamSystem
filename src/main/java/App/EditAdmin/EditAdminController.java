package App.EditAdmin;

import App.AAdmins.AAdminsController;

public class EditAdminController {

    private String username;
    private String password;
    private String id;
    private AAdminsController aAdminsController;
    public void setUsername(String username) {
        this.username = username;
    }

    public void setAAdminsController(AAdminsController aAdminsController) {
        this.aAdminsController = aAdminsController;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAdminId(String id) {
        this.id = id;
    }
}
