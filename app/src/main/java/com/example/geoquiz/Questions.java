package com.example.geoquiz;

import java.io.Serializable;

class Questions implements Serializable {

    private int questionId;
    private boolean answer;
    private String category;
    private String type;


    public Questions(int questionId, boolean answer, String category, String type) {
        this.questionId = questionId;
        this.answer = answer;
        this.category = category;
        this.type = type;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
