package com.spriti.Service;

import com.spriti.DTO.QuizSubmissionDto;
import com.spriti.Entity.Submission;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SubmitService {

    public String submitQuiz(int quizId, QuizSubmissionDto submissionDto, String userEmail);

    public List<Submission> getAll();
}
