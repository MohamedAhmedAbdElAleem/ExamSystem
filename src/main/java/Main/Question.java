package Main;

import java.io.Serializable;
import java.util.List;

public class Question implements Serializable {
    private int questionId;
    private String Question;
    private String difficultyLevel; // Use String for difficulty level
    private String lecture;
    private Boolean usedBefore;
    private String QuestionType;
    private String Answer;
    private String Option2;
    private String Option3;
    private String Option4;
    private List<String> options;
    public Question() {
        this.questionId = 0;
        this.Question = "";
        this.difficultyLevel = "";
        this.lecture = "";
        this.usedBefore = false;
        QuestionType = "";
    }
    public Question(int questionId, String content, String difficultyLevel, String lectureLabel) {
        this.questionId = questionId;
        this.Question = content;
        this.difficultyLevel = difficultyLevel;
        this.lecture = lectureLabel;
        this.usedBefore = false;
        QuestionType = "";
    }
    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String content) {
        this.Question = content;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public String getLecture() {
        return lecture;
    }

    public void setLecture(String lecture) {
        this.lecture = lecture;
    }

    public Boolean isUsedBefore() {
        return usedBefore;
    }

    public void setUsedBefore(Boolean usedBefore) {
        this.usedBefore = usedBefore;
    }

    public Boolean getUsedBefore() {
        return usedBefore;
    }

    public String getQuestionType() {
        return QuestionType;
    }

    public void setQuestionType(String questionType) {
        QuestionType = questionType;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }

    public String getOption2() {
        return Option2;
    }

    public void setOption2(String option2) {
        Option2 = option2;
    }

    public String getOption3() {
        return Option3;
    }

    public void setOption3(String option3) {
        Option3 = option3;
    }

    public String getOption4() {
        return Option4;
    }

    public void setOption4(String option4) {
        Option4 = option4;
    }
    private String studentAnswer;
    public void setStudentAnswer(String text) {
    }
}
