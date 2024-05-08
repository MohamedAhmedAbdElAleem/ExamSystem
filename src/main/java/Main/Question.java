package Main;

import java.io.Serializable;

public class Question implements Serializable {
    private int questionId;
    private String Question;
    private String difficultyLevel; // Use String for difficulty level
    private String lecture;
    private Boolean usedBefore;
    private String QuestionType;
    private String Answer;
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
}
