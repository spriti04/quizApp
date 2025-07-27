package com.spriti.DTO;

public class AnswerDto {

    private int questionId;
    private String selectedOption;

    public AnswerDto(){

    }

    public AnswerDto(int questionId, String selectedOption){
        this.questionId = questionId;
        this.selectedOption = selectedOption;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
    }
}
