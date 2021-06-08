package com.eainde.reactive.router;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.eainde.reactive.entity.User;
import com.eainde.reactive.handler.UserHandlerFunction;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Configuration
public class RouterFunctionConfig {
  @Bean
  @RouterOperation(
      operation =
          @Operation(
              operationId = "findAllUsers",
              summary = "Find All Users",
              tags = {"Users"},
              responses = {
                @ApiResponse(
                    responseCode = "200",
                    description = "successful operation",
                    content = @Content(schema = @Schema(implementation = User.class))),
                @ApiResponse(responseCode = "404", description = "Users not found")
              }))
  public RouterFunction<ServerResponse> route(UserHandlerFunction handlerFunction) {
    return RouterFunctions.route(
        GET("/user").and(accept(MediaType.APPLICATION_JSON)), handlerFunction::getAllUsers);
  }
}
