package com.eainde.reactive.handler;

import com.eainde.reactive.entity.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@SpringBootTest
@AutoConfigureWebTestClient
class UserHandlerFunctionTest {
  @Autowired private WebTestClient webTestClient;
  private String token;

  @Test
  @WithMockUser(username = "Akshay", roles = "SUPERADMIN")
  void getAllUsers() {
    Flux<User> userFlux =
        webTestClient
            .get()
            .uri("/users")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .returnResult(User.class)
            .getResponseBody();

    StepVerifier.create(userFlux).expectSubscription().expectNextCount(4).verifyComplete();
  }

  @Test
  @WithMockUser(username = "Akshay", roles = "SUPERADMIN")
  void getUserById() {
    Flux<User> userFlux =
        webTestClient
            .get()
            .uri("/user/{id}", 1)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .returnResult(User.class)
            .getResponseBody();

    StepVerifier.create(userFlux).expectSubscription().expectNextCount(1).verifyComplete();
  }
}
