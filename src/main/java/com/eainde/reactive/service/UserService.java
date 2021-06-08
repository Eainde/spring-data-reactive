package com.eainde.reactive.service;

import com.eainde.reactive.entity.User;
import com.eainde.reactive.repository.UserRepository;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

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
