package com.spriti.Service;

import com.spriti.DTO.QuestionResDto;
import com.spriti.DTO.QuizDto;
import com.spriti.DTO.QuizResponseDto;
import com.spriti.Entity.Question;
import com.spriti.Entity.Quiz;
import com.spriti.Repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl implements QuizService{

    @Autowired
    private QuizRepository quizRepository;

    @Override
    //Mapper method
    public Quiz convertToQuiz(QuizDto quizDto){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        Quiz quiz = new Quiz();
        quiz.setTitle(quizDto.getTitle());
        quiz.setDescription(quizDto.getDescription());
        quiz.setStartTime(LocalDateTime.parse(quizDto.getStartTime(), formatter));
        quiz.setEndTime(LocalDateTime.parse(quizDto.getEndTime(), formatter));

        List<Question> questions = quizDto.getQuestions();
        for(Question que : questions){
            que.setQuiz(quiz);
        }

        quiz.setQuestions(questions);

        return quiz;
    }

    @Override
    public String saveQuizzes(Quiz quiz) {
        Quiz quiz1 = quizRepository.save(quiz);

        return "Quiz saved Successfully.";

    }

    @Override
    public QuizResponseDto getQuizById(int id) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quiz not exists"));

        List<QuestionResDto> quesResDtos = quiz.getQuestions().stream()
                .map(q -> new QuestionResDto(
                        q.getId(),
                        q.getQueText(),
                        q.getOptA(),
                        q.getOptB(),
                        q.getOptC(),
                        q.getOptD()
                )).collect(Collectors.toList());


        return new QuizResponseDto(
                quiz.getId(),
                quiz.getTitle(),
                (int) java.time.Duration.between(quiz.getStartTime(), quiz.getEndTime()).toMinutes(),
                quesResDtos
        );
    }

    @Override
    public String deleteQuizzes(int id) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quiz not exists"));

        quizRepository.delete(quiz);

        return "Quiz Deleted Successfully";
    }

    @Override
    public String updateQuiz(int id, String startTime, String endTime) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quiz not exists"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDateTime stime = LocalDateTime.parse(startTime, formatter);
        LocalDateTime etime = LocalDateTime.parse(endTime, formatter);

        quiz.setStartTime(stime);
        quiz.setEndTime(etime);

        quizRepository.save(quiz);

        return "Quiz Updated Successfully";
    }
}