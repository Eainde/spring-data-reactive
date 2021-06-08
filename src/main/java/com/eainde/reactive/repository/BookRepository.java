package com.eainde.reactive.repository;

import com.eainde.reactive.entity.Book;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BookRepository extends ReactiveCrudRepository<Book, Long> {}
