package com.elice.bookstore.book.domain.dto;


import com.elice.bookstore.book.domain.Book;
import com.elice.bookstore.book.domain.qna.Question;
import com.elice.bookstore.book.domain.qna.QuestionStatus;
import lombok.Getter;
import lombok.Setter;

/**
 * RequestQuestion.
 */
@Getter
@Setter
public class RequestQuestion {


  private Long bookId;
  private String content;
  private String title;
  private String createdBy;


  // 테스트 코드를 위한
  public Question toEntity(Book book) {
    return Question.builder()
        .book(book)
        .title(title)
        .content(content)
        .createdBy(createdBy)
        .status(QuestionStatus.ANSWER_PENDING)
        .build();
  }
}
