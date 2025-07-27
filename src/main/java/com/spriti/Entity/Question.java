package com.spriti.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String queText;
    private String optA;
    private String optB;
    private String optC;
    private String optD;
    private String corrAns;

    @ManyToOne
    @JsonIgnore
    private  Quiz quiz;

    public Question(){

    }

    public Question(String queText, String optA, String optB, String optC, String optD, String corrAns, Quiz quiz){
        this.queText = queText;
        this.optA = optA;
        this.optB = optB;
        this.optC = optC;
        this.optD = optD;
        this.corrAns = corrAns;
        this.quiz = quiz;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQueText() {
        return queText;
    }

    public void setQueText(String queText) {
        this.queText = queText;
    }

    public String getOptA() {
        return optA;
    }

    public void setOptA(String optA) {
        this.optA = optA;
    }

    public String getOptB() {
        return optB;
    }

    public void setOptB(String optB) {
        this.optB = optB;
    }

    public String getOptC() {
        return optC;
    }

    public void setOptC(String optC) {
        this.optC = optC;
    }

    public String getOptD() {
        return optD;
    }

    public void setOptD(String optD) {
        this.optD = optD;
    }

    public String getCorrAns() {
        return corrAns;
    }

    public void setCorrAns(String corrAns) {
        this.corrAns = corrAns;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}
