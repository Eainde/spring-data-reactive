package com.eainde.reactive.security;

import com.eainde.reactive.util.JWTUtil;

import io.jsonwebtoken.Claims;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationManager implements ReactiveAuthenticationManager {
  private final JWTUtil jwtUtil;

  public AuthenticationManager(final JWTUtil jwtUtil) {
    this.jwtUtil = jwtUtil;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Mono<Authentication> authenticate(Authentication authentication) {
    String authToken = authentication.getCredentials().toString();
    String username = jwtUtil.getUsernameFromToken(authToken);
    return Mono.just(jwtUtil.validateToken(authToken))
        .filter(valid -> valid)
        .switchIfEmpty(Mono.empty())
        .map(
            valid -> {
              Claims claims = jwtUtil.getAllClaimsFromToken(authToken);
              List<Map> rolesMap = claims.get("role", List.class);
              return new UsernamePasswordAuthenticationToken(
                  username,
                  null,
                  rolesMap
                      .stream()
                      .map(k -> new SimpleGrantedAuthority((String) k.get("roleName")))
                      .collect(Collectors.toList()));
            });
  }
}
