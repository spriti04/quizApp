package com.spriti.Service;

import com.spriti.Entity.Question;
import org.springframework.stereotype.Service;

@Service
public interface QuestionService {

    public String saveQuestions(int id, Question question);

//    public String updateQuestions(int id);

    public String deleteQuestion(int id);
}
