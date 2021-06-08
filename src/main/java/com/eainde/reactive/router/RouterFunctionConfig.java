package com.eainde.reactive.router;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.eainde.reactive.handler.UserHandlerFunction;

@Configuration
public class RouterFunctionConfig {
  @Bean
  public RouterFunction<ServerResponse> route(UserHandlerFunction handlerFunction) {
    return RouterFunctions.route(
        GET("/user").and(accept(MediaType.APPLICATION_JSON)), handlerFunction::getAllUsers);
  }
}
