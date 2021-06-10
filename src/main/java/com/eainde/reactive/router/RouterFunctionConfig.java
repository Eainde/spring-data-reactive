package com.eainde.reactive.router;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import com.eainde.reactive.handler.UserHandlerFunction;
import com.eainde.reactive.service.UserService;

import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterFunctionConfig {
  @Bean
  @RouterOperations({
    @RouterOperation(path = "/users", beanClass = UserService.class, beanMethod = "getAllUsers"),
    @RouterOperation(
        path = "/user/{id}",
        beanClass = UserService.class,
        beanMethod = "getUserById"),
    @RouterOperation(
        path = "/user/{name}",
        beanClass = UserService.class,
        beanMethod = "getUserByName")
  })
  public RouterFunction<ServerResponse> route(UserHandlerFunction handlerFunction) {
    return RouterFunctions.route(
            GET("/users").and(accept(MediaType.APPLICATION_JSON)), handlerFunction::getAllUsers)
        .andRoute(
            GET("/user/{id}").and(accept(MediaType.APPLICATION_JSON)), handlerFunction::getUserById)
        .andRoute(
            GET("/user/name/{name}").and(accept(MediaType.APPLICATION_JSON)),
            handlerFunction::getUserByName);
  }
}
