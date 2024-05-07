package App.SID;

import App.ACourses.ACoursesController;
import App.ADoctors.ADoctorsController;
import App.DStudent.DStudentController;

public class SIDController {


    private String username;
    private String process;
    private String id;
    private DStudentController dStudentController;

    public void setUsername(String username1) {
        this.username = username1;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public void setDStudentController(DStudentController dStudentController) {
        this.dStudentController = dStudentController;
    }

    public void setId(String id) {
        this.id = id;
    }
}
