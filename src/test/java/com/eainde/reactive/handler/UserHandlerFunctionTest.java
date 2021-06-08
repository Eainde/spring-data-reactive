package com.eainde.reactive.handler;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import com.eainde.reactive.entity.User;

@SpringBootTest
@AutoConfigureWebTestClient
class UserHandlerFunctionTest {
  @Autowired private WebTestClient webTestClient;

  @Test
  void getAllUsers() {
    Flux<User> userFlux =
        webTestClient
            .get()
            .uri("/user")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .returnResult(User.class)
            .getResponseBody();

    StepVerifier.create(userFlux).expectSubscription().expectNextCount(4).verifyComplete();
  }
}
