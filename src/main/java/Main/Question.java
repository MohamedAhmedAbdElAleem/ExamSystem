package Main;

public class Question {
    private int questionId;
    private String content;
    private String difficultyLevel; // Use String for difficulty level
    private String lectureLabel;
    private boolean usedBefore;
    private boolean isMcq;
    private boolean isTrueOrFalse;

    public Question(int questionId, String content, String difficultyLevel, String lectureLabel, boolean usedBefore) {
        this.questionId = questionId;
        this.content = content;
        this.difficultyLevel = difficultyLevel;
        this.lectureLabel = lectureLabel;
        this.usedBefore = usedBefore;
        this.isMcq = false;
        this.isTrueOrFalse = false;
    }
    public void setAsMcq() {
        isMcq = true;
        isTrueOrFalse = false;
    }

    public void setAsTrueOrFalse() {
        isTrueOrFalse = true;
        isMcq = false;
    }

    public boolean isMcq() {
        return isMcq;
    }

    public boolean isTrueOrFalse() {
        return isTrueOrFalse;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public String getLectureLabel() {
        return lectureLabel;
    }

    public void setLectureLabel(String lectureLabel) {
        this.lectureLabel = lectureLabel;
    }

    public boolean isUsedBefore() {
        return usedBefore;
    }

    public void setUsedBefore(boolean usedBefore) {
        this.usedBefore = usedBefore;
    }

    public void setMcq(boolean mcq) {
        isMcq = mcq;
    }

    public void setTrueOrFalse(boolean trueOrFalse) {
        isTrueOrFalse = trueOrFalse;
    }

}
