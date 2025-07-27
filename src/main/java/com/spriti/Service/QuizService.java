package com.spriti.Service;

import com.spriti.DTO.QuizDto;
import com.spriti.DTO.QuizResponseDto;
import com.spriti.Entity.Quiz;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public interface QuizService {

    // Mapper Method
    public Quiz convertToQuiz(QuizDto quizDto);

    public String saveQuizzes(Quiz quiz);

    public QuizResponseDto getQuizById(int id);

    public String deleteQuizzes(int id);

    public String updateQuiz(int id, String startTime, String endTime);
}
