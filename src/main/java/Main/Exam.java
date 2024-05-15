package Main;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Exam  implements Serializable {
    private static final long serialVersionUID = 8391221681566801910L; // Add this line
    private int examId;
    private String name;
    private LocalDateTime startDate;
    private double duration;
    private int TotalMarks;
    private int lectureStart;
    private int lectureEnd;
    private int doctorId;
    private int easyMarks;
    private int mediumMarks;
    private int hardMarks;
    private int MCQE;
    private int MCQM;
    private int MCQH;
    private int TFE;
    private int TFM;
    private int TFH;
    private int QbId;
    private String QuestionsIds;

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public int getTotalMarks() {
        return TotalMarks;
    }

    public void setTotalMarks(int totalMarks) {
        TotalMarks = totalMarks;
    }

    public int getLectureStart() {
        return lectureStart;
    }

    public void setLectureStart(int lectureStart) {
        this.lectureStart = lectureStart;
    }

    public int getLectureEnd() {
        return lectureEnd;
    }

    public void setLectureEnd(int lectureEnd) {
        this.lectureEnd = lectureEnd;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getEasyMarks() {
        return easyMarks;
    }

    public void setEasyMarks(int easyMarks) {
        this.easyMarks = easyMarks;
    }

    public int getMediumMarks() {
        return mediumMarks;
    }

    public void setMediumMarks(int mediumMarks) {
        this.mediumMarks = mediumMarks;
    }

    public int getHardMarks() {
        return hardMarks;
    }

    public void setHardMarks(int hardMarks) {
        this.hardMarks = hardMarks;
    }

    public int getMCQE() {
        return MCQE;
    }

    public void setMCQE(int MCQE) {
        this.MCQE = MCQE;
    }

    public int getMCQM() {
        return MCQM;
    }

    public void setMCQM(int MCQM) {
        this.MCQM = MCQM;
    }

    public int getMCQH() {
        return MCQH;
    }

    public void setMCQH(int MCQH) {
        this.MCQH = MCQH;
    }

    public int getTFE() {
        return TFE;
    }

    public void setTFE(int TFE) {
        this.TFE = TFE;
    }

    public int getTFM() {
        return TFM;
    }

    public void setTFM(int TFM) {
        this.TFM = TFM;
    }

    public int getTFH() {
        return TFH;
    }

    public void setTFH(int TFH) {
        this.TFH = TFH;
    }

    public int getQbId() {
        return QbId;
    }

    public void setQbId(int qbId) {
        QbId = qbId;
    }

    public String getQuestionsIds() {
        return QuestionsIds;
    }

    public void setQuestionsIds(String questionsIds) {
        QuestionsIds = questionsIds;
    }
    public void setStartDate(LocalDate date, int hour, int minute){
        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();
        this.startDate = LocalDateTime.of(year,month,day, hour, minute);
    }

    public String getDate() {
        String date = startDate.toLocalDate().toString();
//        System.out.println(date);
        return date;
    }

    public String getTime() {
        String time = startDate.toLocalTime().toString();
        return time;
    }
    private String examGrade;
    public void setExamGrade(String notGradedYet) {
        this.examGrade = notGradedYet;
        if (notGradedYet == null|| notGradedYet.equals("Not Graded Yet"))
            this.examGrade = "Not Graded Yet";
        else
            this.examGrade = notGradedYet + " / " + Double.parseDouble(String.valueOf(TotalMarks));
    }

    public String getExamGrade() {
        return examGrade;
    }

    public String getExamEnd() {
        LocalDateTime end = startDate.plusMinutes((long)(duration * 60));
        return end.toLocalTime().toString();
    }
}
