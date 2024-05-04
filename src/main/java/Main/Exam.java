package Main;

import java.util.Date;

public class Exam   {
    private int examId;
    private String title;
    private int courseId;
    private Date startDate;
    private int duration;

    public Exam(int examId, String title, int courseId, Date startDate, int duration) {
        this.examId = examId;
        this.title = title;
        this.courseId = courseId;
        this.startDate = startDate;
        this.duration = duration;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
