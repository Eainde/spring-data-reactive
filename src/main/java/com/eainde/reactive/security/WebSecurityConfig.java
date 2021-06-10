package com.eainde.reactive.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class WebSecurityConfig {
  private final AuthenticationManager authenticationManager;
  private final SecurityContextRepository securityContextRepository;

  WebSecurityConfig(
      final AuthenticationManager authenticationManager,
      final SecurityContextRepository securityContextRepository) {
    this.authenticationManager = authenticationManager;
    this.securityContextRepository = securityContextRepository;
  }

  @Bean
  public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
    return http.exceptionHandling()
        .authenticationEntryPoint(
            (swe, e) ->
                Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED)))
        .accessDeniedHandler(
            (swe, e) ->
                Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN)))
        .and()
        .csrf()
        .disable()
        .formLogin()
        .disable()
        .httpBasic()
        .disable()
        .authenticationManager(authenticationManager)
        .securityContextRepository(securityContextRepository)
        .authorizeExchange()
        .pathMatchers(HttpMethod.OPTIONS)
        .permitAll()
        .pathMatchers(
            "/login/",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/webjars/**",
            "/health")
        .permitAll()
        .pathMatchers(HttpMethod.GET, "/users")
        .hasAnyRole("SUPERADMIN")
        .pathMatchers(HttpMethod.GET, "/user/**")
        .hasAnyRole("SUPERADMIN")
        .pathMatchers(HttpMethod.POST, "/book/**")
        .hasAnyRole("SUPERADMIN")
        .pathMatchers(HttpMethod.PUT, "/book/**")
        .hasAnyRole("SUPERADMIN")
        .anyExchange()
        .authenticated()
        .and()
        .build();
  }
}
