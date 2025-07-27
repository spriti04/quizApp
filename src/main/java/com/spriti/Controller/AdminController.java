package com.spriti.Controller;

import com.spriti.DTO.QuizDto;
import com.spriti.Entity.Question;
import com.spriti.Entity.Quiz;
import com.spriti.Entity.Submission;
import com.spriti.Entity.User;
import com.spriti.Service.QuestionService;
import com.spriti.Service.QuizService;
import com.spriti.Service.SubmitService;
import com.spriti.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userServ;

    @Autowired
    private QuizService quizService;

    @Autowired
    private QuestionService queServ;

    @Autowired
    private SubmitService submitService;

    @PostMapping("/saveAdmin")
    public ResponseEntity<User> saveUserHandler(@RequestBody User user){
        user.setRole("ROLE_ADMIN");
        User user1 = userServ.saveUser(user);

        return new ResponseEntity<>(user1, HttpStatus.CREATED);
    }

    @PostMapping("/saveQuiz")
    public ResponseEntity<String> saveQuizHandler(@RequestBody QuizDto quizDto){
        Quiz quiz1 = quizService.convertToQuiz(quizDto);

        String result = quizService.saveQuizzes(quiz1);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PostMapping("/saveQues/{id}")
    public ResponseEntity<String> saveQuesHandler(@PathVariable int id,
                                                  @RequestBody Question question){
        String result = queServ.saveQuestions(id, question);

        return new ResponseEntity<>(result , HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Submission>> getAllSubmissions(){
        List<Submission> submissions = submitService.getAll();

        return ResponseEntity.ok(submissions);
    }

}
