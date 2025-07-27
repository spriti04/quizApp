package com.spriti.DTO;

import com.spriti.Entity.Question;

import java.time.LocalDateTime;
import java.util.List;

public class QuizDto {

    private String title;
    private String description;
    private String startTime;
    private String endTime;

    private List<Question> questions;

    public QuizDto() {

    }

    public QuizDto(String title, String description, String startTime, String endTime, List<Question> questions) {
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.questions = questions;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
