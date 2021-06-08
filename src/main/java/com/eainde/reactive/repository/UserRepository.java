package com.eainde.reactive.repository;

import com.eainde.reactive.entity.User;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UserRepository extends ReactiveCrudRepository<User, Long> {}
