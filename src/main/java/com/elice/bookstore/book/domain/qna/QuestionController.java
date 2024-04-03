package com.elice.bookstore.book.domain.qna;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/questions")
public class QuestionController {
    private final QuestionService questionService;

    /**
     * Question Post;
     */

    @PostMapping
    public ResponseEntity<Question> createQuestion(@RequestBody Question question){
        Question newQuestion = questionService.createQuestion(question);
        return ResponseEntity.ok(newQuestion);
    }

    /**
     * Question Get;
     */
    @GetMapping
    public ResponseEntity<List<Question>> findALlQuestion(){
        List<Question> questions = questionService.findAllQuestion();
        return ResponseEntity.ok(questions);
    }

}
