package com.spriti.Service;

import com.spriti.DTO.AnswerDto;
import com.spriti.DTO.QuizSubmissionDto;
import com.spriti.Entity.Question;
import com.spriti.Entity.Quiz;
import com.spriti.Entity.Submission;
import com.spriti.Entity.User;
import com.spriti.Repository.QuestionRepository;
import com.spriti.Repository.QuizRepository;
import com.spriti.Repository.SubmissionRepository;
import com.spriti.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class SubmitServImpl implements SubmitService{

    @Autowired
    private QuizRepository quizRepo;

    @Autowired
    private QuestionRepository quesRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private SubmissionRepository submitRepo;

    @Override
    public String submitQuiz(int quizId, QuizSubmissionDto submissionDto, String userEmail) {
        Quiz quiz = quizRepo.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        User user = userRepo.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        int score = 0;

        for(AnswerDto answerDto : submissionDto.getAnswers()){
            Question question = quesRepo.findById(answerDto.getQuestionId())
                    .orElseThrow(() -> new RuntimeException("Question not found"));

            if (question.getCorrAns().equalsIgnoreCase(answerDto.getSelectedOption())){
                score++;
            }
        }

        Submission submission = new Submission();
        submission.setQuiz(quiz);
        submission.setUser(user);
        submission.setScore(score);
        submission.setSubmitTime(LocalTime.now());

        submitRepo.save(submission);

        return "Quiz Submitted. Your score is: " + score + "/" + submissionDto.getAnswers().size();
    }

    @Override
    public List<Submission> getAll() {
        List<Submission> submissions = submitRepo.findAll();

        return submissions;
    }
}
