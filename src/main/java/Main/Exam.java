package Main;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class Exam  implements Serializable {
    private int examId;
    private String name;
    private LocalDateTime startDate;
    private int duration;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
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
}
