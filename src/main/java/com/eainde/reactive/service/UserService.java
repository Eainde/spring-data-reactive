package com.eainde.reactive.service;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;

import com.eainde.reactive.entity.User;
import com.eainde.reactive.repository.UserRepository;

@Service
public class UserService {
  private final UserRepository userRepository;

  UserService(final UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public Flux<User> findAll() {
    return userRepository.findAll();
  }
}
