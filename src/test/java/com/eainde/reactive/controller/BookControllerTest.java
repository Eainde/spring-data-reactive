package com.eainde.reactive.controller;

import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.eainde.reactive.entity.Book;
import com.eainde.reactive.entity.User;
import com.eainde.reactive.exception.GlobalErrorWebExceptionHandler;
import com.eainde.reactive.repository.BookRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = {BookController.class})
class BookControllerTest {
  @Autowired private WebTestClient webTestClient;
  @MockBean GlobalErrorWebExceptionHandler globalErrorWebExceptionHandler;

  @MockBean private BookRepository bookRepository;
  private Book book;

  @BeforeEach
  void setUp() {
    book = new Book();
    book.setId(1);
    book.setAuthor("John");
    book.setTitle("Java");
  }

  @Test
  @WithMockUser(username = "Akshay", roles = "SUPERADMIN")
  void getBooks() {
    Mockito.when(bookRepository.findAll()).thenReturn(Flux.just(book));

    Flux<User> bookFlux =
        webTestClient
            .get()
            .uri("/book/")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .returnResult(User.class)
            .getResponseBody();

    StepVerifier.create(bookFlux).expectSubscription().expectNextCount(1).verifyComplete();
  }

  @Test
  @WithMockUser(username = "akshay", roles = "SUPERADMIN")
  void saveBook() {
    Mockito.when(bookRepository.save(book)).thenReturn(Mono.just(book));

    webTestClient
        .mutateWith(csrf())
        .post()
        .uri("/book/")
        .accept(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(book))
        .exchange()
        .expectStatus()
        .isOk();
    // Mockito.verify(bookRepository, Mockito.times(1)).save(book);

  }

  @Test
  @WithMockUser(username = "akshay", roles = "SUPERADMIN")
  void updateBook() {
    Mockito.when(bookRepository.save(book)).thenReturn(Mono.just(book));

    webTestClient
        .mutateWith(csrf())
        .put()
        .uri("/book/")
        .accept(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(book))
        .exchange()
        .expectStatus()
        .isOk();
  }
}
