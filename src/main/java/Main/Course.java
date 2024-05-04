package Main;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private int courseId;
    private String name;
    private List<Exam> exams;

    public Course(int courseId, String name) {
        this.courseId = courseId;
        this.name = name;
        this.exams = new ArrayList<>();
    }

    public void addExam(Exam exam) {
        exams.add(exam);
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }
}
