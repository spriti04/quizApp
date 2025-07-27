package com.spriti.DTO;

import java.util.List;

public class QuizResponseDto {

    private int quizId;
    private String title;
    private int timeLimitInMinutes;
    private List<QuestionResDto> questions;

    public QuizResponseDto() {

    }

    public QuizResponseDto(int quizId, String title, int timeLimitInMinutes, List<QuestionResDto> questions) {
        this.quizId = quizId;
        this.title = title;
        this.timeLimitInMinutes = timeLimitInMinutes;
        this.questions = questions;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTimeLimitInMinutes() {
        return timeLimitInMinutes;
    }

    public void setTimeLimitInMinutes(int timeLimitInMinutes) {
        this.timeLimitInMinutes = timeLimitInMinutes;
    }

    public List<QuestionResDto> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionResDto> questions) {
        this.questions = questions;
    }
}
