package com.elice.bookstore.book.domain.controller;


import com.elice.bookstore.book.domain.qna.Question;
import com.elice.bookstore.book.domain.dto.RequestQuestion;

import com.elice.bookstore.book.domain.service.QuestionService;
import com.elice.bookstore.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class QuestionController {
    private final QuestionService questionService;

    /**
     * Question : Post;
     */

    @PostMapping("/v1/question")
    public ResponseEntity<Question> createQuestion(@RequestBody RequestQuestion question) {

//        // 현재 인증된 사용자의 정보를 가져옵니다.
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentUserName = authentication.getName(); // 사용자 이름, 이메일 또는 아이디 등을 가져옵니다.
//
//        // 현재 사용자 객체를 얻기 위한 서비스 호출
//        User currentUser = userService.findByUsername(currentUserName);
//
//        // Question 생성 서비스를 호출하면서 현재 사용자 정보도 함께 전달합니다.
//        Question newQuestion = questionService.createQuestion(question, currentUser);
//
//        return ResponseEntity.ok(newQuestion);

        Question newQuestion = questionService.createQuestion(question, new User());
        return ResponseEntity.ok(newQuestion);
    }


    /**
     * Question : Get;
     */
    @GetMapping("/v1/question")
    public ResponseEntity<List<Question>> findALlQuestion() {
        List<Question> questions = questionService.findAllQuestion();

        return ResponseEntity.ok(questions);
    }


    @GetMapping("/v1/{id}")
    public ResponseEntity<List<Question>> findQuestionsByBookId(@PathVariable Long id) {
        List<Question> questions = questionService.findQuestionsByBookId(id);
        return ResponseEntity.ok(questions);
    }

    /**
     * Question : Delete;
     */

    @DeleteMapping("/v1/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);

        return ResponseEntity.ok().build();
    }

}
