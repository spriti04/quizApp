package com.spriti.Service;

import com.spriti.Entity.Question;
import com.spriti.Entity.Quiz;
import com.spriti.Repository.QuestionRepository;
import com.spriti.Repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServImpl implements QuestionService{

    @Autowired
    private QuestionRepository quesRepo;

    @Autowired
    private QuizRepository quizRepo;

    @Override
    public String saveQuestions(int id, Question question) {
        Quiz quiz = quizRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Not Found"));

        question.setQuiz(quiz);

        quesRepo.save(question);

        return "Questions saved successfully.";
    }

    @Override
    public String deleteQuestion(int id) {

        quesRepo.deleteById(id);

        return "Question deleted";
    }
}
