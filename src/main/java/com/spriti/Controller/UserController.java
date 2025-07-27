package com.spriti.Controller;

import com.spriti.DTO.QuizResponseDto;
import com.spriti.DTO.QuizSubmissionDto;
import com.spriti.Entity.User;
import com.spriti.Service.QuizService;
import com.spriti.Service.SubmitService;
import com.spriti.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userServ;

    @Autowired
    private QuizService quizService;

    @Autowired
    private SubmitService submitService;

    @PostMapping("/saveUser")
    public ResponseEntity<User> saveUserHandler(@RequestBody User user){
        user.setRole("ROLE_USER");
        User user1 = userServ.saveUser(user);

        return new ResponseEntity<>(user1, HttpStatus.CREATED);
    }

    @GetMapping("/getUser")
    public ResponseEntity<User> getUserHandler(@PathVariable String email){
        User user = userServ.getUser(email);

        return ResponseEntity.ok(user);
    }

    @GetMapping("/getQuiz/{id}")
    public ResponseEntity<QuizResponseDto> getQuizHandler(@PathVariable int id){
        QuizResponseDto qrd = quizService.getQuizById(id);

        return ResponseEntity.ok(qrd);
    }


    @PostMapping("/quiz/submit/{id}")
    public ResponseEntity<String> submitQuizHandler(@PathVariable int id,
                                                    @RequestBody QuizSubmissionDto submissionDto,
                                                    Authentication auth){
        String email = auth.getName();
        String result = submitService.submitQuiz(id, submissionDto, email);

        return ResponseEntity.ok(result);
    }


}
