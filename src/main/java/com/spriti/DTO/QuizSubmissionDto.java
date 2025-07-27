package com.spriti.DTO;

import java.util.List;

public class QuizSubmissionDto {

    List<AnswerDto> answers;

    public QuizSubmissionDto() {

    }

    public QuizSubmissionDto(List<AnswerDto> answers) {
        this.answers = answers;
    }

    public List<AnswerDto> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDto> answers) {
        this.answers = answers;
    }
}
