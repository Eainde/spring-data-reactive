package com.eainde.reactive.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

import com.eainde.reactive.entity.User;
import com.eainde.reactive.repository.UserRepository;

@Component
public class UserHandlerFunction {
  private final UserRepository userRepository;

  UserHandlerFunction(final UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public Mono<ServerResponse> getAllUsers(ServerRequest serverRequest) {
    return ServerResponse.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(userRepository.findAll(), User.class);
  }
}
