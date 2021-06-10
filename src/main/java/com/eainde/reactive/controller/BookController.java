package com.eainde.reactive.controller;

import com.eainde.reactive.entity.Book;
import com.eainde.reactive.repository.BookRepository;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/book")
public class BookController {
  private final BookRepository bookRepository;

  BookController(final BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @GetMapping("/")
  @PreAuthorize("hasRole('SUPERADMIN') or hasRole('USER') or hasRole('ADMIN')")
  public Flux<Book> getBooks() {
    return bookRepository.findAll();
  }

  @PostMapping("/")
  @PreAuthorize("hasRole('SUPERADMIN') or hasRole('USER') or hasRole('ADMIN')")
  public Mono<Book> saveBook(@RequestBody Book book) {
    return bookRepository.save(book);
  }

  @PutMapping("/")
  @PreAuthorize("hasRole('SUPERADMIN') or hasRole('USER') or hasRole('ADMIN')")
  public Mono<Book> updateBook(@RequestBody Book book) {
    return bookRepository.save(book);
  }

  @DeleteMapping("/")
  @PreAuthorize("hasRole('SUPERADMIN') or hasRole('USER') or hasRole('ADMIN')")
  public boolean deleteBook(@RequestBody Book book) {
    try {
      bookRepository.deleteById(book.getId()).block();
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
