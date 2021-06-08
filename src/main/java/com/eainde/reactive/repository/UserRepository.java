package com.eainde.reactive.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.eainde.reactive.entity.User;

import com.eainde.reactive.entity.User;

public interface UserRepository extends ReactiveCrudRepository<User, Long> {}
