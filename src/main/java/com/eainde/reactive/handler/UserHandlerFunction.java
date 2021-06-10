package com.eainde.reactive.handler;

import com.eainde.reactive.entity.User;
import com.eainde.reactive.service.UserService;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class UserHandlerFunction {
  private final UserService userService;

  UserHandlerFunction(final UserService userService) {
    this.userService = userService;
  }

  public Mono<ServerResponse> getAllUsers(ServerRequest serverRequest) {
    return ServerResponse.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(userService.findAll(), User.class);
  }

  public Mono<ServerResponse> getUserById(ServerRequest serverRequest) {
    return ServerResponse.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(userService.findById(Long.parseLong(serverRequest.pathVariable("id"))), User.class);
  }

  public Mono<ServerResponse> getUserByName(ServerRequest serverRequest) {
    return ServerResponse.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(userService.findByName(serverRequest.pathVariable("name")), User.class);
  }
}
