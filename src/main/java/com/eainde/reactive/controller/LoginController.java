package com.eainde.reactive.controller;

import com.eainde.reactive.model.AuthRequest;
import com.eainde.reactive.model.AuthResponse;
import com.eainde.reactive.service.UserService;
import com.eainde.reactive.util.JWTUtil;
import com.eainde.reactive.util.PBKDF2Encoder;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/login")
public class LoginController {
  private final JWTUtil jwtUtil;
  private final PBKDF2Encoder passwordEncoder;
  private final UserService userService;

  LoginController(
      final JWTUtil jwtUtil, final PBKDF2Encoder passwordEncoder, final UserService userService) {
    this.jwtUtil = jwtUtil;
    this.passwordEncoder = passwordEncoder;
    this.userService = userService;
  }

  @PostMapping("/")
  public Mono<ResponseEntity<AuthResponse>> login(@RequestBody AuthRequest ar) {
    return userService
        .findByName(ar.getUsername())
        .filter(
            userDetails ->
                passwordEncoder.encode(ar.getPassword()).equals(userDetails.getPassword()))
        .map(userDetails -> ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails))))
        .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
  }
}
