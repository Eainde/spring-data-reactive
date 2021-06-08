package com.eainde.reactive.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.eainde.reactive.entity.Book;

public interface BookRepository extends ReactiveCrudRepository<Book, Long> {}
