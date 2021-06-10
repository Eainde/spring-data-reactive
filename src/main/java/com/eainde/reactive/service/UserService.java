package com.eainde.reactive.service;

import com.eainde.reactive.entity.User;
import com.eainde.reactive.repository.UserRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {
  private final UserRepository userRepository;

  UserService(final UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Operation(
      operationId = "findAllUsers",
      summary = "Find all users",
      tags = {"User"},
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "successful operation",
            content = @Content(schema = @Schema(implementation = User.class))),
        @ApiResponse(responseCode = "404", description = "Users not found")
      })
  public Flux<User> findAll() {
    return userRepository.findAll();
  }

  @Operation(
      operationId = "findUserById",
      summary = "Find user by id",
      tags = {"User"},
      parameters = {@Parameter(in = ParameterIn.PATH, name = "id", description = "User Id")},
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "successful operation",
            content = @Content(schema = @Schema(implementation = User.class))),
        @ApiResponse(responseCode = "400", description = "Invalid User ID supplied"),
        @ApiResponse(responseCode = "404", description = "User not found")
      })
  public Flux<User> findById(long id) {
    return userRepository.findById(id);
  }

  @Operation(
      operationId = "findUserByname",
      summary = "Find user by name",
      tags = {"User"},
      parameters = {@Parameter(in = ParameterIn.PATH, name = "name", description = "User name")},
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "successful operation",
            content = @Content(schema = @Schema(implementation = User.class))),
        @ApiResponse(responseCode = "400", description = "Invalid User Name supplied"),
        @ApiResponse(responseCode = "404", description = "User not found")
      })
  public Mono<User> findByName(String name) {
    return userRepository.findByName(name);
  }
}
